package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.service.SimService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sim")
public class SimController {

    private SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<SimDTO>> addSimToVacant(@RequestBody List<SimDTO> simDTOS) throws FaultException {
        return ResponseEntity.ok(simService.addSimsToVacant(simDTOS));
    }

    @GetMapping("/get")
    public ResponseEntity<List<SimDTO>> getAllAvailableSims() {
        return ResponseEntity.ok(simService.getAllAvailableSims());
    }

    @PatchMapping("/{simId}/change/tariff")
    public void changeTariff(@PathVariable("simId") int simId,
                             @RequestParam(value = "newTariffId") int newTariffId) throws FaultException {
        simService.changeTariff(simId, newTariffId);
    }
    @PatchMapping("/{simId}/change/option")
    public void changeOption(@PathVariable("simId") int simId,
                             @RequestParam(value = "newOptionId") int newOptionId) throws FaultException {
        simService.changeOption(simId, newOptionId);
    }
    @PatchMapping("/{simId}/change/status")
    public ResponseEntity<SimDTO> changeStatus(@PathVariable("simId") int simId,
                                                  @RequestParam(value = "newStatus") boolean newStatus) throws FaultException {
        return ResponseEntity.ok(simService.changeStatus(simId, newStatus));
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
