package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.models.TestEntity;
import ru.fintech.example.repository.TestRepository;
import ru.fintech.example.utils.ConversionDTO;

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


    public TestDTO get(int testId) {
        TestEntity testEntity = testRepository.getReferenceById(testId);
        return ConversionDTO.transformToDTO(testEntity);
    }
}
