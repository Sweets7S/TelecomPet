package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Sim;
import ru.fintech.example.models.UpdateUser;
import ru.fintech.example.models.User;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.repository.SimRepository;
import ru.fintech.example.repository.TariffRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    //    @Autowired
    private SimRepository simRepository;
    private TariffRepository tariffRepository;
    private OptionRepository optionRepository;
    protected static int technicalId = 1;

    public UserService(UserRepository userRepository, SimRepository simRepository) {
        this.userRepository = userRepository;
        this.simRepository = simRepository;
    }

    public UserDTO create(UserDTO userDTO) {
        User user = ConversionDTO.transformToEntity(userDTO);
        User userAfterSave = userRepository.save(user);
        return ConversionDTO.transformToDTO(userAfterSave);
    }

    public UserDTO get(int userId) {
        User user = userRepository.getReferenceById(userId);
        return ConversionDTO.transformToDTO(user);
    }

    public List<UserDTO> getAll() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            log.info("Count " + i);
            userDTOList.add(ConversionDTO.transformToDTO(userList.get(i)));
        }
        return userDTOList;
    }

    public void delete(int userId) {
        List<Sim> simList = userRepository.getReferenceById(userId).getSims();
        for (int i = 0; i < simList.size(); i++) {
            simList.get(i).setUser(userRepository.getReferenceById(technicalId));
            simRepository.save(simList.get(i));
        }
        userRepository.deleteById(userId);
    }

    public void update(UpdateUser updateUser) {
        User user = userRepository.getReferenceById(updateUser.getId());
        user.setLogin(updateUser.getLogin());
        user.setActive(updateUser.isActive());
        log.info(user.toString());
        userRepository.save(user);
    }

    private Sim changeUser(int msisdnId, int newUserId) {
        Sim sim = simRepository.getReferenceById(msisdnId);
        sim.setUser(userRepository.getReferenceById(newUserId));
        return simRepository.save(sim);
    }

    public SimDTO terminationContract(int msisdnId) {
        return ConversionDTO.transformToDTO(changeUser(msisdnId, technicalId));
    }

    public void msisdnRenewal(int oldUserId, int msisdnId, int newUserId) throws FaultException {
        Sim sim = simRepository.getReferenceById(msisdnId);
        if (sim.getUser().getId() != oldUserId) {
            throw new FaultException(1000, "User doesnt own the msisdnId: " + msisdnId);
        }
        if (!(simRepository.existsById(newUserId))){
            throw new FaultException(1003, "Такого пользователя не существует: " + newUserId);
        }
        changeUser(msisdnId, newUserId);
    }

    public SimDTO changeIcc(int msisdnId, String icc) {
        Sim sim = simRepository.getReferenceById(msisdnId);
        sim.setIcc(icc);
        return ConversionDTO.transformToDTO(simRepository.save(sim));
    }


    public SimDTO addMsisdnToUser(int newUserId, int msisdnId) throws FaultException {
        Sim sim = simRepository.getReferenceById(msisdnId);
        if (sim.getUser().getId() != technicalId) {
            throw new FaultException(1002, "Этого номера нет в списке доступных номеров - " + msisdnId);
        }
        sim.setUser(userRepository.getReferenceById(newUserId));
        return ConversionDTO.transformToDTO(simRepository.save(sim));
    }

    public SimDTO changeMsisdn(int userId, int oldMsisdnId, int newMsisdnId) throws FaultException {
        Sim oldSim = simRepository.getReferenceById(oldMsisdnId);
        Sim sim = null;
        if (oldSim.getUser().getId() == userId) {
            Sim newSim = simRepository.getReferenceById(newMsisdnId);
            String oldMsisdnNum = oldSim.getMsisdn();
            String newMsisdnNum = newSim.getMsisdn();
            newSim.setMsisdn("55555");
            simRepository.save(newSim);
            oldSim.setMsisdn(newMsisdnNum);
            sim = simRepository.save(oldSim);
            newSim.setMsisdn(oldMsisdnNum);
            simRepository.save(newSim);
        } else throw new FaultException(1000, "User doesnt own the msisdnId: " + oldMsisdnId);
        return ConversionDTO.transformToDTO(sim);
    }

    public UserDTO changePassport(int userId, String document, String fio) {
        User user = userRepository.getReferenceById(userId);
        user.setDocument(document);
        user.setFio(fio);
        return ConversionDTO.transformToDTO(userRepository.save(user));
    }

    public void changePassword(int userId, String password) {
        User user = userRepository.getReferenceById(userId);
        user.setPassword(password);
        ConversionDTO.transformToDTO(userRepository.save(user));
    }
}
