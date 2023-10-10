package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.UpdateOption;
import ru.fintech.example.models.UpdateTariff;
import ru.fintech.example.repository.OptionRepository;
import ru.fintech.example.service.OptionService;

import java.util.List;

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
    @GetMapping("/get")
    public ResponseEntity<List<OptionDTO>> getAllAvailableOptions() {
        return ResponseEntity.ok(optionService.getAllAvailableOptions());
    }
    @GetMapping("/get_archive")
    public ResponseEntity<List<OptionDTO>> getAllArchiveTariffs() {
        return ResponseEntity.ok(optionService.getAllArchiveOptions());
    }
    @PatchMapping("/{optionId}/change/status")
    public ResponseEntity<OptionDTO> changeStatus(@PathVariable("optionId") int optionId) throws FaultException {
        log.info("test");
        return ResponseEntity.ok(optionService.changeStatus(optionId));
    }
    @PatchMapping("/{optionId}/change/price")
    public ResponseEntity<OptionDTO> changePricePerMonth(@PathVariable("optionId") int optionId,
                                                         @RequestParam(value = "newPrice") int newPrice) {
        return ResponseEntity.ok(optionService.changePricePerMonth(optionId, newPrice));
    }
    @PutMapping("/update")
    public void updateOption(@RequestBody UpdateOption updateOption) throws FaultException {
        log.info(updateOption.toString());
        optionService.updateOption(updateOption);
    }

    @ExceptionHandler(FaultException.class)
    public ResponseEntity<String> handleFaultException(FaultException e) {
        return new ResponseEntity<String>(String.format("FaultCode: %s, Massage: %s", e.getFaultCode(), e.getMessage()), HttpStatusCode.valueOf(444));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(String.format("Massage: %s", e.getMessage()), HttpStatusCode.valueOf(500));
    }
}
