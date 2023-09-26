package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.MsisdnDTO;
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
    public List<MsisdnDTO> addMsisdnToVacant(@RequestBody List<MsisdnDTO> msisdnDTOS) {
        return msisdnService.addMsisdnsToVacant(msisdnDTOS);
    }
    @GetMapping("/get")
    public List<MsisdnDTO> getAllAvailibleMsisdns() {
        return msisdnService.getAllAvailivbleMsisdns();
    }
}
