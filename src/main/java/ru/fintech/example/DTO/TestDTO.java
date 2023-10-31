package ru.fintech.example.DTO;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.ToString;

@ToString
@Hidden
public class TestDTO {
    private int id;
    private String name;
    private int age;
    private int rch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRch() {
        return rch;
    }

    public void setRch(int rch) {
        this.rch = rch;
    }
}
