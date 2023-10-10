package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Option;
import ru.fintech.example.models.Tariff;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class OptionService {
    private OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public OptionDTO create(OptionDTO optionDTO) throws FaultException {
        Option option = ConversionDTO.transformToEntity(optionDTO);
        Option optionAfterSave = null;
        try {
            optionAfterSave = optionRepository.save(option);
        } catch (Exception exception) {
            log.info("1008: Опция с таким названием уже существует - {}", option.getName());
            throw new FaultException(1008, "Опция с таким названием уже существует - " + option.getName());
        }
        return ConversionDTO.transformToDTO(optionAfterSave);
    }

//    public List<OptionDTO> getAllActiveOption() {
//        List<OptionDTO> optionDTOList = new ArrayList<>();
//        List<Option> optionList = optionRepository.findAll();
//        for (int i = 1; i < optionList.size(); i++) {
//            if (optionList.get(i).isActive()) {
//                optionDTOList.add(ConversionDTO.transformToDTO(optionList.get(i)));
//            }
//        }
//        return optionDTOList;
//    }
//
//    public List<OptionDTO> getAllNotActiveOption() {
//        List<OptionDTO> optionDTOList = new ArrayList<>();
//        List<Option> optionList = optionRepository.findAll();
//        for (int i = 1; i < optionList.size(); i++) {
//            if (!(optionList.get(i).isActive())) {
//                optionDTOList.add(ConversionDTO.transformToDTO(optionList.get(i)));
//            }
//        }
//        return optionDTOList;
//    }
}
