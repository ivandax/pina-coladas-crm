package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Contact {
    private Integer id;
    private String name;
    private int phoneNumber;
    private String emailAdress;
    private String companyName;

    public Contact(Lead lead){
        this.id = lead.getId();
        this.name = lead.getName();
        this.phoneNumber = lead.getPhoneNumber();
        this.emailAdress = lead.getEmailAdress();
        this.companyName = lead.getCompanyName();
    }

    public void printMe(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String printable = gson.toJson(this);
        System.out.println(printable);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
