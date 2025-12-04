package com.app.services;
import com.app.models.Expense;
import java.util.ArrayList;
import java.util.HashMap;

import  com.app.models.Result;

public class ManagerExpense {
    private ArrayList<Expense> dataBase = new ArrayList<>();
    private int  id = 1;
    public  ManagerExpense(){
    }

    public ArrayList<Expense> getDataBase() {
        return dataBase;
    }

    public Result Add(String description, double value){
        if(value < 0 ){
            return Result.INVALID_AMOUNT;
        }
        dataBase.add(new Expense(description,value,id++));
        return Result.SUCCES;
    }

    public Result Update(int Id, double amount){
         for(Expense E : dataBase){
            if(E.getId() == Id){
               E.setAmount(amount);
            }
         }

         return Result.SUCCES;
    }

    public  Result Delete(int Id){

        for(int i = 0;i < dataBase.size();i++){
            if(dataBase.get(i).getId() == Id){
                dataBase.remove(i);
                 return Result.SUCCES;
            }
        }

        return  Result.ERROR_NOT_FOUND;


    }


    public ArrayList<Expense> seeAll(){
      return new ArrayList<>(this.dataBase);
    }


    public double Summary(){
        double total = 0;

        for(Expense E:dataBase ){
            total += E.getAmount();
        }
        return total;
    }


    public double SummarySpecificMonth(int month){
        double total = 0;
        for(Expense E : dataBase){
            if(E.getDate().getMonthValue() == month){
                total+= E.getAmount();
            }
        }
        return  total;
    }




}
