package com.app.models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Expense {
    private LocalDate Date;
    private int id;
    private String description;
    private double amount;


    public Expense(String description, double amount){
        this.description = description;
        this.amount = amount;
        this.Date = LocalDate.now();

    }

    public int getId() {
        return id;
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
