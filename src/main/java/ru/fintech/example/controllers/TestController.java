package ru.fintech.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.service.TestService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }

    @PostMapping("/add")
    public TestDTO create(@RequestBody TestDTO testDTO){
//        System.out.println(testDTO);
        log.info("Coming request {}", testDTO);
        return testService.create(testDTO);
    }

    @GetMapping("/{testId}")
    public TestDTO get(@PathVariable("testId") int testId){
        log.info("Coming id - {}", testId);
        log.info("Coming id - " + testId);
      return testService.get(testId);
    }

    @GetMapping("")
    public List<TestDTO> getAll(){
//        return testService.getAll();
        return null;
    }

    @PutMapping("/update")
    public void update(@RequestBody TestDTO testDTO){
//        testService.update(testDTO);
    }

    @DeleteMapping("/{testId}")
    public void delete(@PathVariable("testId") int testId){
//        testService.delete(testId);
    }
}
