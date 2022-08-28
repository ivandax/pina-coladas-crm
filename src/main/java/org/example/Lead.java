package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Lead {
    private String name;
    private int phoneNumber;
    private String emailAdress;
    private String companyName;
    private Integer id;

    private static int idCounter = 1;

    public void setId() {
        this.id = idCounter;
        idCounter++;
    }

    public Lead(String name, int phoneNumber, String emailAdress, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.companyName = companyName;
        setId();
    }

    public void printMe(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String printable = gson.toJson(this);
        System.out.println(printable);
    }

    public static Integer getLeadCount(){
        return idCounter - 1;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


