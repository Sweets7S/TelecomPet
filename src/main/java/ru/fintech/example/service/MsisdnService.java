package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;


@Slf4j
@Service
public class MsisdnService {

    private MsisdnRepository msisdnRepository;
    private UserRepository userRepository;

    public  MsisdnService(MsisdnRepository msisdnRepository, UserRepository userRepository){
        this.msisdnRepository = msisdnRepository;
        this.userRepository = userRepository;
    }

    public MsisdnDTO createMsisdn(MsisdnDTO msisdnDTO) {
            Msisdn msisdn = ConversionDTO.transformToEntity(msisdnDTO, userRepository.getReferenceById(8));
            Msisdn msisdnAfterSave = msisdnRepository.save(msisdn);
        return ConversionDTO.transformToDTO(msisdnAfterSave);
    }
}
