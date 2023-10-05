package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Tariff;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.repository.SimRepository;
import ru.fintech.example.repository.TariffRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.List;

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
        List<Tariff> tariffList = tariffRepository.findAll();
        for (int i = 0; i < tariffList.size(); i++) {
            if (tariff.getName().equals(tariffList.get(i).getName())){
                throw new FaultException(1006, "Тариф с таким названием уже существует: "+ tariff.getName());
            }
        }
        Tariff tariffAfterSave = tariffRepository.save(tariff);
        return ConversionDTO.transformToDTO(tariffAfterSave);
    }

}
