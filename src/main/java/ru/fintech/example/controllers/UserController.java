package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.UserDTO;
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
    public UserDTO update(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        return userService.update(userDTO);
    }

    @PutMapping("/updatePassword")
    public UserDTO updatePassword(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        return userService.updatePassword(userDTO);
    }

    @PutMapping("/updatePassportData")
    public UserDTO updatePassportData(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        return userService.updatePassportData(userDTO);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }
}
