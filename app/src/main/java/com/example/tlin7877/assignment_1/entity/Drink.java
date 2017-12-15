package com.example.tlin7877.assignment_1.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by tlin7877 on 11/22/2017.
 */

@Entity(tableName = "Drink")
public class Drink {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "DrinkID")
    private int DrinkID;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "Picture")
    private String Picture;

    @ColumnInfo(name = "Price")
    private double Price;

    @ColumnInfo(name = "Description")
    private String Description;

    @Ignore
    public Drink() {
    }

    public Drink(String Name,String Picture, double Price,String Description) {
        this.Name=Name;
        this.Picture = Picture;
        this.Price=Price;
        this.Description=Description;
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int drinkID) {
        DrinkID = drinkID;
    }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public String getPicture() { return Picture; }

    public void setPicture(String picture) { Picture = picture;}

    public double getPrice() {
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
