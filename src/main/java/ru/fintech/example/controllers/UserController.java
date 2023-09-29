package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.models.*;
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
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        log.info("Coming request {}", userDTO);
        return ResponseEntity.ok(userService.create(userDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> get(@PathVariable("userId") int userId) {
        log.info("Coming id - {}", userId);
        return ResponseEntity.ok(userService.get(userId));
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateUser updateUser) {
        log.info(updateUser.toString());
        userService.update(updateUser);
    }


    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }

    @PutMapping("/{msisdnId}/termination")
    public ResponseEntity<MsisdnDTO> terminationContract(@PathVariable("msisdnId") int msisdnId) {
        return ResponseEntity.ok(userService.terminationContract(msisdnId));
    }

    @PutMapping("/renewal")
    public void msisdnRenewal(@RequestBody Renewal renewal) {
        userService.msisdnRenewal(renewal.getOldUserId(),
                renewal.getMsisdnId(), renewal.getNewUserId());
    }

    @PatchMapping("/{msisdnId}/change/icc")
    public ResponseEntity<MsisdnDTO> changeIcc(@PathVariable("msisdnId") int msisdnId, @RequestParam(value = "icc") String icc) {
        return ResponseEntity.ok(userService.changeIcc(msisdnId, icc));
    }

    @PostMapping("/add/msisdn")
    public void addMsisdnToUser(@RequestBody MsisdnDTO msisdnDTO) {
        userService.addMsisdnToUser(msisdnDTO);
    }

    @PatchMapping("/{userId}/change/msisdn")
    public ResponseEntity<MsisdnDTO> msisdnChange(@PathVariable("userId") int userId,
                                  @RequestParam(value = "oldMsisdnId") int oldMsisdnId,
                                  @RequestParam(value = "newMsisdnId") int newMsisdnId) throws FaultException {
        return ResponseEntity.ok(userService.changeMsisdn(userId, oldMsisdnId, newMsisdnId));
    }

    @PutMapping("/change/passport")
    public ResponseEntity<UserDTO> changePassport(@RequestBody UpdatePassport updatePassport) {
        return ResponseEntity.ok(userService.changePassport(updatePassport.getUserId(),
                updatePassport.getDocument(), updatePassport.getFio()));
    }

    @PatchMapping("/{userId}/change/password")
    public void changePassword(@PathVariable("userId") int userId, @RequestParam(value = "password") String password) {
        userService.changePassword(userId, password);
    }

    @PostMapping("/{msisdnId}/contract")
    public ResponseEntity<UserDTO> contractWithMsisdn(@RequestBody UserDTO userDTO,
                                      @PathVariable("msisdnId") int msisdnId) {
        return ResponseEntity.ok(userService.contractWithMsisdn(userDTO, msisdnId));
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
