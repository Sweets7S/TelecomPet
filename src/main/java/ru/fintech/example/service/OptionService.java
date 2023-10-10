package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Option;
import ru.fintech.example.models.Tariff;
import ru.fintech.example.models.UpdateOption;
import ru.fintech.example.models.UpdateTariff;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.repository.SimRepository;
import ru.fintech.example.repository.TariffRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;
import static ru.fintech.example.service.UserService.technicalId;

@Slf4j
@Service
public class OptionService {
    private UserRepository userRepository;
    private SimRepository simRepository;
    private TariffRepository tariffRepository;
    private OptionRepository optionRepository;

    public OptionService(UserRepository userRepository, SimRepository simRepository,
                         TariffRepository tariffRepository, OptionRepository optionRepository) {
        this.userRepository = userRepository;
        this.simRepository = simRepository;
        this.tariffRepository = tariffRepository;
        this.optionRepository = optionRepository;
    }
    public OptionDTO addOption(OptionDTO optionDTO) throws FaultException {
        Option option = ConversionDTO.transformToEntity(optionDTO);
        Option optionExist = optionRepository.getByName(optionDTO.getName());
        if (optionExist != null) {
            log.info(1008 + "Опция с таким названием {} уже существует", option.getName());
            throw new FaultException(1008, "Опция с таким названием уже существует: " + option.getName());
        }
        Option optionAfterSave = optionRepository.save(option);
        return ConversionDTO.transformToDTO(optionAfterSave);
    }
    public List<OptionDTO> getAllAvailableOptions() {
        List<Option> options = optionRepository.findAllByActive(true).stream().toList();
        List<OptionDTO> optionDTOS = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getOptionId() != technicalId) {
                optionDTOS.add(ConversionDTO.transformToDTO(options.get(i)));
            }
        }
        return optionDTOS;
    }
    public List<OptionDTO> getAllArchiveOptions() {
        List<Option> options = optionRepository.findAllByActive(false).stream().toList();
        List<OptionDTO> optionDTOS = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getOptionId() != technicalId) {
                optionDTOS.add(ConversionDTO.transformToDTO(options.get(i)));
            }
        }
        return optionDTOS;
    }
    public OptionDTO changeStatus(int optionId) throws FaultException {
        Option option = optionRepository.getReferenceById(optionId);
        option.setActive(false);
        return ConversionDTO.transformToDTO(optionRepository.save(option));
    }
    public OptionDTO changePricePerMonth(int optionId, int newPrice) {
        Option option = optionRepository.getReferenceById(optionId);
        option.setPricePerMonth(newPrice);
        return ConversionDTO.transformToDTO(optionRepository.save(option));
    }
    public void updateOption(UpdateOption updateOption) throws FaultException {
        if (!optionRepository.existsById(updateOption.getOptionId())){
            log.info(1009 + "Данная опция {} не существует", updateOption.getOptionId());
            throw new FaultException(1009, "Данная опция не существует - " + updateOption.getOptionId());
        }
        Option option = optionRepository.getReferenceById(updateOption.getOptionId());
        option.setPackageVoice(updateOption.getPackageVoice());
        option.setPackageData(updateOption.getPackageData());
        option.setPackageSms(updateOption.getPackageSms());
        option.setSpecCode(updateOption.getSpecCode());
        log.info(option.toString());
        optionRepository.save(option);
    }
}
