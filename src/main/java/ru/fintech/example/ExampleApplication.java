package ru.fintech.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
//ToDo Делаем метод расторжения договора(При расторжении договора, привязка номера к юзеру 8)
//ToDo -Переоформление номера на другого пользователя
//ToDo -Метод смены симкарты (icc)
//ToDo -Метод добавления номеров (Захардкожены к 8 юзеру)
//ToDo -Метод добавления номера для существуюещего пользователя из имеющихся
//ToDo -Метод заключения договора сразу с покупкой Sim
//ToDo -Метод возвращающий все свободные номера (У 8 юзера)
//ToDo -Метод смены номера (мсисден)
//ToDo -Метод для обновления паспортных данных (поле документ, поле фио)
//ToDo -Метод смены пароля (поле пассворд)
//ToDo -Убрать из метода апдейт смену пароля, смену логина
//ToDo Тариф в обновлении 1.0.2... (Название, месячная оплата, пакет минут, пакет смс, пакет интернета, максимальная скорость, минуты в другие страны)
//ToDo Опции в обновлении 1.0.2... (Название, месячная оплата, безлимит на соцсети и мессенджеры)
//ToDo Банковские продукты в обновлении 1.0.3... (Привязка карты, переводы)
