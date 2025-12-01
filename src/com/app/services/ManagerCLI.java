package com.app.services;
import java.util.HashMap;
import com.app.models.Expense;


public class ManagerCLI {
    private ManagerExpense ManagerExpense;
    private String[] flags  = {"--description","--amount","--id","--month"};
    public ManagerCLI(){
        this.ManagerExpense = new ManagerExpense();
    }

    public void parseCommand(String txt){
        String[] string = txt.split("\\s+");

        if(string[0].equalsIgnoreCase("expense-tracker") ){

            switch (string[1].toLowerCase()){
                case "add":
                    HashMap<String,String> flagValues = new HashMap<>();

                    for(int i = 0;i < flags.length;i++){
                        String element = flags[i];
                        for (int j = 0; j < string.length; j++) {
                            if (element.equalsIgnoreCase(string[j])){
                                flagValues.put(element,string[j+1]);
                            }

                        }
                    }

                    String description = flagValues.get("--description");
                    double amount = Double.parseDouble(flagValues.get("--amount"));
                     ManagerExpense.Add(new Expense(description,amount));
                    break;


                case "list":

                case "summary":
                    if(string.length == 2){

                    } else {
                        double value = 0;
                        for (int i = 0; i< flags.length;i++) {
                            String element = flags[i];
                            for (int j = 0; j <string.length ; j++) {
                                if (element.equalsIgnoreCase(string[j])){
                                     value = Double.parseDouble(string[j+1]);
                                }
                            }
                        }

                    }


                case "delete":

            }

        }

    }


}
