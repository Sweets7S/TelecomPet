package ru.fintech.example.models;

import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.fintech.example.DTO.UserDTO;

import java.io.Serializable;

@NoArgsConstructor
@ToString
public class ContractWithMsisdn implements Serializable {
    UserDTO userDTO;
    int msisdnId;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public int getMsisdnId() {
        return msisdnId;
    }

    public void setMsisdnId(int msisdnId) {
        this.msisdnId = msisdnId;
    }
}
