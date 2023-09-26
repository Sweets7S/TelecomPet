package ru.fintech.example.models;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Renewal {
    private int oldUserId;
    private int msisdnId;
    private int newUserId;


    public int getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(int oldUserId) {
        this.oldUserId = oldUserId;
    }

    public int getMsisdnId() {
        return msisdnId;
    }

    public void setMsisdnId(int msisdnId) {
        this.msisdnId = msisdnId;
    }

    public int getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(int newUserId) {
        this.newUserId = newUserId;
    }
}
