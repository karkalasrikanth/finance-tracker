package com.fintrack.fin.dashboard;

import com.fintrack.fin.budget.BudgetHealthResponse;
import com.fintrack.fin.budget.BudgetService;
import com.fintrack.fin.insight.InsightService;
import com.fintrack.fin.transaction.SummaryResponse;
import com.fintrack.fin.transaction.Transaction;
import com.fintrack.fin.transaction.TransactionRepository;
import com.fintrack.fin.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionService transactionService;
    private final BudgetService budgetService;
    private final TransactionRepository transactionRepository;
    private final InsightService insightService;

    @Transactional(rollbackFor = Exception.class)
    public DashboardResponse getDashboard(Long userId, YearMonth month) {

        // Summary
        SummaryResponse summary = transactionService.getSummary(userId, month);

        // Budget health
        List<BudgetHealthResponse> budgetHealth = budgetService.getBudgetHealth(userId, month);

        // Recent transactions
        LocalDateTime start = month.atDay(1).atStartOfDay();
        LocalDateTime end = month.atEndOfMonth().atTime(23, 59, 59);

        List<Transaction> recentTransactions =
                transactionRepository.findTop5ByUserIdAndCreatedTsBetweenOrderByCreatedTsDesc(userId, start, end);

        // Rule-based insights
        List<String> insights = insightService.generateInsights(summary, budgetHealth);

        return new DashboardResponse(summary, budgetHealth, recentTransactions, insights);
    }
}
