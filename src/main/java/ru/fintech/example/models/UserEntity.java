package ru.fintech.example.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "fio")
    private String fio;

    @Column(name = "document")
    private String document;

    @Column(name = "number")
    private String number;

    @Column(name = "active")
    private boolean active;

    @Column(name = "icc")
    private String icc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
