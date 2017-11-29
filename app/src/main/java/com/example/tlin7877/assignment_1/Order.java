package com.example.tlin7877.assignment_1;

/**
 * Created by tlin7877 on 11/22/2017.
 */

public class Order {
    private int OrderID;
    private String ReferenceNumber;
    private int DrinkID;
    private String UserEmail;
    private String Size;
    private String Comment;
    private String Date;

    public Order() {
    }

    public Order(int OrderID,String ReferenceNumber,int DrinkID,
                 String UserEmail,String Size,String Comment,String Date) {
        this.OrderID = OrderID;
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
