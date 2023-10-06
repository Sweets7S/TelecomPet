package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.service.OptionService;

@Slf4j
@RestController
@RequestMapping("/option")
public class OptionController {
    private OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/add")
    public ResponseEntity<OptionDTO> addTariff(@RequestBody OptionDTO optionDTO) throws FaultException {
        return ResponseEntity.ok(optionService.addOption(optionDTO));
    }
}
