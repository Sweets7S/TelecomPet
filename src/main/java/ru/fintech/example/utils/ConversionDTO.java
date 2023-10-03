package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.Sim;
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
        userDTO.setSimDTOS(transformToDTOs(user.getSims()));
        return userDTO;
    }

    public static List<SimDTO> transformToDTOs(List<Sim> sim) {
        List<SimDTO> simDTOS = new ArrayList<>();
        if (sim != null) {
            for (int i = 0; i < sim.size(); i++) {
                simDTOS.add(transformToDTO(sim.get(i)));
            }
        }
        return simDTOS;
    }
    public static List<Sim> transformToEntities(List<SimDTO> simDTOS, User user) {
        List<Sim> sims = new ArrayList<>();
        if (simDTOS != null) {
            for (int i = 0; i < simDTOS.size(); i++) {
                sims.add(transformToEntity(simDTOS.get(i), user));
            }
        }
        return sims;
    }

    public static SimDTO transformToDTO(Sim sim) {
        SimDTO simDTO = new SimDTO();
        simDTO.setUserId(sim.getUser().getId());
        simDTO.setSimId(sim.getSimId());
        simDTO.setIcc(sim.getIcc());
        simDTO.setActive(sim.isActive());
        simDTO.setMsisdn(sim.getMsisdn());
        return simDTO;
    }

    public static Sim transformToEntity(SimDTO simDTO, User user) {
        Sim sim = new Sim();
        sim.setMsisdn(simDTO.getMsisdn());
        sim.setIcc(simDTO.getIcc());
        sim.setUser(user);
        sim.setActive(simDTO.isActive());
        sim.setSimId(simDTO.getSimId());
        return sim;
    }
}
