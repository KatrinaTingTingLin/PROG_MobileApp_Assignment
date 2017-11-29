package com.example.tlin7877.assignment_1;

/**
 * Created by tlin7877 on 11/22/2017.
 */

public class Card {
    private int CardNumber;
    private float Value;
    private String Picture;
    private String UserEmail;

    public Card(){

    }

    public Card(int CardNumber,float Value,String Picture, String UserEmail)
    {
        this.CardNumber = CardNumber;
        this.Value=Value;
        this.Picture=Picture;
        this.UserEmail=UserEmail;
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

    public String getUserEmail() { return UserEmail;}

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}
