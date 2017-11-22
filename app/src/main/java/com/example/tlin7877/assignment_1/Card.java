package com.example.tlin7877.assignment_1;

/**
 * Created by tlin7877 on 11/22/2017.
 */

public class Card {
    private int CardNumber;
    private float Value;
    private String Picture;
    private int UserID;

    public Card(){

    }

    public Card(float Value,String Picture, int UserID)
    {
        this.Value=Value;
        this.Picture=Picture;
        this.UserID=UserID;
    }

    public int getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(int cardNumber) {
        CardNumber = cardNumber;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public int getUserID() { return UserID;}

    public void setUserID(int userID) {
        UserID = userID;
    }
}
