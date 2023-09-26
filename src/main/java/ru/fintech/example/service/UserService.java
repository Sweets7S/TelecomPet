package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.UpdateUser;
import ru.fintech.example.models.PassportData;
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

    public UserService(UserRepository userRepository, MsisdnRepository msisdnRepository) {
        this.userRepository = userRepository;
        this.msisdnRepository = msisdnRepository;
    }

    public UserDTO create(UserDTO userDTO) {
        User user = ConversionDTO.transformToEntity(userDTO);
        User userAfterSave = userRepository.save(user);
        return ConversionDTO.transformToDTO(userAfterSave);
    }

//    public UserDTO createSim(UserDTO userDTO) {
//
//        return UserDTO;
//    }

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
            msisdnList.get(i).setUser(userRepository.getReferenceById(8));
            msisdnRepository.save(msisdnList.get(i));
        }
        userRepository.deleteById(userId);
    }

    public void update(UpdateUser updateUser) {
        User user = userRepository.getReferenceById(updateUser.getId());
        log.info(updateUser.toString());
        user.setLogin(updateUser.getLogin());
        user.setActive(updateUser.isActive());
        userRepository.save(user);
    }

    public void updatePassword(int id, String password) {
        User user = userRepository.getReferenceById(id);
        log.info(password);
        user.setPassword(password);
        ConversionDTO.transformToDTO(userRepository.save(user));
    }

    public void updatePassportData(PassportData passportData) {
        User user = userRepository.getReferenceById(passportData.getId());
        log.info(passportData.toString());
        user.setFio(passportData.getFio());
        user.setDocument(passportData.getDocument());
        User userAfterSave = userRepository.save(user);
        userRepository.save(userAfterSave);
    }

    public void updateIcc(int msisdnId, String msisdnIcc) {
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        log.info(msisdnIcc);
        msisdn.setIcc(msisdnIcc);
        ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
    }

    public void updateMsisdn(int msisdnId, String newMsisdn) {
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        log.info(newMsisdn);
        msisdn.setMsisdn(newMsisdn);
        ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
    }

    public MsisdnDTO addAvailableNumber(int newUserId, int msisdnId) {
        MsisdnDTO msisdnDTO = new MsisdnDTO();
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        if (8 == msisdn.getUser().getId()) {
            msisdn.setUser(userRepository.getReferenceById(newUserId));
            msisdnDTO = ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
        } else {
            log.info("Number is already used, UserId - {}", msisdn.getUser().getId());
        }
        return msisdnDTO;
    }

    public MsisdnDTO renuvalNumberToUser(int oldUserId, int newUserId, int msisdnId) {
        MsisdnDTO msisdnDTO = new MsisdnDTO();
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        if (oldUserId == msisdn.getUser().getId()) {
            msisdn.setUser(userRepository.getReferenceById(newUserId));
            msisdnDTO = ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
        }
        return msisdnDTO;
    }
}
