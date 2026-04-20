package com.fintrack.fin.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
            SELECT t.category, SUM(t.amount) FROM Transaction t 
                         WHERE t.userId = :userId AND t.type = 'EXPENSE' 
                         AND t.createdTs BETWEEN :start AND :end 
                                     GROUP BY t.category
            """)
    List<Object[]> getCategorySpend(Long userId, LocalDateTime start, LocalDateTime end);

    @Query("""
                SELECT t.type, SUM(t.amount) FROM Transaction t
                WHERE t.userId = :userId
                  AND t.createdTs BETWEEN :start AND :end
                GROUP BY t.type
            """)
    List<Object[]> getSummary(Long userId, LocalDateTime start, LocalDateTime end);

    List<Transaction> findTop5ByUserIdAndCreatedTsBetweenOrderByCreatedTsDesc(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
