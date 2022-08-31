package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private Integer id;
    private Industry industry;

    private Integer employeeCount;
    private String city;
    private String country;
    private final List<Contact> contactList;
    private List<Opportunity> opportunityList;

    private static int idCounter = 1;

    public Account(Industry industry, Integer employeeCount, String city, String country) {
        this.id = idCounter;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = new ArrayList<>();
        this.opportunityList = new ArrayList<>();

        idCounter++;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addContact(Contact contact){
        this.contactList.add(contact);
    }
    public void addOpportunity(Opportunity opportunity){
        this.opportunityList.add(opportunity);
    }

    public void printMe(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String printable = gson.toJson(this);
        System.out.println(printable);
    }

    public List<Contact> getContacts(){
        return this.contactList;
    }

    public List<Opportunity> getOpportunityList(){
        return this.opportunityList;
    }

}
