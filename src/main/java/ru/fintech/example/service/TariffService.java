package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Tariff;
import ru.fintech.example.repository.TariffRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

import static ru.fintech.example.service.UserService.technicalId;


@Slf4j
@Service
public class TariffService {
    private TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
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
//    public TariffDTO create(TariffDTO tariffDTO) throws FaultException {
//        Tariff tariff = ConversionDTO.transformToEntity(tariffDTO);
//        Tariff tariffExist = tariffRepository.getByName(tariffDTO.getName());
//        if (tariffExist != null) {
//            log.info(1006 + "Тариф с таким названием {} уже существует", tariff.getName());
//            throw new FaultException(1006, "Тариф с таким названием уже существует: " + tariff.getName());
//        }
//        Tariff tariffAfterSave = tariffRepository.save(tariff);
//        return ConversionDTO.transformToDTO(tariffAfterSave);
//    }

    public List<TariffDTO> getAllByActive(boolean active) {
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        List<Tariff> tariffs = tariffRepository.findAllByActive(active).stream().toList();
        for (int i = 1; i < tariffs.size(); i++) {
            if (tariffs.get(i).getTariffId() != technicalId) {
                tariffDTOList.add(ConversionDTO.transformToDTO(tariffs.get(i)));
            }
        }
        //Одинаковые
//        List<Tariff> tariffList = tariffs.stream()
//                .filter(it -> it.getTariffId() != technicalId)
//                .toList();
//        for (Tariff t: tariffList) {
//            tariffDTOList.add(ConversionDTO.transformToDTO(t));
//        }
        return tariffDTOList;
    }

    public void changePricePerMouth(int tariffId, int pricePerMouth) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setPricePerMonth(pricePerMouth);
        ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }

    public TariffDTO updateTariff(int tariffId, int packageVoice, int packageData, int packageSms, int packageVoiceCountry) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setPackageVoice(packageVoice);
        tariff.setPackageData(packageData);
        tariff.setPackageSms(packageSms);
        tariff.setPackageVoiceCountry(packageVoiceCountry);
        return ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }

    public void changeSpeedMax(int tariffId, int speedMax) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setSpeedMax(speedMax);
        ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }

    public void changeTariffToArchive(int tariffId) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setActive(false);
        ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }
}
