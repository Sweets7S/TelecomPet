package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.models.UserEntity;

@Slf4j
public class ConversionDTO {

    public static TestEntity transformToTestEntity(TestDTO testDTO){
        TestEntity testEntity = new TestEntity();
        testEntity.setName(testDTO.getName());
        testEntity.setAge(testDTO.getAge());
        testEntity.setRch(testDTO.getRch());
        return testEntity;
    }

    public static TestDTO transformToTestDTO(TestEntity testEntity){
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setName(testEntity.getName());
        testDTO.setAge(testEntity.getAge());
        testDTO.setRch(testEntity.getRch());
        return testDTO;
    }

    public static UserEntity transformToUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFio(userDTO.getFio());
        userEntity.setDocument(userDTO.getDocument());
        userEntity.setNumber(userDTO.getNumber());
        userEntity.setActive(userDTO.getActive());
        userEntity.setIcc(userDTO.getIcc());
        return userEntity;
    }

    public static UserDTO transformToUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userEntity.setLogin(userEntity.getLogin());
        userEntity.setPassword(userEntity.getPassword());
        userEntity.setFio(userEntity.getFio());
        userEntity.setDocument(userEntity.getDocument());
        userEntity.setNumber(userEntity.getNumber());
        userEntity.setActive(userEntity.isActive());
        userEntity.setIcc(userEntity.getIcc());
        return userDTO;
    }
}
