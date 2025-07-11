package com.paichai.health.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponse {
    private String email;
    private String roleId;
    private String name;
    private String phone;
    private String profileUrl;
}
