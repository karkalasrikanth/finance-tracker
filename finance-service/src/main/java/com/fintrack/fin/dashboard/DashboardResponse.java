package com.fintrack.fin.dashboard;

import com.fintrack.fin.budget.BudgetHealthResponse;
import com.fintrack.fin.transaction.SummaryResponse;
import com.fintrack.fin.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DashboardResponse {

    private SummaryResponse summary;
    private List<BudgetHealthResponse> budgetHealth;
    private List<Transaction> recentTransactions;
    private List<String> insights;
}
