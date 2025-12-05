package com.app;
import  com.app.cli.ManagerCLI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManagerCLI ManagerCli = new ManagerCLI();

        while (true){
            System.out.print(">>");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("exit")){

                break;
            }
            ManagerCli.parseCommand(answer);
        }
        sc.close();

    }
}