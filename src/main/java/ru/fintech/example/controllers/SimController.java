package ru.fintech.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.SimDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.service.SimService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sim")
@Tag(name = "SimController", description = "Методы для работы с сим-картами")
public class SimController {

    private SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление свободных номеров")
    public ResponseEntity<List<SimDTO>> addSimsToVacant(@RequestBody List<SimDTO> simDTOS) throws FaultException {
        return ResponseEntity.ok(simService.addSimsToVacant(simDTOS));
    }
    @GetMapping("/get")
    @Operation(summary = "Получение доступных номеров")
    public ResponseEntity<List<SimDTO>> getAllAvailableSims() {
        return ResponseEntity.ok(simService.getAllAvailableSims());
    }

    @PutMapping("/{simId}/tariffRenewal")
    @Operation(summary = "Смена тарифа")
    public void tariffRenewal(@PathVariable("simId") int simId,
                              @RequestParam(value = "tariffId") int tariffId) throws FaultException {
        simService.tariffRenewal(simId, tariffId);
    }

    @PutMapping("/{simId}/optionRenewal")
    @Operation(summary = "Смена опции")
    public void optionRenewal(@PathVariable("simId") int simId,
                              @RequestParam(value = "optionId") int optionId) throws FaultException {
        simService.optionRenewal(simId, optionId);
    }

    @PatchMapping("/{simId}/changeActive")
    @Operation(summary = "Смена статуса активности")
    public ResponseEntity<SimDTO> changeActive(@PathVariable("simId") int simId,
                                               @RequestParam(value = "newActive") boolean newActive) throws FaultException {
        return ResponseEntity.ok(simService.changeActive(simId, newActive));
    }

    @GetMapping("/getAllNotActiveSims")
    @Operation(summary = "Получение всех не активных сим-карт")
    public ResponseEntity<List<SimDTO>> getAllNotActiveSims() {
        return ResponseEntity.ok(simService.getAllNotActiveSims());
    }

    @ExceptionHandler(FaultException.class)
    public ResponseEntity<String> handleFaultException(FaultException e){
        return new ResponseEntity<String>(String.format("FaultCode: %s, Massage: %s", e.getFaultCode(), e.getMessage()), HttpStatusCode.valueOf(444));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<String>(String.format("Massage: %s", e.getMessage()), HttpStatusCode.valueOf(500));
    }
}
