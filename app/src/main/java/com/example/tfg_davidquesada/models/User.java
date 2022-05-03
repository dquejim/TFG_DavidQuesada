package com.example.tfg_davidquesada.models;

public class User {
    private String name;
    private String password;
    private String number;
    private String adress;

    public User(String name, String password, String number, String adress){
        this.name = name;
        this.password = password;
        this.number = number;
        this.adress = adress;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


}
