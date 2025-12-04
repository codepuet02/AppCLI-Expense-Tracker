package com.app.views;
import com.app.models.Expense;
import com.app.models.Response;
import com.app.models.Result;

import java.util.ArrayList;

public class PrinterViews {

    public PrinterViews(){

    }

    public void mesaggeExit(String txt){
        System.out.println(txt);
    }

    public void messageError(String txt){
        System.out.println(txt);
    }

    public void  messageList(ArrayList<Expense> list){
        System.out.printf("%-5s %-15s %-10s %-12s\n", "ID", "DATE", "DESC", "AMOUNT");
        for(Expense E : list){
            System.out.printf("%-5s %-15s %-10s %-12s\n", E.getId(),E.getDate(),E.getDescription(),E.getAmount());
        }

    }


}
