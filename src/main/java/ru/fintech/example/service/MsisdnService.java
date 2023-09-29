package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.User;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

import static ru.fintech.example.service.UserService.technicalUserId;

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
    public List<MsisdnDTO> addMsisdnsToVacant(List<MsisdnDTO> msisdnDTOS) throws FaultException {
        List<Msisdn> msisdns = ConversionDTO.transformToEntities(msisdnDTOS,
                userRepository.getReferenceById(technicalUserId));
        List<Msisdn> msisdnsResult = new ArrayList<>();
        for (int i = 0; i < msisdns.size(); i++) {
            try {
                msisdnsResult.add(msisdnRepository.save(msisdns.get(i)));
            } catch (Throwable e) {
                log.info("Такой Msisdn уже существует - {} ", msisdns.get(i));
                throw new FaultException(1001, "Такой номер уже существует");
            }
        }
        return ConversionDTO.transformToDTOs(msisdnsResult);
    }

    public List<MsisdnDTO> getAllAvailivbleMsisdns() {
        List<MsisdnDTO> msisdnDTOS = new ArrayList<>();
        User vacant = userRepository.getReferenceById(technicalUserId);
        List<Msisdn> msisdnList = vacant.getMsisdns();
        for (int i = 0; i < msisdnList.size(); i++) {
            log.info("Count " + i);
            msisdnDTOS.add(ConversionDTO.transformToDTO(msisdnList.get(i)));
        }
        return msisdnDTOS;
    }
}
