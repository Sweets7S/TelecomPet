package ru.fintech.example.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "msisdn")
@NoArgsConstructor
public class Msisdn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msisdn_id")
    private int msisdnId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "icc")
    private String icc;

    @Column(name = "active")
    private boolean active;

    public int getMsisdnId() {
        return msisdnId;
    }

    public void setMsisdnId(int msisdnId) {
        this.msisdnId = msisdnId;
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

    @Override
    public String toString() {
        return "Msisdn{" +
                "msisdnId=" + msisdnId +
                ", userId=" + user.getId() +
                ", msisdn='" + msisdn + '\'' +
                ", icc='" + icc + '\'' +
                ", active=" + active +
                '}';
    }
}
