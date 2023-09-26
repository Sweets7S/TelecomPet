package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
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

    @PostMapping("/addAvailableNumber")
    public MsisdnDTO addAvailableNumber(@RequestParam(value = "newUserId") int newUserId, @RequestParam(value = "msisdnId") int msisdnId) {
        return userService.addAvailableNumber(newUserId, msisdnId);
    }

//    @PostMapping("/addSim")
//    public UserDTO createSim(@RequestBody UserDTO userDTO) {
//        log.info("Coming request {}", userDTO);
//        return userService.create(userDTO);
//    }

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

//    @PutMapping("/updateMsisdn")
//    public UserDTO updateMsisdn(@RequestBody UserDTO userDTO) {
//        log.info(userDTO.toString());
//        return userService.updateMsisdn(userDTO);

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }
}
