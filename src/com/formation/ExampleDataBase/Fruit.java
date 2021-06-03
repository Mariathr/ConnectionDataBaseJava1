package com.formation.ExampleDataBase;

import java.time.LocalDate;

public class Fruit {
    private Long Id;
    private String name;
    private LocalDate date;

    public Fruit() {
    }

    public Fruit(Long id, String name, LocalDate date) {
        Id = id;
        this.name = name;
        this.date = date;
    }
    public Fruit(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
