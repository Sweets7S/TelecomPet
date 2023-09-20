package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.UserEntity;
import ru.fintech.example.repository.UserRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO userDTO){
        UserEntity userEntity = ConversionDTO.transformToUserEntity(userDTO);
        UserEntity userEntityAfterSave = userRepository.save(userEntity);
        return ConversionDTO.transformToUserDTO(userEntityAfterSave);
    }

    public UserDTO get(int userId){
        UserEntity userEntity = userRepository.getReferenceById(userId);
        return ConversionDTO.transformToUserDTO(userEntity);
    }

    public List<UserDTO> getAll(){
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findAll();
        for (int i = 0; i < userEntityList.size(); i++) {
            log.info("Count " + i);
            userDTOList.add(ConversionDTO.transformToUserDTO(userEntityList.get(i)));
        }
        return userDTOList;
    }

    public void delete(int userId){
        userRepository.deleteById(userId);
    }

    public UserDTO update(UserDTO userDTO){
        UserEntity userEntity = userRepository.getReferenceById(userDTO.getId());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFio(userDTO.getFio());
        userEntity.setDocument(userDTO.getDocument());
        userEntity.setNumber(userDTO.getNumber());
        userEntity.setActive(userDTO.getActive());
        userEntity.setIcc(userDTO.getIcc());
        log.info(userEntity.toString());
        return ConversionDTO.transformToUserDTO(userRepository.save(userEntity));
    }
}
