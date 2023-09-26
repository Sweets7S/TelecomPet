package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.User;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MsisdnService {
    private UserRepository userRepository;
    //    @Autowired
    private MsisdnRepository msisdnRepository;

    public MsisdnService(UserRepository userRepository, MsisdnRepository msisdnRepository) {
        this.userRepository = userRepository;
        this.msisdnRepository = msisdnRepository;
    }

    //    @Transactional аннтоация если будет ошибка, то тогда изменения не будут внесены
    public List<MsisdnDTO> addMsisdnsToVacant(List<MsisdnDTO> msisdnDTOS) {
        List<Msisdn> msisdns = ConversionDTO.transformToEntities(msisdnDTOS,
                userRepository.getReferenceById(8));
        List<Msisdn> msisdnsResult = new ArrayList<>();
        for (int i = 0; i < msisdns.size(); i++) {
            msisdnsResult.add(msisdnRepository.save(msisdns.get(i)));
        }
        return ConversionDTO.transformToDTOs(msisdnsResult);
    }

    public List<MsisdnDTO> getAllAvailivbleMsisdns() {
        List<MsisdnDTO> msisdnDTOS = new ArrayList<>();
        User vacant = userRepository.getReferenceById(8);
        List<Msisdn> msisdnList = vacant.getMsisdns();
        for (int i = 0; i < msisdnList.size(); i++) {
            log.info("Count " + i);
            msisdnDTOS.add(ConversionDTO.transformToDTO(msisdnList.get(i)));
        }
        return msisdnDTOS;
    }
}
