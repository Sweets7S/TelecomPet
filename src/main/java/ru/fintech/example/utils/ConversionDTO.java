package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.models.User;

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

    public static User transformToEntity(UserDTO userDTO){
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFio(userDTO.getFio());
        user.setDocument(userDTO.getDocument());
        user.setNumber(userDTO.getNumber());
        user.setActive(userDTO.isActive());
        user.setIcc(userDTO.getIcc());
        return user;
    }

    public static UserDTO transformToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setFio(user.getFio());
        userDTO.setDocument(user.getDocument());
        userDTO.setNumber(user.getNumber());
        userDTO.setActive(user.isActive());
        userDTO.setIcc(user.getIcc());
        return userDTO;
    }
}
