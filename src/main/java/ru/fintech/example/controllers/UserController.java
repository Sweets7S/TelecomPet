package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.UserDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserServise userServise;

    public UserController(UserService userService){
        this.userServise = userService;
    }

    @PostMapping("/add")
    public UserDTO create(@RequestBody UserDTO userDTO){
        log.info("Coming request {}", userDTO);
        return userServise.create(userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO get(@PathVariable("userId") int userId){
        log.info("Coming id - {}", userId);
        return userServise.get(userId);
    }

    @GetMapping("")
    public List<UserDTO> getAll(){
        return userServise.getAll();
    }

    @PutMapping("/update")
    public UserDTO update(@RequestBody UserDTO userDTO){
        log.info(userDTO.toString());
        return userServise.update(userDTO);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId){
        userServise.delete(userId);
    }
}
