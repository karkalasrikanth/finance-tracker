package com.fintrack.fin.insight;

import lombok.Getter;

@Getter
public class InsightRequest {
    private Long userId;
    private String query;
    private String response;
}
