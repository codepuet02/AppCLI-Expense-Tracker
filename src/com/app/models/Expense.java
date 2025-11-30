package com.app.models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Expense {
    private LocalDate Date;
    private int id;
    private String name;
    private String description;
    private double amount;
    private String category;


    public Expense(String name,String description, double amount,String category){
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.Date = LocalDate.now();
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate(){
        return  Date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String  toString (){
        return String.valueOf(this.id) + " " + this.Date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + this.description + " " + String.valueOf(this.amount);
    }
}
