package com.fintrack.fin.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SummaryResponse {
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
}
