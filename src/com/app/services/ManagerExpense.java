package com.app.services;
import com.app.models.Expense;
import java.util.ArrayList;
import java.util.HashMap;
import  com.app.models.Response;

import  com.app.models.Result;

public class ManagerExpense {
    private ArrayList<Expense> dataBase = new ArrayList<>();
    private int  id = 1;
    public  ManagerExpense(){
    }

    public ArrayList<Expense> getDataBase() {
        return dataBase;
    }

    public Response<Void> Add(String description, double value){
        if(value < 0 ){
            return new Response<>(Result.INVALID_AMOUNT,null,"Monto invalido");
        }
        dataBase.add(new Expense(description,value,id++));
        return new Response<>(Result.SUCCES,null,"Gasto agregado de manera exitosa");
    }

    public Result Update(int Id, double amount){
         for(Expense E : dataBase){
            if(E.getId() == Id){
               E.setAmount(amount);
            }
         }

         return Result.SUCCES;
    }

    public  Response<Void> Delete(int Id){

        for(int i = 0;i < dataBase.size();i++){
            if(dataBase.get(i).getId() == Id){
                dataBase.remove(i);
                 return  new Response<>(Result.SUCCES,null,"gasto eliminado de manera exitosa");
            }
        }

        return  new Response<>(Result.ERROR_NOT_FOUND,null,"no se encontro id");


    }


    public Response<ArrayList<Expense>> seeAll(){
      if(dataBase.isEmpty()){
         return new Response<>(Result.ERROR_NOT_FOUND,null,"No se encontraron Gastos");
      }

      return new Response<>(Result.SUCCES, new ArrayList<>(dataBase),null);

    }


    public Response<Double> Summary(){
         if (dataBase.isEmpty()){
             return new Response<>(Result.ERROR_NOT_FOUND,0.0, "no hay resumen para mostrar");
         }

        double total = 0;
        for(Expense E:dataBase ){
            total += E.getAmount();
        }
        return new Response<>(Result.SUCCES,total,null);
    }


    public Response<Double> SummarySpecificMonth(int month){
        if(month < 1 || month > 12){
            return new Response<>(Result.INVALID_DATE,0.0,"el numero del mes es invalido");
        }

        double total = 0;
        for(Expense E : dataBase){
            if(E.getDate().getMonthValue() == month){
                total+= E.getAmount();
            }
        }
        if(total == 0){
            return new Response<>(Result.INVALID_AMOUNT,total,"no hay gastos resgistrados en ese mes");
        }
        return new Response<>(Result.SUCCES,total,null);
    }




}
