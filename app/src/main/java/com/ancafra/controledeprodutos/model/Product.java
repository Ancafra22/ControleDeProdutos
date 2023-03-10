package com.ancafra.controledeprodutos.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private int quantity;
    private double value;

    public Product() {
    }

    public Product(int id, String name, int quantity, double value) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
