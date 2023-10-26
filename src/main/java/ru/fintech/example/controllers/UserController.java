package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.SimDTO;
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

    @PutMapping("/{simId}/termination")
    public ResponseEntity<SimDTO> terminationContract(@PathVariable("simId") int simId) {
        return ResponseEntity.ok(userService.terminationContract(simId));
    }

    @PutMapping("/renewal")
    public void simRenewal(@RequestBody Renewal renewal) throws FaultException {
        userService.simRenewal(renewal.getOldUserId(),
                renewal.getSimId(), renewal.getNewUserId());
    }

    @PatchMapping("/{simId}/change/icc")
    public ResponseEntity<SimDTO> changeIcc(@PathVariable("simId") int simId, @RequestParam(value = "icc") String icc) throws InterruptedException {
        return ResponseEntity.ok(userService.changeIcc(simId, icc));
    }

    @PostMapping("/{newUserId}/add/sim")
    public void addSimToUser(@PathVariable("newUserId") int newUserId,
                             @RequestParam(value = "simId") int simId,
                             @RequestParam(value = "tariffId") int tariffId) throws FaultException {
        userService.addSimToUser(newUserId, simId, tariffId);
    }

    @PatchMapping("/{userId}/change/msisdn")
    public ResponseEntity<SimDTO> changeMsisdn(@PathVariable("userId") int userId,
                                               @RequestParam(value = "oldSimId") int oldSimId,
                                               @RequestParam(value = "newSimId") int newSimId) throws FaultException {
        return ResponseEntity.ok(userService.changeMsisdn(userId, oldSimId, newSimId));
    }

    @PutMapping("/change/passport")
    public ResponseEntity<UserDTO> changePassport(@RequestBody UpdatePassport updatePassport) {
        return ResponseEntity.ok(userService.changePassport(
                updatePassport.getUserId(),
                updatePassport.getDocument(),
                updatePassport.getFio()));
    }

    @PatchMapping("/{userId}/change/password")
    public void changePassword(@PathVariable("userId") int userId, @RequestParam(value = "password") String password) {
        userService.changePassword(userId, password);
    }

    @PostMapping("/{simId}/{tariffId}/contract")
    public ResponseEntity<UserDTO> contractWithSim(@RequestBody UserDTO userDTO,
                                                   @PathVariable("simId") int simId,
                                                   @PathVariable("tariffId") int tariffId) throws FaultException {
        UserDTO userDTO1 = userService.create(userDTO);
        userService.addSimToUser(userDTO1.getId(), simId, tariffId);
        userService.addTariffToSim(simId, tariffId);
        UserDTO user = userService.get(userDTO1.getId());
        log.info(user.toString());
        return ResponseEntity.ok(user);
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
