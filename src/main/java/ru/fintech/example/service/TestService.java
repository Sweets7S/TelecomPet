package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.repository.TestRepository;
import ru.fintech.example.utils.ConversionDTO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TestService {

    private TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public TestDTO create(TestDTO testDTO){
        TestEntity entity = ConversionDTO.transformToEntity(testDTO);
        TestEntity entityAfterSave = testRepository.save(entity);
        TestDTO dto = ConversionDTO.transformToDTO(entityAfterSave);
        return dto;
    }


    public TestDTO get(int testId) throws Exception {
        TestEntity testEntity = testRepository.getReferenceById(testId);
        if(testId == 1) throw new FaultException(123, "Пользователя с таким Id не существует");
        return ConversionDTO.transformToDTO(testEntity);
    }


    public List<TestDTO> getAll() {
        List<TestDTO> testDTOList = new ArrayList<>();
        List<TestEntity> testEntityList = testRepository.findAll();
        for (int i = 0; i < testEntityList.size(); i++) {
            log.info("Count " + i);
            testDTOList.add(ConversionDTO.transformToDTO(testEntityList.get(i)));
        }
            return testDTOList;
    }


    public void delete(int testId) {
        log.info("Deleted element with id - " + testId);
        testRepository.deleteById(testId);
    }

    public TestDTO update(TestDTO testDTO) {
        TestEntity testEntity = testRepository.getReferenceById(testDTO.getId());
//        log.info(testEntity.toString());
        testEntity.setName(testDTO.getName());
        testEntity.setAge(testDTO.getAge());
        testEntity.setRch(testDTO.getRch());
        log.info(testEntity.toString());
        return ConversionDTO.transformToDTO(testRepository.save(testEntity));
    }
}