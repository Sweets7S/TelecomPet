package ru.fintech.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.UpdateUser;
import ru.fintech.example.models.User;
import ru.fintech.example.repository.MsisdnRepository;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    //    @Autowired
    private MsisdnRepository msisdnRepository;
    protected static int technicalUserId = 8;

    public UserService(UserRepository userRepository, MsisdnRepository msisdnRepository) {
        this.userRepository = userRepository;
        this.msisdnRepository = msisdnRepository;
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
        List<Msisdn> msisdnList = userRepository.getReferenceById(userId).getMsisdns();
        for (int i = 0; i < msisdnList.size(); i++) {
            msisdnList.get(i).setUser(userRepository.getReferenceById(technicalUserId));
            msisdnRepository.save(msisdnList.get(i));
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

    private Msisdn changeUser(int msisdnId, int newUserId) {
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        msisdn.setUser(userRepository.getReferenceById(newUserId));
        return msisdnRepository.save(msisdn);
    }

    public MsisdnDTO terminationContract(int msisdnId) {
        return ConversionDTO.transformToDTO(changeUser(msisdnId, technicalUserId));
    }

    public void msisdnRenewal(int oldUserId, int msisdnId, int newUserId) throws FaultException {
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        if (msisdn.getUser().getId() != oldUserId) {
            throw new FaultException(1000, "User doesnt own the msisdnId: " + msisdnId);
        }
        changeUser(msisdnId, newUserId);
    }

    public MsisdnDTO changeIcc(int msisdnId, String icc) {
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        msisdn.setIcc(icc);
        return ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
    }


    public MsisdnDTO addMsisdnToUser(int newUserId, int msisdnId) throws FaultException {
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        if (msisdn.getUser().getId() != technicalUserId){
            throw new FaultException(1002, "Этого номера нет в списке доступных номеров - " + msisdnId);
        }
        msisdn.setUser(userRepository.getReferenceById(newUserId));
        return ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
    }

    public MsisdnDTO changeMsisdn(int userId, int oldMsisdnId, int newMsisdnId) throws FaultException {
        Msisdn oldMsisdn = msisdnRepository.getReferenceById(oldMsisdnId);
        Msisdn msisdn = null;
        if (oldMsisdn.getUser().getId() == userId) {
            Msisdn newMsisdn = msisdnRepository.getReferenceById(newMsisdnId);
            String oldMsisdnNum = oldMsisdn.getMsisdn();
            String newMsisdnNum = newMsisdn.getMsisdn();
            newMsisdn.setMsisdn("55555");
            msisdnRepository.save(newMsisdn);
            oldMsisdn.setMsisdn(newMsisdnNum);
            msisdn = msisdnRepository.save(oldMsisdn);
            newMsisdn.setMsisdn(oldMsisdnNum);
            msisdnRepository.save(newMsisdn);
        } else throw new FaultException(1000, "User doesnt own the msisdnId: " + oldMsisdnId);
        return ConversionDTO.transformToDTO(msisdn);
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

//    public UserDTO contractWithMsisdn(UserDTO userDTO, int msisdnId) {
//        User user = null;
//        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
//        try {
//            user = userRepository.getReferenceById(userDTO.getId());
//            log.info(user.toString()); // если User не найден в базе, бросит EntityNotFoundException
//            msisdn.setUser(user);
//        } catch (EntityNotFoundException e) {
//            log.info("User with this id-{} not found", userDTO.getId());
//            user = ConversionDTO.transformToEntity(userDTO);
//            User userAfterSave = userRepository.save(user);
//            user = userAfterSave;
//            msisdn.setUser(user);
//        }
//        msisdnRepository.save(msisdn);
//        return ConversionDTO.transformToDTO(user);
//    }
}
