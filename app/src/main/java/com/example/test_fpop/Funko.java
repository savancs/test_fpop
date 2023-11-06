package com.example.test_fpop;

public class Funko {

    String name;
    int number;
    String type;
    String fandom;
    boolean on;
    String ultimate;
    Double price;

    public Funko(String name, int number, String type, String fandom, String on, String ultimate, Double price) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.fandom = fandom;
        this.on = Boolean.parseBoolean(on);
        this.ultimate = ultimate;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFandom() {
        return fandom;
    }

    public void setFandom(String fandom) {
        this.fandom = fandom;
    }
    public boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public String getUltimate() {
        return ultimate;
    }

    public void setUltimate(String ultimate) {
        this.ultimate = ultimate;
    }

    public Double getPrice(){ return price;}

    public void setPrice(Double price){this.price = price;}
}
