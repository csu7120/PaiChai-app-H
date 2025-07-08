package com.paichai.health.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String profileUrl;
    private String roleId;
}
