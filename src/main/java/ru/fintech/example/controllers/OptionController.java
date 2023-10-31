package ru.fintech.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.OptionDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.UpdateOption;
import ru.fintech.example.service.OptionService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/option")
@Tag(name = "OptionController", description = "Методы для работы с опциями")
public class OptionController {

    private OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/add")
    @Operation(summary = "Создание новой опции")
    public ResponseEntity<OptionDTO> create(@RequestBody OptionDTO optionDTO) throws FaultException {
        log.info("Coming request {}", optionDTO);
        return ResponseEntity.ok(optionService.create(optionDTO));
    }

    @GetMapping("/active")
    @Operation(summary = "Получение доступных опций")
    public ResponseEntity<List<OptionDTO>> getAllActiveOption() {
        return ResponseEntity.ok(optionService.getAllByActive(true));
    }

    @GetMapping("/notActive")
    @Operation(summary = "Получение архивных опций")
    public ResponseEntity<List<OptionDTO>> getAllNotActiveOption() {
        return  ResponseEntity.ok(optionService.getAllByActive(false));
    }

    @PatchMapping("/{optionId}/changeActive")
    @Operation(summary = "Изменение статуса активности опции")
    public ResponseEntity<OptionDTO> changeActive(@PathVariable("optionId") int optionId,
                                                  @RequestParam(value = "newActive") boolean newActive) throws FaultException {
        return ResponseEntity.ok(optionService.changeActive(optionId, newActive));
    }

    @PatchMapping("/{optionId}/changeOptionToArchive")
    @Operation(summary = "Сделать опцию архивной")
    public void changeOptionToArchive(@PathVariable("optionId") int optionId) {
        optionService.changeOptionToArchive(optionId);
    }

    @PatchMapping("/{optionId}/changePricePerMouth")
    @Operation(summary = "Изменение абонентской платы")
    public void changePricePerMouth(@PathVariable("optionId") int optionId, @RequestParam(value = "pricePerMouth") int pricePerMouth) {
        optionService.changePricePerMouth(optionId, pricePerMouth);
    }

    @PutMapping("/updateOption")
    @Operation(summary = "Изменение пакета опции")
    public ResponseEntity<OptionDTO> updateOption(@RequestBody UpdateOption updateOption) {
        return ResponseEntity.ok(optionService.updateOption(
                updateOption.getOptionId(),
                updateOption.getPackageVoice(),
                updateOption.getPackageData(),
                updateOption.getPackageSms(),
                updateOption.getSpecCode()));
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
