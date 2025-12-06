package com.app.cli;
import java.util.HashMap;

public class FlagExtractor {

public FlagExtractor(){}


    public HashMap<String,String> flagExtractor(String[] tokens, String[] validFlags){
        HashMap<String,String> flajs = new HashMap<>();
        
        for (String f : validFlags){

            for (int i = 0;i < tokens.length;i++ ){

                if(f.equalsIgnoreCase(tokens[i])){
                    if((i+1) < tokens.length) {
                        if(tokens[i+1].startsWith("-") ){

                            flajs.put(f,"MISSING");

                        }else if(f.equalsIgnoreCase("--description")){
                            StringBuilder sb = new StringBuilder();
                            int aux = i+1;
                            sb.append(tokens[aux]);
                            for (int j = aux+1; j < tokens.length; j++) {
                                if(tokens[j].startsWith("-")){
                                    break;
                                }
                                sb.append(" ").append(tokens[j]);

                            }
                            flajs.put(f,sb.toString());
                        } else {

                            flajs.put(f, tokens[i+1]);
                        }

                    }else{
                        flajs.put(f,"MISSING");
                    }



                }
            }
        }


        return flajs;

    }

}
