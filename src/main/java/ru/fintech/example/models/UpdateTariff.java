package ru.fintech.example.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
public class UpdateTariff {

    int tariffId;
    int packageVoice;
    int packageData;
    int packageSms;
    int packageVoiceCountry;

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getPackageVoice() {
        return packageVoice;
    }

    public void setPackageVoice(int packageVoice) {
        this.packageVoice = packageVoice;
    }

    public int getPackageData() {
        return packageData;
    }

    public void setPackageData(int packageData) {
        this.packageData = packageData;
    }

    public int getPackageSms() {
        return packageSms;
    }

    public void setPackageSms(int packageSms) {
        this.packageSms = packageSms;
    }

    public int getPackageVoiceCountry() {
        return packageVoiceCountry;
    }

    public void setPackageVoiceCountry(int packageVoiceCountry) {
        this.packageVoiceCountry = packageVoiceCountry;
    }
}
