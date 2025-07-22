package com.paichai.health.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InfluencerResponse {
    private Integer userId;
    private String name;
    private String profileUrl;
}