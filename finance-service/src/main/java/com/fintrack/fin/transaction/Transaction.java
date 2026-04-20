package com.fintrack.fin.transaction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(length = 100)
    private String category;

    @Column(nullable = false, length = 20)
    private String type; // EXPENSE / INCOME

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "created_ts", updatable = false, insertable = false)
    private LocalDateTime createdTs;
}
