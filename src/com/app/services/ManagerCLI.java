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
        String[] string = txt.trim().split("\\s+");

        if(txt.trim().isEmpty()){
            System.out.println("cadena vacia");
            return;
        }
        if(string.length < 2 ){
            System.out.println("comando imcompleto");
            return;
        }

        if(!string[0].equalsIgnoreCase("expense-tracker")  ){
            System.out.println("error: comando debe empezar con exprense-tracker");
            return;
        }

            switch (string[1].toLowerCase()){
                case "add":
                    comandAdd(string);
                    break;

                case "list":

                    comandList();

                    break;

                case "summary":

                    comandSummary(string);
                  break;


                case "delete":

                  comandDelete(string);

            }


    }

    private void comandAdd(String[] tokens ){
        HashMap<String,String> Tokens = flagExtractor(tokens,flags);
        if (!Tokens.containsKey("--description")){
            System.out.println("error: falta de parametro --description");
            return;
        }
        if (!Tokens.containsKey("--amount")){
            System.out.println("error: falta de parametro --amount");
            return;
        }

        try{
            String description = Tokens.get("--description");
            double value = Double.parseDouble(Tokens.get("--amount"));
            ManagerExpense.Add(new Expense(description,value));
        }catch (NumberFormatException e){
            System.out.println("error: debe de ser un numero valido");
        }

    }

    private  void comandList(){
        ManagerExpense.seeAll();
    }

    private  void comandSummary(String[] tokens){
        HashMap<String,String> Tokens = flagExtractor(tokens,flags);

        if(Tokens.containsKey("--month")){

            try {
                int valueMonth = Integer.parseInt(Tokens.get("--month"));
                ManagerExpense.SummarySpecificMonth(valueMonth);
            }catch (NumberFormatException e){
                System.out.println("error: debe  de ser un numero");
            }
        }else {
            ManagerExpense.Summary();
        }


    }

    private void  comandDelete(String[] tokens){
        HashMap<String,String> Tokens = flagExtractor(tokens,flags);
        if(!Tokens.containsKey("--id")){
            System.out.println("error: falta de parametro --id");
            return;
        }
        try {
            int id = Integer.parseInt(Tokens.get("--id"));
            ManagerExpense.Delete(id);
        }catch (NumberFormatException e){
            System.out.println("error: debe ingresar un numero");
        }


    }



    private HashMap<String,String> flagExtractor(String[] tokens, String[] validFlags){
       HashMap<String,String> flajs = new HashMap<>();
       for (String f : validFlags){
           for (int i = 0;i < tokens.length;i++ ){
               if(f.equalsIgnoreCase(tokens[i])){
                   if((i+1) < tokens.length){
                       if(tokens[i+1].startsWith("-")){
                           flajs.put(f,null);
                           
                       }else {
                           flajs.put(f,tokens[i+1]);
                       }

                   }
                   else {
                       flajs.put(f,null);

                   }


               }
           }
       }
       return flajs;

    }




}
