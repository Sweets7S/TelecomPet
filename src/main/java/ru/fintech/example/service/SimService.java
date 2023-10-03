package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Sim;
import ru.fintech.example.models.User;
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
public class SimService {
    private UserRepository userRepository;
    //    @Autowired
    private SimRepository simRepository;
    private TariffRepository tariffRepository;
    private OptionRepository optionRepository;

    public SimService(UserRepository userRepository, SimRepository simRepository) {
        this.userRepository = userRepository;
        this.simRepository = simRepository;
    }

    //    @Transactional аннтоация если будет ошибка, то тогда изменения не будут внесены
    public List<SimDTO> addMsisdnsToVacant(List<SimDTO> simDTOS) throws FaultException {
        List<Sim> sims = ConversionDTO.transformToEntities(simDTOS,
                userRepository.getReferenceById(technicalId));
        List<Sim> msisdnsResult = new ArrayList<>();
        for (int i = 0; i < sims.size(); i++) {
            try {
                msisdnsResult.add(simRepository.save(sims.get(i)));
            } catch (Throwable e) {
                log.info("Такой Msisdn уже существует - {} ", sims.get(i));
                throw new FaultException(1001, "Такой номер уже существует");
            }
        }
        return ConversionDTO.transformToDTOs(msisdnsResult);
    }

    public List<SimDTO> getAllAvailivbleMsisdns() {
        List<SimDTO> simDTOS = new ArrayList<>();
        User vacant = userRepository.getReferenceById(technicalId);
        List<Sim> simList = vacant.getSims();
        for (int i = 0; i < simList.size(); i++) {
            log.info("Count " + i);
            simDTOS.add(ConversionDTO.transformToDTO(simList.get(i)));
        }
        return simDTOS;
    }
}
