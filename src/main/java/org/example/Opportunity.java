package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Opportunity {

    private Integer id;

    private Contact decisionMaker;
    private Product product;
    private int quantity;
    private Status status;

    private static int idCounter = 1;

    public Opportunity(Contact decisionMaker, Product product, int quantity, Status status) {
        this.decisionMaker = decisionMaker;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
        this.id = idCounter;

        idCounter++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void printMe(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String printable = gson.toJson(this);
        System.out.println(printable);
    }
}
