package ru.fintech.example.DTO;

import lombok.ToString;

@ToString
public class OptionDTO {
    private int optionId;
    private String name;
    private int pricePerMonth;
    private int packageVoice;
    private int packageData;
    private int packageSms;
    private int specCode;
    private boolean active;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
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

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
                "optionId=" + optionId +
                ", name='" + name + '\'' +
                ", pricePerMonth=" + pricePerMonth +
                ", packageVoice=" + packageVoice +
                ", packageData=" + packageData +
                ", packageSms=" + packageSms +
                ", specCode=" + specCode +
                ", active=" + active +
                '}';
    }
}