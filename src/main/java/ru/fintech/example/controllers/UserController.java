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

    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * ToDo Refactor this method because db was changed
     */
    @Deprecated
    @PostMapping("/add")
    public UserDTO create(@RequestBody UserDTO userDTO){
        log.info("Coming request {}", userDTO);
        return userService.create(userDTO);
    }

    /**
     * ToDo Refactor this method because db was changed
     */
    @Deprecated
    @GetMapping("/{userId}")
    public UserDTO get(@PathVariable("userId") int userId){
        log.info("Coming id - {}", userId);
        return userService.get(userId);
    }

    /**
     * ToDo Refactor this method because db was changed
     */
    @Deprecated
    @GetMapping("")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    /**
     * ToDo Refactor this method because db was changed
     */
    @Deprecated
    @PutMapping("/update")
    public UserDTO update(@RequestBody UserDTO userDTO){
        log.info(userDTO.toString());
        return userService.update(userDTO);
    }

    /**
     * ToDo Refactor this method because db was changed
     */
    @Deprecated
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") int userId){
        userService.delete(userId);
    }
}
