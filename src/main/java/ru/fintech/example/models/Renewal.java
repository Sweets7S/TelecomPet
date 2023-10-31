package ru.fintech.example.models;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Renewal {
    private int oldUserId;
    private int simId;
    private int newUserId;


    public int getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(int oldUserId) {
        this.oldUserId = oldUserId;
    }

    public int getSimId() {
        return simId;
    }

    public void setSimId(int simId) {
        this.simId = simId;
    }

    public int getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(int newUserId) {
        this.newUserId = newUserId;
    }
}
