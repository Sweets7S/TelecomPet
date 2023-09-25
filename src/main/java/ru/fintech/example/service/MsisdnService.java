package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.utils.ConversionDTO;

@Slf4j
@Service
public class MsisdnService {

    private MsisdnRepository msisdnRepository;

    public  MsisdnService(MsisdnRepository msisdnRepository){
        this.msisdnRepository = msisdnRepository;
    }

//    public MsisdnDTO create(MsisdnDTO msisdnDTO) {
//        Msisdn msisdn = ConversionDTO.transformToEntity(MsisdnDTO);
//        Msisdn msisdnAfterSave = msisdnRepository.save(msisdn);
//        return ConversionDTO.transformToDTO(msisdnAfterSave);
//    }
}
