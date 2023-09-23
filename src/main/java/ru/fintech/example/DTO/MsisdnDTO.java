package ru.fintech.example.DTO;

public class MsisdnDTO {
    private int msisdnId;
    private int userId;
    private String msisdn;
    private String icc;
    private boolean active;

    public int getMsisdnId() {
        return msisdnId;
    }

    public void setMsisdnId(int msisdnId) {
        this.msisdnId = msisdnId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIcc() {
        return icc;
    }

    public void setIcc(String icc) {
        this.icc = icc;
    }
}
