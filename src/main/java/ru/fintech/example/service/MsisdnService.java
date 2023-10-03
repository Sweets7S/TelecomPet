package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

import static ru.fintech.example.service.UserService.technical;


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
    public List<MsisdnDTO> createMsisdn(List<MsisdnDTO> msisdnDTOs) {
        List<MsisdnDTO> msisdnDTOList = new ArrayList<>();
        List<Msisdn> msisdnList = ConversionDTO.transformToEntities(msisdnDTOs, userRepository.getReferenceById(technical));
        msisdnDTOList.clear();
        for (int i = 0; i < msisdnList.size(); i++) {
            msisdnDTOList.add(ConversionDTO.transformToDTO(msisdnRepository.save(msisdnList.get(i))));
            log.info("Count " + i);
        }
        return msisdnDTOList;
    }

    public List<MsisdnDTO> getAvailableMsisdns() {
        List<MsisdnDTO> msisdnDTOList = new ArrayList<>();
        List<Msisdn> msisdnList = userRepository.getReferenceById(technical).getMsisdns();
        for (int i = 0; i < msisdnList.size(); i++) {
            log.info("Count " + i);
            msisdnDTOList.add(ConversionDTO.transformToDTO(msisdnList.get(i)));
        }
        return msisdnDTOList;
    }
}
