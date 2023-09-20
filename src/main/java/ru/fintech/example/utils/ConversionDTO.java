package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.models.UserEntity;

@Slf4j
public class ConversionDTO {

    public static TestEntity transformToEntity(TestDTO testDTO){
        TestEntity testEntity = new TestEntity();
        testEntity.setName(testDTO.getName());
        testEntity.setAge(testDTO.getAge());
        testEntity.setRch(testDTO.getRch());
        return testEntity;
    }

    public static TestDTO transformToDTO(TestEntity testEntity){
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setName(testEntity.getName());
        testDTO.setAge(testEntity.getAge());
        testDTO.setRch(testEntity.getRch());
        return testDTO;
    }

    public static UserEntity transformToEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFio(userDTO.getFio());
        userEntity.setDocument(userDTO.getDocument());
        userEntity.setNumber(userDTO.getNumber());
        userEntity.setActive(userDTO.isActive());
        userEntity.setIcc(userDTO.getIcc());
        return userEntity;
    }

    public static UserDTO transformToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFio(userEntity.getFio());
        userDTO.setDocument(userEntity.getDocument());
        userDTO.setNumber(userEntity.getNumber());
        userDTO.setActive(userEntity.isActive());
        userDTO.setIcc(userEntity.getIcc());
        return userDTO;
    }
}
