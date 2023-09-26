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
    public void create(@RequestBody List<MsisdnDTO> msisdnDTOs) {
        log.info("Coming request {}", msisdnDTOs);
        msisdnService.createMsisdn(msisdnDTOs);
    }

    @GetMapping("")
    public List<MsisdnDTO> getAllMsisdns() {
        return msisdnService.getAllMsisdns();
    }
}
