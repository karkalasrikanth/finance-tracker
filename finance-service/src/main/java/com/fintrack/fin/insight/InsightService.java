package com.fintrack.fin.insight;

import com.fintrack.fin.budget.BudgetHealthResponse;
import com.fintrack.fin.transaction.SummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsightService {

    private final InsightRepository insightRepository;

    public Insight saveInsight(Long userId, String query, String response) {
        Insight insight = new Insight();
        insight.setUserId(userId);
        insight.setQuery(query);
        insight.setResponse(response);

        return insightRepository.save(insight);
    }

    public List<Insight> getUserInsights(Long userId) {
        return insightRepository.findByUserIdOrderByCreatedTsDesc(userId);
    }

    public List<String> generateInsights(SummaryResponse summary, List<BudgetHealthResponse> budgetHealth) {

        List<String> insights = new ArrayList<>();

        // Rule 1: high spending
        if (summary.getTotalExpense().compareTo(summary.getTotalIncome().multiply(BigDecimal.valueOf(0.8))) > 0) {
            insights.add("Your expenses are high compared to income.");
        }

        // Rule 2: budget warnings
        for (BudgetHealthResponse b : budgetHealth) {
            if ("CRITICAL".equals(b.getStatus())) {
                insights.add("Critical overspending in " + b.getCategory());
            } else if ("WARNING".equals(b.getStatus())) {
                insights.add("Watch spending in " + b.getCategory());
            }
        }

        return insights;
    }
}