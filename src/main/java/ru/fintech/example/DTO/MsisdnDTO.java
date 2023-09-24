package ru.fintech.example.DTO;

import lombok.ToString;

@ToString
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

    public MsisdnDTO msisdnId(int msisdnId){
        this.setMsisdnId(msisdnId);
        return this;
    }
    public MsisdnDTO userId(int userId){
        this.setUserId(userId);
        return this;
    }
    public MsisdnDTO msisdn(String msisdn){
        this.setMsisdn(msisdn);
        return this;
    }
    public MsisdnDTO icc(String icc){
        this.setIcc(icc);
        return this;
    }
    public MsisdnDTO active(boolean active) {
        this.setActive(active);
        return this;
    }
}
