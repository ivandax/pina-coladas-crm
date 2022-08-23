package org.example;

public class Lead {
    private String name;
    private int phoneNumber;
    private String emailAdress;
    private String companyName;
    private int id;

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

    public void setId(int id) {
        this.id = id;
    }
}


