package com.app.services;
import com.app.models.Expense;
import java.util.ArrayList;

public class ManagerExpense {
    private ArrayList<Expense> dataBase = new ArrayList<>();
    public  ManagerExpense(){
    }

    public void Add(Expense obj){
        dataBase.add(obj);
    }

    public void Update(int Id, double amount){
         for(Expense E : dataBase){
            if(E.getId() == Id){
               E.setAmount(amount);
            }
         }
    }

    public  void Delete(int Id){
        for(int i = 0;i < dataBase.size();i++){
            if(dataBase.get(i).getId() == Id){
                dataBase.remove(i);
                  break;
            }
        }
    }

    public  ArrayList<Expense> seeAll(){
        return new ArrayList<>(dataBase);
    }

    public double Summary(){
         double total = 0;
        for(Expense E: dataBase){
            total += E.getAmount();
        }
        return total;
    }


    public double SummarySpecificMonth(int Value){
        double total = 0;
        for(Expense E : dataBase){
            if(E.getDate().getMonthValue() == Value){
                total+= E.getAmount();
            }
        }
        return total;
    }

}
