package ru.fintech.example.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "options")
@NoArgsConstructor
public class Option {
    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;

    @Column(name = "name")
    private String name;

    @Column(name = "price_per_month")
    private int pricePerMonth;

    @Column(name = "package_voice")
    private int packageVoice;

    @Column(name = "package_data")
    private int packageData;

    @Column(name = "package_sms")
    private int packageSms;

    @Column(name = "spec_code")
    private int specCode;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "option")
    private List<Sim> sims;

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

    public List<Sim> getSims() {
        return sims;
    }

    public void setSims(List<Sim> sims) {
        this.sims = sims;
    }

    @Override
    public String toString() {
        return "Option{" +
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