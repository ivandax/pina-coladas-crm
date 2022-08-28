package org.example;

import java.util.ArrayList;
import java.util.List;
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

        List<Lead> leadList = new ArrayList<>();

        while(true){
            System.out.println("Please enter the next command: \n");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("new lead")){
                System.out.println("We will capture new lead \n");
                String name = captureStringInput(scanner, "Please enter the lead's name: ");
                Integer phoneNumber = captureNumericInput(scanner, "Please enter the lead's phone number: ");
                String email = captureStringInput(scanner, "Please enter the lead's email: ");
                String companyName = captureStringInput(scanner, "Please enter the lead's company name: ");
                Lead newLead = new Lead(name, phoneNumber, email, companyName );
                leadList.add(newLead);
                System.out.println("New Lead Added! Enter 'show leads' to see list of leads" );
            }
            if(input.toLowerCase().split(" ")[0].equals("convert")){
                try{
                    Integer id = captureParameterFromString(input);
                    System.out.println("Here we have the id to search on the lead list " + id);
                    // Here we have an id to search for the lead.
                } catch (IllegalAccessException e){
                    System.err.println(e.getMessage());
                }
            }
            if(input.toLowerCase().equals("show leads")){
                showLeads(leadList);
            }
            if(input.toLowerCase().equals("exit")){
                System.out.println("Exiting...");
                break;
            }
        }
    }

    private static void showLeads(List<Lead> leadList){
        if(leadList.size() == 0){
            System.out.println("No leads created yet");
        } else {
            for(Lead lead : leadList){
                lead.printMe();
            }
        }
    }

    private static Integer captureParameterFromString(String string) throws IllegalAccessException {
        Integer id = null;
        String[] split = string.split(" ");
        if(split.length != 2){
            throw new IllegalAccessException("The provided command is invalid. We expected a command followed by an id");
        }
        try{
            id = Integer.parseInt(split[1]);
        } catch(NumberFormatException e){
            System.err.println("Could not convert the parameter to an integer");
        }
        return id;
    }
}
