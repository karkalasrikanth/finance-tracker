package com.fintrack.fin.insight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsightRepository extends JpaRepository<Insight, Long> {
    List<Insight> findByUserIdOrderByCreatedTsDesc(Long userId);
}
