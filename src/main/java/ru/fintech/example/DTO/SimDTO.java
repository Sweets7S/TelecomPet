package ru.fintech.example.DTO;

import lombok.ToString;

@ToString
public class SimDTO {
    private int simId;
    private int userId;
    private String msisdn;
    private String icc;
    private boolean active;
    private TariffDTO tariffDTO;
    private OptionDTO optionDTO;

    public int getSimId() {
        return simId;
    }

    public void setSimId(int simId) {
        this.simId = simId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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

    public TariffDTO getTariffDTO() {
        return tariffDTO;
    }

    public void setTariffDTO(TariffDTO tariffDTO) {
        this.tariffDTO = tariffDTO;
    }

    public OptionDTO getOptionDTO() {
        return optionDTO;
    }

    public void setOptionDTO(OptionDTO optionDTO) {
        this.optionDTO = optionDTO;
    }
}
