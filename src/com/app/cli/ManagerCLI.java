package com.app.cli;
import java.util.ArrayList;
import java.util.HashMap;
import com.app.models.Expense;
import com.app.models.Response;
import com.app.models.Result;
import com.app.services.ManagerExpense;
import com.app.views.PrinterViews;



public class ManagerCLI {
   final  private ManagerExpense ManagerExpense;
    private String[] flags  = {"--description","--amount","--id","--month"};
    final private FlagExtractor FlagExtractor;
    final private PrinterViews printer;


    public ManagerCLI(){
        this.ManagerExpense = new ManagerExpense();
        this.FlagExtractor = new FlagExtractor();
        this.printer = new PrinterViews();

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
                  break;
                default:
                    System.out.println("no se reconoce el comando");

            }



    }

    private void comandAdd(String[] tokens ){
        HashMap<String,String> Tokens =  FlagExtractor.flagExtractor(tokens,flags);
        System.out.println("Tokens = " + Tokens);
        if (!Tokens.containsKey("--description")){
            System.out.println("error: falta de flag--description");
            return;
        }
        if (!Tokens.containsKey("--amount")){
            System.out.println("error: falta de flag --amount");
            return;
        }
        if (Tokens.containsKey("--description") && Tokens.get("--description").equals("MISSING")){
            System.out.println("error: falta de valor para la flag --description");
            return;
        }
        if (Tokens.containsKey("--amount") && Tokens.get("--amount").equals("MISSING")){
            System.out.println("error: falta de valor para la flag --value");
            return;
        }



            try{



                String description = Tokens.get("--description");
                double value = Double.parseDouble(Tokens.get("--amount"));
                Response<Void> R = ManagerExpense.Add(description,value);
                if(R.getStatus() == Result.SUCCES){
                    printer.mesaggeExit(R.getTxt());
                    return;
                }
                if (R.getStatus()== Result.INVALID_AMOUNT) {
                    printer.messageError(R.getTxt());

                }

            }catch (NumberFormatException e){
                System.out.println("error: debe de ser un numero valido");
            }




    }

    private void comandList(){
        Response<ArrayList<Expense>> obj = ManagerExpense.seeAll();
        Result R  = obj.getStatus();
        if(R == Result.ERROR_NOT_FOUND){
            printer.messageError(obj.getTxt());
            return;
        }
        if(R == Result.SUCCES){
            printer.messageList(obj.getData());
        }

    }

    private  void comandSummary(String[] tokens){
        HashMap<String,String> Tokens = FlagExtractor.flagExtractor(tokens,flags);
        if(Tokens.isEmpty()){

            Response<Double> obj = ManagerExpense.Summary();
            Result R = obj.getStatus();
            if (R == Result.ERROR_NOT_FOUND){
                printer.messageError(obj.getTxt());
                return;
            }
            if (R == obj.getStatus()){
                printer.mesaggeExit(String.valueOf(obj.getData()));
                return;
            }

        }

        if (!Tokens.containsKey("--month")){
            System.out.println("error:Flag no reconocida");
            return;
        }
        if(Tokens.get("--month").equals("MISSING")){
            System.out.println("Error: falta de valor para la flag --month");
            return;
        }



            try {
                int valueMonth = Integer.parseInt(Tokens.get("--month"));
                Response<Double> obj = ManagerExpense.SummarySpecificMonth(valueMonth);
                Result R = obj.getStatus();

                if (R == Result.SUCCES){
                    printer.mesaggeExit(String.valueOf(obj.getData()));
                    return;
                }

                if(R == Result.INVALID_DATE){
                    printer.messageError(obj.getTxt());

                }



            }catch (NumberFormatException e){
                System.out.println("error: debe  de ser un numero");
            }

    }

    private void  comandDelete(String[] tokens){
        HashMap<String,String> Tokens = FlagExtractor.flagExtractor(tokens,flags);
        if(!Tokens.containsKey("--id")){
            System.out.println("error: falta de parametro --id");
            return;
        }
        if(Tokens.get("--id").equals("MISSING")){
            System.out.println("falta de valor en la flag --id");
            return;
        }
        try {
            int id = Integer.parseInt(Tokens.get("--id"));
            Response<Void> obj  = ManagerExpense.Delete(id);
            Result R = obj.getStatus();
            if (R == Result.SUCCES){
                printer.mesaggeExit(obj.getTxt());
                return;
            }
            if (R == Result.ERROR_NOT_FOUND){
                printer.messageError(obj.getTxt());

            }
        }catch (NumberFormatException e){
            System.out.println("error: debe ingresar un numero");
        }


    }

}
