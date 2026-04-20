package com.fintrack.fin.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BudgetAlert {
    private String category;
    private BigDecimal limit;
    private BigDecimal spent;
}
