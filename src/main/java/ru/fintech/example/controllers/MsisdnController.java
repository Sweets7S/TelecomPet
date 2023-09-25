package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.service.MsisdnService;

@Slf4j
@RestController
@RequestMapping("/msisdn")
public class MsisdnController {

    private MsisdnService msisdnService;

    public MsisdnController(MsisdnService msisdnService) {
        this.msisdnService = msisdnService;
    }

//    @PostMapping("/add")
//    public MsisdnDTO create(@RequestBody MsisdnDTO msisdnDTO) {
//        log.info("Coming request {}", msisdnDTO);
//        return msisdnService.create(msisdnDTO);
//    }
}
