package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.TariffDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.UpdateTariff;
import ru.fintech.example.service.TariffService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tariff")
public class TariffController {

    private TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @PostMapping("/add")
    public ResponseEntity<TariffDTO> create(@RequestBody TariffDTO tariffDTO) throws FaultException {
        log.info("Coming request {}", tariffDTO);
        return ResponseEntity.ok(tariffService.create(tariffDTO));
    }

    @GetMapping("active")
    public ResponseEntity<List<TariffDTO>> getAllActiveTariff() {
        return ResponseEntity.ok(tariffService.getAllActiveTariff());
    }

    @GetMapping("notActive")
    public ResponseEntity<List<TariffDTO>> getAllNotActiveTariff() {
        return  ResponseEntity.ok(tariffService.getAllNotActiveTariff());
    }

    @PatchMapping("/{tariffId}/changePricePerMouth")
    public void changePricePerMouth(@PathVariable("tariffId") int tariffId, @RequestParam(value = "pricePerMouth") int pricePerMouth) {
        tariffService.changePricePerMouth(tariffId, pricePerMouth);
    }

    @PutMapping("/updateTariff")
    public ResponseEntity<TariffDTO> updateTariff(@RequestBody UpdateTariff updateTariff) {
        return ResponseEntity.ok(tariffService.updateTariff(
                updateTariff.getTariffId(),
                updateTariff.getPackageVoice(),
                updateTariff.getPackageData(),
                updateTariff.getPackageSms(),
                updateTariff.getPackageVoiceCountry()));
    }

    @PatchMapping("/{tariffId}/changeSpeedMax")
    public void changeSpeedMax(@PathVariable("tariffId") int tariffId, @RequestParam(value = "speedMax") int speedMax) {
        tariffService.changeSpeedMax(tariffId, speedMax);
    }

    @PatchMapping("/{tariffId}/changeTariffToArchive")
    public void changeTariffToArchive(@PathVariable("tariffId") int tariffId) {
        tariffService.changeTariffToArchive(tariffId);
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
