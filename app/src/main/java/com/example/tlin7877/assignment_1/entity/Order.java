package com.example.tlin7877.assignment_1.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by tlin7877 on 11/22/2017.
 */

@Entity(tableName = "Order_T")
public class Order {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "OrderID")
    private int OrderID;

    @ForeignKey(entity = Drink.class, parentColumns = "DrinkID", childColumns ="DrinkID" )
    @ColumnInfo(name = "DrinkID")
    private int DrinkID;

    @ForeignKey(entity = User.class, parentColumns = "Email", childColumns ="UserEmail" )
    @ColumnInfo(name = "UserEmail")
    private String UserEmail;

    @ColumnInfo(name = "Quantity")
    private int Quantity;

    @Ignore
    public Order() {
    }

    public Order(int DrinkID, String UserEmail,int Quantity) {
        this.DrinkID=DrinkID;
        this.UserEmail=UserEmail;
        this.Quantity = Quantity;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int drinkID) {
        DrinkID = drinkID;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
