package com.fintrack.fin.budget;

import com.fintrack.fin.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;

    public List<BudgetAlert> checkBudget(Long userId, YearMonth yearMonth) {

        LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime end = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        // Budget limits
        List<Budget> budgets = budgetRepository.findByUserId(userId);

        // Actual spend
        Map<String, BigDecimal> spendMap = transactionRepository
                .getCategorySpend(userId, start, end)
                .stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (BigDecimal) result[1]
                ));

        List<BudgetAlert> alerts = new ArrayList<>();

        for (Budget budget : budgets) {
            BigDecimal spent = spendMap.getOrDefault(
                    budget.getCategory(),
                    BigDecimal.ZERO
            );

            if (spent.compareTo(budget.getMonthlyLimit()) > 0) {
                alerts.add(new BudgetAlert(
                        budget.getCategory(),
                        budget.getMonthlyLimit(),
                        spent
                ));
            }
        }

        return alerts;
    }

    public List<BudgetHealthResponse> getBudgetHealth(Long userId, YearMonth month) {

        LocalDateTime start = month.atDay(1).atStartOfDay();
        LocalDateTime end = month.atEndOfMonth().atTime(23, 59, 59);

        List<Budget> budgets = budgetRepository.findByUserId(userId);

        Map<String, BigDecimal> spentMap = transactionRepository
                .getCategorySpend(userId, start, end)
                .stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (BigDecimal) r[1]
                ));

        List<BudgetHealthResponse> budgetHealthResponses = new ArrayList<>();

        for (Budget b : budgets) {

            BigDecimal spent = spentMap.getOrDefault(b.getCategory(), BigDecimal.ZERO);
            BigDecimal limit = b.getMonthlyLimit();
            BigDecimal remaining = limit.subtract(spent);

            double percent = limit.compareTo(BigDecimal.ZERO) == 0
                    ? 0
                    : spent.doubleValue() / limit.doubleValue() * 100;

            String status =
                    percent >= 100 ? "CRITICAL" :
                            percent >= 80 ? "WARNING" :
                            "OK";

            budgetHealthResponses.add(new BudgetHealthResponse(
                    b.getCategory(),
                    limit,
                    spent,
                    remaining,
                    percent,
                    status
            ));
        }

        return budgetHealthResponses;
    }
}