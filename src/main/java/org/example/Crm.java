package org.example;

import java.util.Scanner;

public class Crm {
    public static void startProgram(){
        Scanner scanner = new Scanner(System.in);
        listenForCommands(scanner);
    }

    private static Integer captureNumericInput(Scanner scanner, String question) {
        Integer myInteger = null;
        while (myInteger == null) {
            System.out.println(question);
            String input = scanner.nextLine();
            try {
                myInteger = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("This is not a number!");
            }
        }
        return myInteger;
    }

    private static String captureStringInput(Scanner scanner, String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    private static void listenForCommands(Scanner scanner) {
        while(true){
            System.out.println("Please enter the next command: \n");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("new lead")){
                System.out.println("We will capture new lead \n");
                String name = captureStringInput(scanner, "Please enter the lead's name: ");
                Integer phoneNumber = captureNumericInput(scanner, "Please enter the lead's phone number: ");
                String email = captureStringInput(scanner, "Please enter the lead's email: ");
                String companyName = captureStringInput(scanner, "Please enter the lead's company name: ");
                System.out.println("We got: " + name + " " + phoneNumber + " " + email + " " + companyName + "\n");
            }
            if(input.toLowerCase().equals("convert lead")){
                System.out.println("We will convert lead");
            }
            if(input.toLowerCase().equals("exit")){
                System.out.println("Exiting...");
                break;
            }
        }
    }
}
