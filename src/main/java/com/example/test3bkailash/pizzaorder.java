package com.example.test3bkailash;

import javafx.beans.property.*;

public class pizzaorder{
    private String customerName;
    private int mobileNumber;
    private String pizzaSize;
    private int numToppings;
    private int totalBill;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getNumToppings() {
        return numToppings;
    }

    public void setNumToppings(int numToppings) {
        this.numToppings = numToppings;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public pizzaorder(String customerName, int mobileNumber, String pizzaSize, int numToppings, int totalBill) {
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.pizzaSize = pizzaSize;
        this.numToppings = numToppings;
        this.totalBill = totalBill;
    }
}
