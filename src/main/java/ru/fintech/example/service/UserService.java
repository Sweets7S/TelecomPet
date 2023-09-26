package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
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
            msisdnList.get(i).setUser(userRepository.getReferenceById(8));
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

    public void terminationContract(int userID) {
        //same as delete method?
    }

    public void msisdnRenewal(int oldUserId, int msisdnId, int newUserId) {
        List<Msisdn> msisdnList = userRepository.getReferenceById(oldUserId).getMsisdns();
        Msisdn msisdn = msisdnRepository.getReferenceById(msisdnId);
        msisdn.setUser(userRepository.getReferenceById(newUserId));
        msisdnRepository.save(msisdn);
    }

    public MsisdnDTO changeIcc(int userId, String icc) {
        Msisdn msisdn = msisdnRepository.getReferenceById(userId);
        msisdn.setIcc(icc);
        return ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
    }


    public MsisdnDTO addMsisdnToUser(MsisdnDTO msisdnDTO) {
        Msisdn msisdn = ConversionDTO.transformToEntity(msisdnDTO,
                userRepository.getReferenceById(msisdnDTO.getUserId()));
        Msisdn msisdnAutoSave = msisdnRepository.save(msisdn);
        return ConversionDTO.transformToDTO(msisdnAutoSave);
    }


    public MsisdnDTO changeMsisdn(int userId, String msisdnCh) {
        Msisdn msisdn = msisdnRepository.getReferenceById(userId);
        msisdn.setMsisdn(msisdnCh);
        return ConversionDTO.transformToDTO(msisdnRepository.save(msisdn));
    }

    public UserDTO changePassportData(UserDTO userDTO) {
        User user = userRepository.getReferenceById(userDTO.getId());
        user.setDocument(userDTO.getDocument());
        user.setFio(userDTO.getFio());
        return ConversionDTO.transformToDTO(userRepository.save(user));
    }

    public UserDTO changePassword(int userId, String password) {
        User user = userRepository.getReferenceById(userId);
        user.setPassword(password);
        return ConversionDTO.transformToDTO(userRepository.save(user));
    }

    public UserDTO contractWithMsisdn(UserDTO userDTO, Msisdn msisdn) {
        User user = ConversionDTO.transformToEntity(userDTO);
        msisdn.setUser(userRepository.getReferenceById(user.getId()));
        msisdnRepository.save(msisdn);
        User userAfterSave = userRepository.save(user);
        return ConversionDTO.transformToDTO(userAfterSave);
    }
}
