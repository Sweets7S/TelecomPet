package ru.fintech.example.models;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sim")
@NoArgsConstructor
@Hidden
public class Sim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_id")
    private int simId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "icc")
    private String icc;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

    public int getSimId() {
        return simId;
    }

    public void setSimId(int simId) {
        this.simId = simId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getIcc() {
        return icc;
    }

    public void setIcc(String icc) {
        this.icc = icc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Sim{" +
                "simId=" + simId +
                ", userId=" + user.getId() +
                ", msisdn='" + msisdn + '\'' +
                ", icc='" + icc + '\'' +
                ", active=" + active +
                ", tariffId=" + tariff.getTariffId() +
                ", optionId=" + option.getOptionId() +
                '}';
    }
}
