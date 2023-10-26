package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Option;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

import static ru.fintech.example.service.UserService.technicalId;


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

    public List<OptionDTO> getAllByActive(boolean active) {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        List<Option> optionList = optionRepository.findAllByActive(active).stream().toList();
        for (int i = 1; i < optionList.size(); i++) {
            if (optionList.get(i).getOptionId() != technicalId) {
                optionDTOList.add(ConversionDTO.transformToDTO(optionList.get(i)));
            }
        }
        return optionDTOList;
    }

    public void changeOptionToArchive(int optionId) {
        Option option = optionRepository.getReferenceById(optionId);
        option.setActive(false);
        ConversionDTO.transformToDTO(optionRepository.save(option));
    }

    public void changePricePerMouth(int optionId, int pricePerMouth) {
        Option option = optionRepository.getReferenceById(optionId);
        option.setPricePerMonth(pricePerMouth);
        ConversionDTO.transformToDTO(optionRepository.save(option));
    }

    public OptionDTO updateOption(int optionId, int packageVoice, int packageData, int packageSms, int specCode) {
        Option option = optionRepository.getReferenceById(optionId);
        option.setPackageVoice(packageVoice);
        option.setPackageData(packageData);
        option.setPackageSms(packageSms);
        option.setSpecCode(specCode);
        return ConversionDTO.transformToDTO(optionRepository.save(option));
    }

    public OptionDTO changeActive(int optionId, boolean newActive) throws FaultException {
        Option option = optionRepository.getReferenceById(optionId);
        if (option.isActive() == newActive) {
            log.info("1012: Option - {} уже с этим значением - {}", optionId, newActive);
            throw new FaultException(1012, "Option - " + optionId + " уже с этим значением - " + newActive);
        }
        option.setActive(newActive);
        return ConversionDTO.transformToDTO(optionRepository.save(option));
    }
}
