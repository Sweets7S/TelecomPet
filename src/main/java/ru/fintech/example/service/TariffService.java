package ru.fintech.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fintech.example.repository.SimRepository;
import ru.fintech.example.repository.TariffRepository;


@Slf4j
@Service
public class TariffService {
    private TariffRepository tariffRepository;
    private SimRepository simRepository;

    public TariffService(TariffRepository tariffRepository, SimRepository simRepository) {
        this.tariffRepository = tariffRepository;
        this.simRepository = simRepository;
    }

}
