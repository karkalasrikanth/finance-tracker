package com.fintrack.fin.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BudgetHealthResponse {
    private String category;
    private BigDecimal limit;
    private BigDecimal spent;
    private BigDecimal remaining;
    private double usagePercent;
    private String status; // OK, WARNING, CRITICAL
}
