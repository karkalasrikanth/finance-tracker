package com.fintrack.fin.insight;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insights")
@RequiredArgsConstructor
public class InsightController {

    private final InsightService insightService;

    @PostMapping
    public Insight create(@RequestBody InsightRequest insightRequest) {
        return insightService.saveInsight(
                insightRequest.getUserId(),
                insightRequest.getQuery(),
                insightRequest.getResponse()
        );
    }

    @GetMapping("/{userId}")
    public List<Insight> getByUser(@PathVariable Long userId) {
        return insightService.getUserInsights(userId);
    }
}
