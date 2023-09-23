package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.models.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ConversionDTO {

    public static TestEntity transformToEntity(TestDTO testDTO) {
        TestEntity testEntity = new TestEntity();
        testEntity.setName(testDTO.getName());
        testEntity.setAge(testDTO.getAge());
        testEntity.setRch(testDTO.getRch());
        return testEntity;
    }

    public static TestDTO transformToDTO(TestEntity testEntity) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setName(testEntity.getName());
        testDTO.setAge(testEntity.getAge());
        testDTO.setRch(testEntity.getRch());
        return testDTO;
    }

    public static User transformToEntity(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFio(userDTO.getFio());
        user.setDocument(userDTO.getDocument());
//        user.setNumber(userDTO.getNumber());
        user.setActive(userDTO.isActive());
//        user.setIcc(userDTO.getIcc());
        return user;
    }

    //    public static List<Msisdn> transformToEntities(List<MsisdnDTO> msisdnDTOS){
//        List<Msisdn> msisdnList = new ArrayList<>();
//        for (int i = 0; i < msisdnDTOS.size(); i++) {
//            Msisdn msisdn = new Msisdn();
//            msisdn.setMsisdnId(msisdnDTOS.get(i).getMsisdnId());
//            msisdn.setMsisdn(msisdnDTOS.get(i).getMsisdn());
//            msisdn.setIcc(msisdnDTOS.get(i).getIcc());
//            msisdn.setActive(msisdnDTOS.get(i).isActive());
//            msisdn.setUser(msisdnDTOS.get(i).getUserId());
//        }
//    }
    public static List<MsisdnDTO> transformToDTOs(List<Msisdn> msisdns) {

        List<MsisdnDTO> msisdnDTOList = new ArrayList<>();
        if (msisdns != null) {
            for (int i = 0; i < msisdns.size(); i++) {
                MsisdnDTO msisdnDTO = new MsisdnDTO();
                msisdnDTO.setMsisdnId(msisdns.get(i).getMsisdnId());
                msisdnDTO.setIcc(msisdns.get(i).getIcc());
                msisdnDTO.setActive(msisdns.get(i).isActive());
                msisdnDTO.setMsisdn(msisdns.get(i).getMsisdn());
                msisdnDTO.setUserId(msisdns.get(i).getUser().getId());
                msisdnDTOList.add(msisdnDTO);
            }
        }
        return msisdnDTOList;
    }

    public static UserDTO transformToDTO(User user) {
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
}
