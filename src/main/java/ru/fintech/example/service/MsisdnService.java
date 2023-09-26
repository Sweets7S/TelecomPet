package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class MsisdnService {

    private MsisdnRepository msisdnRepository;
    private UserRepository userRepository;

    public MsisdnService(MsisdnRepository msisdnRepository, UserRepository userRepository) {
        this.msisdnRepository = msisdnRepository;
        this.userRepository = userRepository;
    }
    //Todo возвращать List DTO
//    @Transactional // Если не все, то откатит до изначального состояния
    public void createMsisdn(List<MsisdnDTO> msisdnDTOs) {
        List<Msisdn> msisdnList = ConversionDTO.transformToEntities(msisdnDTOs, userRepository.getReferenceById(8));
        for (int i = 0; i < msisdnList.size(); i++) {
            msisdnRepository.save(msisdnList.get(i));
            log.info("Count " + i);
        }
    }

    public List<MsisdnDTO> getAllMsisdns() {
        List<MsisdnDTO> msisdnDTOList = new ArrayList<>();
        List<Msisdn> msisdnList = userRepository.getReferenceById(8).getMsisdns();
        for (int i = 0; i < msisdnList.size(); i++) {
            log.info("Count " + i);
            msisdnDTOList.add(ConversionDTO.transformToDTO(msisdnList.get(i)));
        }
        return msisdnDTOList;
    }
}
