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
}
