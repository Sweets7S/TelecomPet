package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.repository.SimRepository;


@Slf4j
@Service
public class OptionService {
    private OptionRepository optionRepository;
    private SimRepository simRepository;

    public OptionService(OptionRepository optionRepository, SimRepository simRepository) {
        this.optionRepository = optionRepository;
        this.simRepository = simRepository;
    }

}
