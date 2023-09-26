package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.MsisdnDTO;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.models.Msisdn;
import ru.fintech.example.models.Renewal;
import ru.fintech.example.models.UpdateUser;
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


    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }
//    public void terminationContract(int userID) {
//        //same as delete method?
//    }
    @PutMapping("/renewal")
    public void msisdnRenewal(@RequestBody Renewal renewal){
        userService.msisdnRenewal(renewal.getOldUserId(),
                renewal.getMsisdnId(), renewal.getNewUserId());
    }
    @PatchMapping("/{userId}/change/icc")
    public MsisdnDTO changeIcc(@PathVariable("userId")int userId, @RequestParam(value = "icc") String icc ) {
        return userService.changeIcc(userId, icc);
    }

    @PostMapping("/add/msisdn")
    public void addMsisdnToUser( @RequestBody MsisdnDTO msisdnDTO) {
        userService.addMsisdnToUser(msisdnDTO);
    }

    @PatchMapping("/{userId}/change/msisdn")
    public MsisdnDTO msisdnChange(@PathVariable("userId") int userId, @RequestParam(value = "msisdn") String msisdn) {
        return userService.changeMsisdn(userId, msisdn);
    }
    @PutMapping("/change/passport")
    public UserDTO updatePassportData(@RequestBody UserDTO userDTO) {
        return userService.changePassportData(userDTO);
    }
    @PatchMapping("/{userId}/change/password")
    public UserDTO changePassword(@PathVariable("userId") int userId,@RequestParam(value = "password") String password) {
        return userService.changePassword(userId, password);
    }
    @PostMapping("/contract/with/msisdn")
    public UserDTO contractWithMsisdn(UserDTO userDTO, Msisdn msisdn){
        return userService.contractWithMsisdn(userDTO, msisdn);
    }
}
