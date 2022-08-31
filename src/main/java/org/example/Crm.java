package org.example;

import java.util.*;

public class Crm {
    public static void startProgram(){
        Scanner scanner = new Scanner(System.in);
        listenForCommands(scanner);
    }

    private static void listenForCommands(Scanner scanner) {

        List<Lead> leadList = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();
        List<Opportunity> opportunityList = new ArrayList<>();

        while(true){
            System.out.println("Please enter the next command: \n");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("new lead")){
                createNewLead(scanner, leadList);
            }
            if(input.toLowerCase().split(" ")[0].equals("convert")){
                try{
                    Integer id = captureParameterFromString(input);
                    Optional<Lead> maybeLead = leadList.stream().filter(lead -> Objects.equals(lead.getId(), id)).findFirst();
                    if(maybeLead.isPresent()){
                        Integer leadId = maybeLead.get().getId();
                        System.out.println("Found the lead:");
                        System.out.println("Creating contact...");
                        Contact newContact = new Contact(maybeLead.get());
                        newContact.printMe();
                        System.out.println("Please input information for the Account:");
                        createNewAccount(scanner, accountList, opportunityList, newContact);
                        System.out.println("Removing lead with id " + leadId);
                        leadList.removeIf(lead -> Objects.equals(lead.getId(), leadId));
                        System.out.println("Current accounts: \n");
                        showAccounts(accountList);
                    } else {
                        System.out.println("We could not find this lead \n");
                    }
                } catch (IllegalAccessException e){
                    System.err.println(e.getMessage());
                }
            }
            if(input.toLowerCase().split(" ")[0].equals("lookup")){
                try{
                    Integer id = captureParameterFromString(input);
                    Optional<Lead> maybeLead = leadList.stream().filter(lead -> Objects.equals(lead.getId(), id)).findFirst();
                    if(maybeLead.isPresent()){
                        System.out.println("Found the lead:\n");
                        maybeLead.get().printMe();
                    } else {
                        System.out.println("We could not find this lead \n");
                    }
                } catch (IllegalAccessException e){
                    System.err.println(e.getMessage());
                }
            }
            if(input.toLowerCase().split(" ")[0].equals("close-lost")){
                try{
                    Integer id = captureParameterFromString(input);
                    Optional<Opportunity> maybeOpportunity = opportunityList.stream().filter(opp -> Objects.equals(opp.getId(), id)).findFirst();
                    if(maybeOpportunity.isPresent()){
                        System.out.println("Found the opportunity:\n");
                        maybeOpportunity.get().setStatus(Status.CLOSED_LOST);
                        updateOpportunityInAccounts(accountList, maybeOpportunity.get());
                        maybeOpportunity.get().printMe();
                    } else {
                        System.out.println("We could not find this opportunity \n");
                    }
                } catch (IllegalAccessException e){
                    System.err.println(e.getMessage());
                }
            }
            if(input.toLowerCase().split(" ")[0].equals("close-won")){
                try{
                    Integer id = captureParameterFromString(input);
                    Optional<Opportunity> maybeOpportunity = opportunityList.stream().filter(opp -> Objects.equals(opp.getId(), id)).findFirst();
                    if(maybeOpportunity.isPresent()){
                        System.out.println("Found the opportunity, setting to won:\n");
                        maybeOpportunity.get().setStatus(Status.CLOSED_WON);
                        updateOpportunityInAccounts(accountList, maybeOpportunity.get());
                        maybeOpportunity.get().printMe();
                    } else {
                        System.out.println("We could not find this opportunity \n");
                    }
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
            if(input.toLowerCase().equals("show opportunities")){
                showOpportunities(opportunityList);
            }
            if(input.toLowerCase().equals("show accounts")){
                showAccounts(accountList);
            }
        }
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

    private static Industry captureIndustry(Scanner scanner) {
        Industry selectedIndustry = null;
        while (selectedIndustry == null) {
            System.out.println("Please select the industry (produce / ecommerce / manufacturing / medical / other");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("produce")){
                selectedIndustry = Industry.PRODUCE;
            } else if(input.equalsIgnoreCase("ecommerce")){
                selectedIndustry = Industry.ECOMMERCE;
            }else if(input.equalsIgnoreCase("manufacturing")){
                selectedIndustry = Industry.MANUFACTURING;
            }else if(input.equalsIgnoreCase("medical")){
                selectedIndustry = Industry.MEDICAL;
            } else {
                selectedIndustry = Industry.OTHER;
            }
        }
        return selectedIndustry;
    }

    private static Product captureProduct(Scanner scanner) {
        Product selectedProduct = null;
        while (selectedProduct == null) {
            System.out.println("Please enter the product: (hybrid / flatbed / box)");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("hybrid")){
                selectedProduct = Product.HYBRID;
            } else if(input.equalsIgnoreCase("flatbed")){
                selectedProduct = Product.FLATBED;
            }else if(input.equalsIgnoreCase("box")){
                selectedProduct = Product.BOX;
            }
        }
        return selectedProduct;
    }


    private static void createNewLead(Scanner scanner, List<Lead> leadList){
        System.out.println("We will capture new lead \n");
        String name = captureStringInput(scanner, "Please enter the lead's name: ");
        Integer phoneNumber = captureNumericInput(scanner, "Please enter the lead's phone number: ");
        String email = captureStringInput(scanner, "Please enter the lead's email: ");
        String companyName = captureStringInput(scanner, "Please enter the lead's company name: ");
        Lead newLead = new Lead(name, phoneNumber, email, companyName );
        leadList.add(newLead);
        System.out.println("New Lead Added! Enter 'show leads' to see list of leads" );
    }

    private static void createNewAccount(Scanner scanner, List<Account> accountList, List<Opportunity> opportunityList, Contact contact){
        System.out.println("We will create a new account \n");
        Industry industry = captureIndustry(scanner);
        Integer employeeCount = captureNumericInput(scanner, "Please enter employee count");
        String city = captureStringInput(scanner, "Please enter the city");
        String country = captureStringInput(scanner, "Please enter the country");
        Account newAccount = new Account(industry, employeeCount, city, country);
        Opportunity newOpportunity = createNewOpportunity(scanner, contact);
        opportunityList.add(newOpportunity);
        newAccount.addContact(contact);
        newAccount.addOpportunity(newOpportunity);
        accountList.add(newAccount);
    }

    private static Opportunity createNewOpportunity(Scanner scanner, Contact contact){
        System.out.println("We will create a new opportunity \n");
        Product product = captureProduct(scanner);
        Integer quantity = captureNumericInput(scanner, "How many of this product?");
        return new Opportunity(contact, product, quantity, Status.OPEN);
    }

    private static void showLeads(List<Lead> leadList){
        System.out.println(Lead.getLeadCount());
        if(leadList.size() == 0){
            System.out.println("No leads available\n");
        } else {
            for(Lead lead : leadList){
                lead.printMe();
            }
        }
    }

    private static void showAccounts(List<Account> accountList){
        System.out.println("Reporting all accounts: \n");
        if(accountList.size() == 0){
            System.out.println("No accounts available\n");
        } else {
            for(Account account : accountList){
                account.printMe();
            }
        }
    }

    private static void showOpportunities(List<Opportunity> opportunityList){
        System.out.println("Reporting all opportunities: \n");
        if(opportunityList.size() == 0){
            System.out.println("No opportunities available\n");
        } else {
            for(Opportunity opp : opportunityList){
                opp.printMe();
            }
        }
    }

    private static Integer captureParameterFromString(String string) throws IllegalAccessException {
        Integer id = null;
        String[] split = string.split(" ");
        if(split.length < 2){
            throw new IllegalAccessException("The provided command is invalid. We expected a command followed by an id");
        }
        try{
            String lastStringFromSplit = split[split.length - 1];
            id = Integer.parseInt(lastStringFromSplit);
        } catch(NumberFormatException e){
            System.err.println("Could not convert the parameter to an integer");
        }
        return id;
    }

    private static void updateOpportunityInAccounts(List<Account> accountList, Opportunity opportunity){
        for(Account account : accountList){
            List<Opportunity> opportunityList = account.getOpportunityList();
            for(Opportunity opp : opportunityList){
                if(Objects.equals(opp.getId(), opportunity.getId())){
                    opp.setStatus(opportunity.getStatus());
                }
            }
        }
    }
}
