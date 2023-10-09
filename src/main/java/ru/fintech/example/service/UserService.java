package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Sim;
import ru.fintech.example.models.UpdateUser;
import ru.fintech.example.models.User;
import ru.fintech.example.repository.SimRepository;
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

    private Sim changeUser(int simId, int newUserId) {
        Sim sim = simRepository.getReferenceById(simId);
        sim.setUser(userRepository.getReferenceById(newUserId));
        return simRepository.save(sim);
    }

    public SimDTO terminationContract(int simId) {
        return ConversionDTO.transformToDTO(changeUser(simId, technicalId));
    }

    public void simRenewal(int oldUserId, int simId, int newUserId) throws FaultException {
        Sim sim = simRepository.getReferenceById(simId);
        if (sim.getUser().getId() != oldUserId) {
            log.info("1000: User doesn't own the simId: {}", simId);
            throw new FaultException(1000, "User doesn't own the simId: " + simId);
        }
        if (!(userRepository.existsById(newUserId))){
            log.info("1003: Такого пользователя не существует: {}", newUserId);
            throw new FaultException(1003, "Такого пользователя не существует: " + newUserId);
        }
        changeUser(simId, newUserId);
    }

    public SimDTO changeIcc(int simId, String icc) {
        Sim sim = simRepository.getReferenceById(simId);
        sim.setIcc(icc);
        return ConversionDTO.transformToDTO(simRepository.save(sim));
    }


    public SimDTO addSimToUser(int newUserId, int simId) throws FaultException {
        Sim sim = simRepository.getReferenceById(simId);
        if (sim.getUser().getId() != technicalId) {
            log.info("1002: Этого номера нет в списке доступных номеров - {}", simId);
            throw new FaultException(1002, "Этого номера нет в списке доступных номеров - " + simId);
        }
        sim.setUser(userRepository.getReferenceById(newUserId));
        return ConversionDTO.transformToDTO(simRepository.save(sim));
    }

    public SimDTO changeMsisdn(int userId, int oldSimId, int newSimId) throws FaultException {
        Sim oldSim = simRepository.getReferenceById(oldSimId);
        Sim sim = null;
        if (oldSim.getUser().getId() == userId) {
            Sim newSim = simRepository.getReferenceById(newSimId);
            String oldMsisdnNum = oldSim.getMsisdn();
            String newMsisdnNum = newSim.getMsisdn();
            newSim.setMsisdn("55555");
            simRepository.save(newSim);
            oldSim.setMsisdn(newMsisdnNum);
            sim = simRepository.save(oldSim);
            newSim.setMsisdn(oldMsisdnNum);
            simRepository.save(newSim);
        } else {log.info("1000: User doesn't own the simId: {}", oldSimId);
        throw new FaultException(1000, "User doesn't own the simId: " + oldSimId);}
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
