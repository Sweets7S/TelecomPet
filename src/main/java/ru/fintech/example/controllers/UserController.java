package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.UpdateUser;
import ru.fintech.example.models.PassportData;
import ru.fintech.example.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        log.info("Coming request {}", userDTO);
        return userService.create(userDTO);
    }

    @PostMapping("/regestrationContract")
    public UserDTO regestrationContract(@RequestBody UserDTO userDTO, @RequestParam(value = "msisdnId") int msisdnId) throws FaultException {
        UserDTO userDTO1 = userService.create(userDTO);
        userService.addOneMoreNumber(userDTO1.getId(), msisdnId);
        log.info("Coming request {}", userDTO);
        return userService.get(userDTO1.getId());
    }

    @PostMapping("/addOneMoreNumber")
    public MsisdnDTO addAvailableNumber(@RequestParam(value = "userId") int userId, @RequestParam(value = "msisdnId") int msisdnId) throws FaultException {
        return userService.addOneMoreNumber(userId, msisdnId);
    }

    @PostMapping("/reRegestrationContract")
    public MsisdnDTO reRegestrationContract(@RequestParam(value = "oldUserId") int oldUserId, @RequestParam(value = "newUserId") int newUserId, @RequestParam(value = "msisdnId") int msisdnId) throws FaultException {
        return userService.reRegestrationContract(oldUserId, newUserId, msisdnId);
    }

    @GetMapping("/{userId}")
    public UserDTO get(@PathVariable("userId") int userId) {
        log.info("Coming id - {}", userId);
        return userService.get(userId);
    }

    @GetMapping("")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateUser updateUser) {
        log.info(updateUser.toString());
        userService.update(updateUser);
    }

    @PatchMapping("/{userId}/updatePassword")
    public void updatePassword(@PathVariable("userId") int userId, @RequestParam(value = "password") String userPassword) {
        userService.updatePassword(userId, userPassword);
    }

    @PatchMapping("/{msisdnId}/updateIcc")
    public void updateIcc(@PathVariable("msisdnId") int msisdnId, @RequestParam(value = "icc") String msisdnIcc) {
        userService.updateIcc(msisdnId, msisdnIcc);
    }

    @PatchMapping("/{msisdnId}/updateMsisdn")
    public void updateMsisdn(@PathVariable("msisdnId") int msisdnId, @RequestParam(value = "msisdn") String newMsisdn) {
        userService.updateMsisdn(msisdnId, newMsisdn);
    }

    @PutMapping("/updatePassportData")
    public void updatePassportData(@RequestBody PassportData passportData) {
        log.info(passportData.toString());
        userService.updatePassportData(passportData);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }

    @DeleteMapping("/deleteMsisdn/{msisdnId}")
    public void deleteMsisdn(@PathVariable("msisdnId") int msisdnId) {
        userService.deleteMsisdn(msisdnId);
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
