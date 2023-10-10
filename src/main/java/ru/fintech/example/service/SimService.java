package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Sim;
import ru.fintech.example.models.Tariff;
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

    public SimService(UserRepository userRepository, SimRepository simRepository,
                      TariffRepository tariffRepository, OptionRepository optionRepository) {
        this.userRepository = userRepository;
        this.simRepository = simRepository;
        this.tariffRepository = tariffRepository;
        this.optionRepository = optionRepository;
    }

    //    @Transactional аннтоация если будет ошибка, то тогда изменения не будут внесены
    public List<SimDTO> addSimsToVacant(List<SimDTO> simDTOS) throws FaultException {
        List<Sim> sims = ConversionDTO.transformToEntities(simDTOS,
                userRepository.getReferenceById(technicalId),
                tariffRepository.getReferenceById(technicalId),
                optionRepository.getReferenceById(technicalId));
        List<Sim> simsResult = new ArrayList<>();
        for (int i = 0; i < sims.size(); i++) {
            try {
                simsResult.add(simRepository.save(sims.get(i)));
            } catch (Throwable e) {
                log.info(1001 + "Такая Sim уже существует - {} ", sims.get(i));
                throw new FaultException(1001, "Такой номер уже существует: " + sims.get(i));
            }
        }
        for (int i = 0; i < simsResult.size(); i++) {
            simsResult.get(i).setTariff(tariffRepository.getReferenceById(technicalId));
            simsResult.get(i).setOption(null);
            simRepository.save(simsResult.get(i));
        }

        return ConversionDTO.transformToDTOs(simsResult);
    }

    public List<SimDTO> getAllAvailableSims() {
        List<SimDTO> simDTOS = new ArrayList<>();
        User vacant = userRepository.getReferenceById(technicalId);
        List<Sim> simList = vacant.getSims();
        for (int i = 0; i < simList.size(); i++) {
            log.info("Count " + i);
            simDTOS.add(ConversionDTO.transformToDTO(simList.get(i)));
        }
        return simDTOS;
    }

    public void changeTariff(int simId, int newTariffId) throws FaultException {
        //Only active
        Sim sim = simRepository.getReferenceById(simId);
        if (!(tariffRepository.existsById(newTariffId))) {
            log.info(1007 + "Данный тариф не существует: " + newTariffId);
            throw new FaultException(1007, "Данный тариф не существует - " + newTariffId);
        }
        if (!tariffRepository.getReferenceById(newTariffId).isActive()) {
            log.info(1004 + "Данный тариф архивный: " + newTariffId);
            throw new FaultException(1004, "Данный тариф архивный - " + newTariffId);
        }
        sim.setTariff(tariffRepository.getReferenceById(newTariffId));
        simRepository.save(sim);
    }
    public void changeOption(int simId, int newOptionId) throws FaultException {
        Sim sim = simRepository.getReferenceById(simId);
        if (!optionRepository.existsById(newOptionId)) {
            log.info(1009 + "Данная опция не существует - " + newOptionId);
            throw new FaultException(1009, "Данная опция не существует - " + newOptionId);
        }
        if (!tariffRepository.getReferenceById(newOptionId).isActive()) {
            log.info(1010 + "Данная опция архивная - " + newOptionId);
            throw new FaultException(1010, "Данная опция архивная - " + newOptionId);
            //Работал наоборот с архивными опциями
        }
        sim.setOption(optionRepository.getReferenceById(newOptionId));
        simRepository.save(sim);
    }
    //Добавить метод измения у номера active на true/false
    // (если sim.active уже с этим значением вернуть ошибку 1005)
    public SimDTO changeStatus(int simId, boolean newStatus) throws FaultException {
        Sim sim = simRepository.getReferenceById(simId);
        if (sim.isActive() == newStatus){
            log.info(1005 + "Sim уже с этим значением - " + newStatus);
            throw new FaultException(1005, "Sim уже с этим значением - " + newStatus);
        }
        sim.setActive(newStatus);
        return ConversionDTO.transformToDTO(simRepository.save(sim));
    }
}
