package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.UserDTO;
import ru.fintech.example.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserDTO create(@RequestBody UserDTO userDTO){
        log.info("Coming request {}", userDTO);
        return userService.createUser(userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO get(@PathVariable("userId") int userId){
        log.info("Coming id - {}", userId);
        return userService.getUser(userId);
    }

    @GetMapping("")
    public List<UserDTO> getAll(){
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public UserDTO update(@RequestBody UserDTO userDTO){
        log.info(userDTO.toString());
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId){
        userService.deleteUser(userId);
    }
}
