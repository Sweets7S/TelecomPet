package ru.fintech.example.utils;

import lombok.extern.slf4j.Slf4j;
import ru.fintech.example.DTO.*;
import ru.fintech.example.models.*;

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

    public static List<Sim> transformToEntities(List<SimDTO> simDTOS,
                                                User user,
                                                Tariff tariff,
                                                Option option) {
        List<Sim> sims = new ArrayList<>();
        if (simDTOS != null) {
            for (int i = 0; i < simDTOS.size(); i++) {
                sims.add(transformToEntity(simDTOS.get(i), user, tariff, option));

            }
        }
        return sims;
    }

    public static SimDTO transformToDTO(Sim sim) {
        SimDTO simDTO = new SimDTO();
        simDTO.setSimId(sim.getSimId());
        simDTO.setUserId(sim.getUser().getId());
        simDTO.setMsisdn(sim.getMsisdn());
        simDTO.setIcc(sim.getIcc());
        simDTO.setActive(sim.isActive());
        simDTO.setTariffId(sim.getTariff().getTariffId());
        if (sim.getOption() == null) {
            sim.setOption(null);
        } else {
            simDTO.setOptionId(sim.getOption().getOptionId());
        }
        return simDTO;
    }

    public static Sim transformToEntity(SimDTO simDTO, User user,
                                        Tariff tariff,
                                        Option option) {
        Sim sim = new Sim();
        sim.setMsisdn(simDTO.getMsisdn());
        sim.setIcc(simDTO.getIcc());
        sim.setUser(user);
        sim.setActive(simDTO.isActive());
        sim.setSimId(simDTO.getSimId());
        sim.setTariff(tariff);
        sim.setOption(option);
        return sim;
    }

    public static TariffDTO transformToDTO(Tariff tariff) {
        TariffDTO tariffDTO = new TariffDTO();
        tariffDTO.setTariffId(tariff.getTariffId());
        tariffDTO.setName(tariff.getName());
        tariffDTO.setPricePerMonth(tariff.getPricePerMonth());
        tariffDTO.setPackageVoice(tariff.getPackageVoice());
        tariffDTO.setPackageData(tariff.getPackageData());
        tariffDTO.setPackageSms(tariff.getPackageSms());
        tariffDTO.setSpeedMax(tariff.getSpeedMax());
        tariffDTO.setPackageVoiceCountry(tariff.getPackageVoiceCountry());
        tariffDTO.setActive(tariff.isActive());
        return tariffDTO;
    }

    public static Tariff transformToEntity(TariffDTO tariffDTO) {
        Tariff tariff = new Tariff();
        tariff.setName(tariffDTO.getName());
        tariff.setPricePerMonth(tariffDTO.getPricePerMonth());
        tariff.setPackageVoice(tariffDTO.getPackageVoice());
        tariff.setPackageData(tariffDTO.getPackageData());
        tariff.setPackageSms(tariffDTO.getPackageSms());
        tariff.setSpeedMax(tariffDTO.getSpeedMax());
        tariff.setPackageVoiceCountry(tariffDTO.getPackageVoiceCountry());
        tariff.setActive(tariffDTO.isActive());
        return tariff;
    }

    public static OptionDTO transformToDTO(Option option) {
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.setOptionId(option.getOptionId());
        optionDTO.setName(option.getName());
        optionDTO.setPricePerMonth(option.getPricePerMonth());
        optionDTO.setPackageVoice(option.getPackageVoice());
        optionDTO.setPackageData(option.getPackageData());
        optionDTO.setPackageSms(option.getPackageSms());
        optionDTO.setSpecCode(option.getSpecCode());
        optionDTO.setActive(option.isActive());
        return optionDTO;
    }

    public static Option transformToEntity(OptionDTO optionDTO) {
        Option option = new Option();
        option.setName(optionDTO.getName());
        option.setPricePerMonth(optionDTO.getPricePerMonth());
        option.setPackageVoice(optionDTO.getPackageVoice());
        option.setPackageData(optionDTO.getPackageData());
        option.setPackageSms(optionDTO.getPackageSms());
        option.setSpecCode(optionDTO.getSpecCode());
        option.setActive(optionDTO.isActive());
        return option;
    }
}
