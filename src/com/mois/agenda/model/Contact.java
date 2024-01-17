package com.mois.agenda.model;

import java.sql.Date;

public class Contact {

    private int id;
    private String name;
    private int age;
    private Date registerDate;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Date getRegisterDate() {
        return registerDate;
    }
}
