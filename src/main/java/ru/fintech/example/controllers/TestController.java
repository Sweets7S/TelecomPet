package ru.fintech.example.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fintech.example.DTO.TestDTO;
import ru.fintech.example.Exceptions.FaultException;
import ru.fintech.example.service.TestService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
@Hidden
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
    public ResponseEntity<TestDTO> get(@PathVariable("testId") int testId) throws Exception {
        log.info("Coming id - {}", testId);
        log.info("Coming id - " + testId);
      return ResponseEntity.ok(testService.get(testId));
    }

    @GetMapping("")
    public List<TestDTO> getAll(){
        return testService.getAll();
    }

    @PutMapping("/update")
    public TestDTO update(@RequestBody TestDTO testDTO){
        log.info(testDTO.toString());
        return testService.update(testDTO);
    }

    @DeleteMapping("/{testId}")
    public void delete(@PathVariable("testId") int testId) {
        testService.delete(testId);
    }

    @ExceptionHandler(FaultException.class)
    public ResponseEntity<String> handleFaultException(FaultException e){
        return new ResponseEntity<String>(String.format("FaultCode: %s, Massage: %s", e.getFaultCode(), e.getMessage()), HttpStatusCode.valueOf(444));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<String>(String.format("Massage: %s", e.getMessage()), HttpStatusCode.valueOf(500));
    }
//    @DeleteMapping("") // RequestParam = QueryParam
//    public void delete(@RequestParam(value = "testId") int testId, @RequestParam(value = "age") int age, @RequestParam(value = "name") String name){
//        log.info("{} {} {}", testId, age, name);
//        testService.delete(testId);
//    }
}
