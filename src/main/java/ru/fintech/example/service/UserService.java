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

    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = ConversionDTO.transformToUserEntity(userDTO);
        return ConversionDTO.transformToUserDTO(userEntity);
    }

    public UserDTO getUser(int userId){
        UserEntity userEntity = userRepository.getReferenceById(userId);
        return ConversionDTO.transformToUserDTO(userEntity);
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findAll();
        for (int i = 0; i < userEntityList.size(); i++) {
            log.info("Count " + i);
            userDTOList.add(ConversionDTO.transformToUserDTO(userEntityList.get(i)));
        }
        return userDTOList;
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public UserDTO updateUser(UserDTO userDTO){
        UserEntity userEntity = userRepository.getReferenceById(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setRch(userDTO.getRch());
        log.info(userEntity.toString());
        return ConversionDTO.transformToUserDTO(userRepository.save(userEntity));
    }
}
