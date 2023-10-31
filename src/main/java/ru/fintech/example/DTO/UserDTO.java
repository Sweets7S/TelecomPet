package ru.fintech.example.DTO;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.ToString;

import java.util.List;

@ToString
public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String fio;
    private String document;
    private boolean active;
    private List<SimDTO> simDTOS;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SimDTO> getSimDTOS() {
        return simDTOS;
    }

    public void setSimDTOS(List<SimDTO> simDTOS) {
        this.simDTOS = simDTOS;
    }
}
