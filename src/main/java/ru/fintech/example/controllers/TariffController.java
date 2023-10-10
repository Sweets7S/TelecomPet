package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.SimDTO;
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
    public ResponseEntity<TariffDTO> addTariff(@RequestBody TariffDTO tariffDTO) throws FaultException {
        return ResponseEntity.ok(tariffService.addTariff(tariffDTO));
    }

    @GetMapping("/get")
    public ResponseEntity<List<TariffDTO>> getAllAvailableTariffs() {
        return ResponseEntity.ok(tariffService.getAllAvailableTariffs());
    }

    @GetMapping("/get_archive")
    public ResponseEntity<List<TariffDTO>> getAllArchiveTariffs() {
        return ResponseEntity.ok(tariffService.getAllArchiveTariffs());
    }

    @PatchMapping("/{tariffId}/change/price")
    public ResponseEntity<TariffDTO> changePricePerMonth(@PathVariable("tariffId") int tariffId,
                                                         @RequestParam(value = "newPrice") int newPrice) {
        return ResponseEntity.ok(tariffService.changePricePerMonth(tariffId, newPrice));
    }

    @PutMapping("/update")
    public void updateTariff(@RequestBody UpdateTariff updateTariff) throws FaultException {
        log.info(updateTariff.toString());
        tariffService.updateTariff(updateTariff);
    }

    @PatchMapping("/{tariffId}/change/speed/max")
    public ResponseEntity<TariffDTO> changeSpeedMax(@PathVariable("tariffId") int tariffId,
                                                    @RequestParam(value = "newMaxSpeed") int newMaxSpeed) {
        return ResponseEntity.ok(tariffService.changeSpeedMax(tariffId, newMaxSpeed));
    }

    @PatchMapping("/{tariffId}/change/status")
    public ResponseEntity<TariffDTO> changeStatus(@PathVariable("tariffId") int tariffId) throws FaultException {
        return ResponseEntity.ok(tariffService.changeStatusToArchive(tariffId));
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
