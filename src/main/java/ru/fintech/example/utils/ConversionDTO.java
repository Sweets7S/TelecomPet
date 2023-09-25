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
    public static List<MsisdnDTO> transformToDTOs(List<Msisdn> msisdn){
        List<MsisdnDTO> msisdnDTOS = new ArrayList<>();
        if (msisdn != null) {
            for (int i = 0; i < msisdn.size(); i++) {
                msisdnDTOS.add(transformToDTO(msisdn.get(i)));
            }
        }
        return msisdnDTOS;
    }

    public static Msisdn transformToEntity(MsisdnDTO msisdnDTO, User user) {
        Msisdn msisdn = new Msisdn();
        msisdn.setUser(user);
        msisdn.setMsisdnId(msisdnDTO.getMsisdnId());
        msisdn.setMsisdn(msisdnDTO.getMsisdn());
        msisdn.setIcc(msisdnDTO.getIcc());
        msisdn.setActive(msisdnDTO.isActive());
        return msisdn;
    }

    public static MsisdnDTO transformToDTO(Msisdn msisdn) {
        MsisdnDTO msisdnDTO = new MsisdnDTO();
//            .msisdnId(msisdn.get(i).getMsisdnId())
//            .userId(msisdn.get(i).getUser().getId())
//            .msisdn(msisdn.get(i).getMsisdn())
//            .icc(msisdn.get(i).getIcc())
//            .active(msisdn.get(i).isActive());
        msisdnDTO.setMsisdnId(msisdn.getMsisdnId());
        msisdnDTO.setUserId(msisdn.getUser().getId());
        msisdnDTO.setMsisdn(msisdn.getMsisdn());
        msisdnDTO.setIcc(msisdn.getIcc());
        msisdnDTO.setActive(msisdn.isActive());
        return msisdnDTO;
    }
}
