package com.example.tlin7877.assignment_1.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by tlin7877 on 11/22/2017.
 */

@Entity(tableName = "Card")
public class Card {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CardNumber")
    private int CardNumber;

    @ColumnInfo(name = "Value")
    private float Value;

    @ColumnInfo(name = "Picture")
    private String Picture;

    @ColumnInfo(name = "UserEmail")
    private String UserEmail;

    @Ignore
    public Card(){

    }

    public Card(float Value,String Picture, String UserEmail)
    {
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
