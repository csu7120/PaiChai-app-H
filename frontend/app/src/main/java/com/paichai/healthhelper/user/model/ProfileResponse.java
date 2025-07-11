package com.paichai.healthhelper.user.model;

public class ProfileResponse {
    private String email;
    private String roleId;
    private String name;
    private String phone;
    private String profileUrl;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getProfileUrl() { return profileUrl; }
    public void setProfileUrl(String profileUrl) { this.profileUrl = profileUrl; }
}
