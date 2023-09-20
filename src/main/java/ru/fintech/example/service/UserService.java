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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO userDTO){
        UserEntity entity = ConversionDTO.transformToEntity(userDTO);
        UserEntity entityAfterSave = userRepository.save(entity);
        UserDTO dto = ConversionDTO.transformToDTO(entityAfterSave);
        return dto;
    }


    public UserDTO get(int userId) {
        UserEntity userEntity = userRepository.getReferenceById(userId);
        return ConversionDTO.transformToDTO(userEntity);
    }


    public List<UserDTO> getAll() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findAll();
        for (int i = 0; i < userEntityList.size(); i++) {
            log.info("Count " + i);
            userDTOList.add(ConversionDTO.transformToDTO(userEntityList.get(i)));
        }
        return userDTOList;
    }


    public void delete(int userId) {
        log.info("Deleted element with id - " + userId);
        userRepository.deleteById(userId);
    }

    public UserDTO update(UserDTO userDTO) {
        UserEntity userEntity = userRepository.getReferenceById(userDTO.getId());
//        log.info(userEntity.toString());
        userEntity.setName(userDTO.getName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setRch(userDTO.getRch());
        log.info(userEntity.toString());
        return ConversionDTO.transformToDTO(userRepository.save(userEntity));
    }
}