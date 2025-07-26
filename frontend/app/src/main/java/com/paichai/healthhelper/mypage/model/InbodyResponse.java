//최근 인바디 기록 영역

package com.paichai.healthhelper.mypage.model;

public class InbodyResponse {
    private float weight;
    private float fatRatio;
    private String recordedAt;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getFatRatio() {
        return fatRatio;
    }

    public void setFatRatio(float fatRatio) {
        this.fatRatio = fatRatio;
    }

    public String getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(String recordedAt) {
        this.recordedAt = recordedAt;
    }
}
