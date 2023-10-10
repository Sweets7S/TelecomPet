package ru.fintech.example.DTO;

import lombok.ToString;

@ToString
public class TariffDTO {
    private int tariffId;
    private String name;
    private int pricePerMonth;
    private int packageVoice;
    private int packageData;
    private int packageSms;
    private int speedMax;
    private int packageVoiceCountry;
    private boolean active;

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(int pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
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

    public int getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(int speedMax) {
        this.speedMax = speedMax;
    }

    public int getPackageVoiceCountry() {
        return packageVoiceCountry;
    }

    public void setPackageVoiceCountry(int packageVoiceCountry) {
        this.packageVoiceCountry = packageVoiceCountry;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TariffDTO{" +
                "tariffId=" + tariffId +
                ", name='" + name + '\'' +
                ", pricePerMonth=" + pricePerMonth +
                ", packageVoice=" + packageVoice +
                ", packageData=" + packageData +
                ", packageSms=" + packageSms +
                ", speedMax=" + speedMax +
                ", packageVoiceCountry=" + packageVoiceCountry +
                ", active=" + active +
                '}';
    }
}