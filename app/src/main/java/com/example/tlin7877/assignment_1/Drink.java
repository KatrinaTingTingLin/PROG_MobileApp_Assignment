package com.example.tlin7877.assignment_1;

/**
 * Created by tlin7877 on 11/22/2017.
 */

public class Drink {
    private int DrinkID;
    private String Name;
    private float Price;
    private String Description;

    public Drink() {
    }

    public Drink(int DrinkID,String Name,float Price,String Description) {
        this.DrinkID =DrinkID;
        this.Name=Name;
        this.Price=Price;
        this.Description=Description;
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int drinkID) {
        DrinkID = drinkID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
