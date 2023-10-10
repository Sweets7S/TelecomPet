package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Tariff;
import ru.fintech.example.models.UpdateTariff;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.repository.SimRepository;
import ru.fintech.example.repository.TariffRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.fintech.example.service.UserService.technicalId;

@Slf4j
@Service
public class TariffService {
    private UserRepository userRepository;
    private SimRepository simRepository;
    private TariffRepository tariffRepository;
    private OptionRepository optionRepository;

    public TariffService(UserRepository userRepository, SimRepository simRepository,
                         TariffRepository tariffRepository, OptionRepository optionRepository) {
        this.userRepository = userRepository;
        this.simRepository = simRepository;
        this.tariffRepository = tariffRepository;
        this.optionRepository = optionRepository;
    }

    public TariffDTO addTariff(TariffDTO tariffDTO) throws FaultException {
        Tariff tariff = ConversionDTO.transformToEntity(tariffDTO);
        Tariff tariffExist = tariffRepository.getByName(tariffDTO.getName());
        if (tariffExist != null) {
            log.info(1006 + "Тариф с таким названием {} уже существует", tariff.getName());
            throw new FaultException(1006, "Тариф с таким названием уже существует: " + tariff.getName());
        }
        //Одинаковое с кодом сверху
//        try {
//            tariffAfterSave = tariffRepository.save(tariff);
//        } catch (Exception exception) {
//            log.info("1006: Тариф с таким названием уже существует - {}", tariff.getName());
//            throw new FaultException(1006, "Тариф с таким названием уже существует - " + tariff.getName());
//        }
        return ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }

    public List<TariffDTO> getAllByActive(boolean active) {
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        List<Tariff> tariffList = tariffRepository.findAllByActive(active).stream().toList();
        for (int i = 1; i < tariffList.size(); i++) {
            if (tariffList.get(i).getTariffId() != technicalId) {
                tariffDTOList.add(ConversionDTO.transformToDTO(tariffList.get(i)));
            }
        }
        //Одинаковый с циклом сверху
//        List<Tariff> tariffList = tariffs.stream()
//                .filter(it -> it.getTariffId() != technicalId)
//                .toList();
//        for (Tariff t: tariffList) {
//            tariffDTOS.add(ConversionDTO.transformToDTO(t));
//        }

        return tariffDTOList;
    }

    public TariffDTO changePricePerMonth(int tariffId, int newPrice) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setPricePerMonth(newPrice);
        return ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }

    public void updateTariff(UpdateTariff updateTariff) throws FaultException {
        if (!tariffRepository.existsById(updateTariff.getTariffId())) {
            log.info(1007 + "Данный тариф {} не существует", updateTariff.getTariffId());
            throw new FaultException(1007, "Данный тариф не существует - " + updateTariff.getTariffId());
        }
        Tariff tariff = tariffRepository.getReferenceById(updateTariff.getTariffId());
        tariff.setPackageVoice(updateTariff.getPackageVoice());
        tariff.setPackageData(updateTariff.getPackageData());
        tariff.setPackageSms(updateTariff.getPackageSms());
        tariff.setPackageVoiceCountry(updateTariff.getPackageVoiceCountry());
        log.info(tariff.toString());
        tariffRepository.save(tariff);
    }

    public TariffDTO changeSpeedMax(int tariffId, int newMaxSpeed) {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setSpeedMax(newMaxSpeed);
        return ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }

    public TariffDTO changeStatusToArchive(int tariffId) throws FaultException {
        Tariff tariff = tariffRepository.getReferenceById(tariffId);
        tariff.setActive(false);
        return ConversionDTO.transformToDTO(tariffRepository.save(tariff));
    }
}
