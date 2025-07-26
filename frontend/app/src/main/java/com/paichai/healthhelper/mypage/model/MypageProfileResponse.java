//상단 내 정보 (이름, 팔로워, 사진)

package com.paichai.healthhelper.mypage.model;

public class MypageProfileResponse {
    private String name;
    private String profileUrl;
    private int followerCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }
}
