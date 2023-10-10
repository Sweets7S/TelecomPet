package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Tariff;
import ru.fintech.example.repository.SimRepository;
import ru.fintech.example.repository.TariffRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class TariffService {
    private TariffRepository tariffRepository;
    private SimRepository simRepository;

    public TariffService(TariffRepository tariffRepository, SimRepository simRepository) {
        this.tariffRepository = tariffRepository;
        this.simRepository = simRepository;
    }

    public TariffDTO create(TariffDTO tariffDTO) throws FaultException {
        Tariff tariff = ConversionDTO.transformToEntity(tariffDTO);
        Tariff tariffAfterSave = null;
        try {
            tariffAfterSave = tariffRepository.save(tariff);
        } catch (Exception exception) {
            log.info("1006: Тариф с таким названием уже существует - {}", tariff.getName());
            throw new FaultException(1006, "Тариф с таким названием уже существует - " + tariff.getName());
        }
        return ConversionDTO.transformToDTO(tariffAfterSave);
    }

    public List<TariffDTO> getAllActiveTariff() {
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        List<Tariff> tariffList = tariffRepository.findAll();
        for (int i = 1; i < tariffList.size(); i++) {
            if (tariffList.get(i).isActive()) {
                tariffDTOList.add(ConversionDTO.transformToDTO(tariffList.get(i)));
            }
        }
        return tariffDTOList;
    }

    public List<TariffDTO> getAllNotActiveTariff() {
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        List<Tariff> tariffList = tariffRepository.findAll();
        for (int i = 1; i < tariffList.size(); i++) {
            if (!(tariffList.get(i).isActive())) {
                tariffDTOList.add(ConversionDTO.transformToDTO(tariffList.get(i)));
            }
        }
        return tariffDTOList;
    }

    public void changePricePerMouth(int tariffId, int pricePerMouth) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setPricePerMonth(pricePerMouth);
        ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }
}
