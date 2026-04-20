package com.fintrack.fin.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/summary")
    public SummaryResponse summary(@RequestParam Long userId, @RequestParam int year, @RequestParam int month) {
        return transactionService.getSummary(userId, YearMonth.of(year, month));
    }
}