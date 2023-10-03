package ru.fintech.example.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tariff")
@NoArgsConstructor
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private int tariffId;

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

    @Column(name = "speed_max")
    private int speedMax;

    @Column(name = "package_voice_country")
    private int packageVoiceCountry;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "tariff")
    private List<Sim> sims;

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

    public List<Sim> getSims() {
        return sims;
    }

    public void setSims(List<Sim> sims) {
        this.sims = sims;
    }

    @Override
    public String toString() {
        return "Tariff{" +
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
