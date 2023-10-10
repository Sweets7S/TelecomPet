package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.Exceptions.FaultException;
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
    public ResponseEntity<OptionDTO> create(@RequestBody OptionDTO optionDTO) throws FaultException {
        log.info("Coming request {}", optionDTO);
        return ResponseEntity.ok(optionService.create(optionDTO));
    }

    @GetMapping("active")
    public ResponseEntity<List<OptionDTO>> getAllActiveOption() {
        return ResponseEntity.ok(optionService.getAllActiveOption());
    }

    @GetMapping("notActive")
    public ResponseEntity<List<OptionDTO>> getAllNotActiveOption() {
        return  ResponseEntity.ok(optionService.getAllNotActiveOption());
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
