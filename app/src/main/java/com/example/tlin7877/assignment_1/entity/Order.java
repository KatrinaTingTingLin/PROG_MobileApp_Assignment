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

    @ColumnInfo(name = "ReferenceNumber")
    private String ReferenceNumber;

    @ForeignKey(entity = Drink.class, parentColumns = "DrinkID", childColumns ="DrinkID" )
    @ColumnInfo(name = "DrinkID")
    private int DrinkID;

    @ForeignKey(entity = User.class, parentColumns = "Email", childColumns ="UserEmail" )
    @ColumnInfo(name = "UserEmail")
    private String UserEmail;

    @ColumnInfo(name = "Size")
    private String Size;

    @ColumnInfo(name = "Comment")
    private String Comment;

    @ColumnInfo(name = "Date")
    private String Date;

    @Ignore
    public Order() {
    }

    public Order(String ReferenceNumber,int DrinkID,
                 String UserEmail,String Size,String Comment,String Date) {
        this.ReferenceNumber=ReferenceNumber;
        this.DrinkID=DrinkID;
        this.UserEmail=UserEmail;
        this.Size=Size;
        this.Comment=Comment;
        this.Date=Date;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getReferenceNumber() {
        return ReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
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

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}
