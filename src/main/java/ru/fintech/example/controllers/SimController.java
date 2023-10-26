package ru.fintech.example.controllers;

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
public class SimController {

    private SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<SimDTO>> addSimsToVacant(@RequestBody List<SimDTO> simDTOS) throws FaultException {
        return ResponseEntity.ok(simService.addSimsToVacant(simDTOS));
    }
    @GetMapping("/get")
    public ResponseEntity<List<SimDTO>> getAllAvailableSims() {
        return ResponseEntity.ok(simService.getAllAvailableSims());
    }

    @PutMapping("/{simId}/tariffRenewal")
    public void tariffRenewal(@PathVariable("simId") int simId,
                              @RequestParam(value = "tariffId") int tariffId) throws FaultException {
        simService.tariffRenewal(simId, tariffId);
    }

    @PutMapping("/{simId}/optionRenewal")
    public void optionRenewal(@PathVariable("simId") int simId,
                              @RequestParam(value = "optionId") int optionId) throws FaultException {
        simService.optionRenewal(simId, optionId);
    }

    @PatchMapping("/{simId}/changeActive")
    public ResponseEntity<SimDTO> changeActive(@PathVariable("simId") int simId,
                                               @RequestParam(value = "newActive") boolean newActive) throws FaultException {
        return ResponseEntity.ok(simService.changeActive(simId, newActive));
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
