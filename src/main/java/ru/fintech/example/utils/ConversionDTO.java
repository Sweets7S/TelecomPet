package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.models.User;
import ru.fintech.example.repository.MsisdnRepository;

import java.util.ArrayList;
import java.util.List;

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
        user.setActive(userDTO.isActive());
        return user;
    }

    public static UserDTO transformToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setFio(user.getFio());
        userDTO.setDocument(user.getDocument());
        userDTO.setActive(user.isActive());
        userDTO.setMsisdnDTOS(transformToDTOs(user.getMsisdns()));
        return userDTO;
    }
    public static List<MsisdnDTO> transformToDTOs(List<Msisdn> msisdn){
        List<MsisdnDTO> msisdnDTOS = new ArrayList<>();
        if (msisdn != null) {
            for (int i = 0; i < msisdn.size(); i++) {
                MsisdnDTO msisdnDTO = new MsisdnDTO();
                msisdnDTO.setMsisdnId(msisdn.get(i).getMsisdnId());
                msisdnDTO.setUserId(msisdn.get(i).getUser().getId());
                msisdnDTO.setMsisdn(msisdn.get(i).getMsisdn());
                msisdnDTO.setIcc(msisdn.get(i).getIcc());
                msisdnDTO.setActive(msisdn.get(i).isActive());
                msisdnDTOS.add(msisdnDTO);
            }
        }
        return msisdnDTOS;
    }
}
