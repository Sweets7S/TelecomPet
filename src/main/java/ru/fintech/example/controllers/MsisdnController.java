package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.service.MsisdnService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/msisdn")
public class MsisdnController {

    private MsisdnService msisdnService;

    public MsisdnController(MsisdnService msisdnService) {
        this.msisdnService = msisdnService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<MsisdnDTO>> addMsisdnToVacant(@RequestBody List<MsisdnDTO> msisdnDTOS) throws FaultException {
        return ResponseEntity.ok(msisdnService.addMsisdnsToVacant(msisdnDTOS));
    }
    @GetMapping("/get")
    public ResponseEntity<List<MsisdnDTO>> getAllAvailibleMsisdns() {
        return ResponseEntity.ok(msisdnService.getAllAvailivbleMsisdns());
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
