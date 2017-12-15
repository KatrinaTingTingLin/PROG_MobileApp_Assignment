package com.example.tlin7877.assignment_1.entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;


/**
 * Created by tlin7877 on 11/22/2017.
 */
@Entity(tableName = "User")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Password")
    private String Password;

    @ColumnInfo(name = "FirstName")
    private String FirstName;

    @ColumnInfo(name = "LastName")
    private String LastName;

    @ColumnInfo(name = "Address")
    private String Address;

    @ColumnInfo(name = "City")
    private String City;

    @ColumnInfo(name = "Province")
    private String Province;

    @ColumnInfo(name = "PostalCode")
    private String PostalCode;

    @ColumnInfo(name = "Birthday")
    private String Birthday;

    @ColumnInfo(name = "ReceiveEmail")
    private int ReceiveEmail;

    @Ignore
    public User()
    {

    }
    public User(String Email, String Password,String FirstName,String LastName,
                String Address, String City,String Province,
                String PostalCode, String Birthday,int ReceiveEmail)
    {
        this.Email = Email;
        this.Password=Password;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Address=Address;
        this.City=City;
        this.Province=Province;
        this.PostalCode=PostalCode;
        this.Birthday=Birthday;
        this.ReceiveEmail=ReceiveEmail;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() { return PostalCode;}

    public void setPostalCode(String postalCode) { PostalCode = postalCode;}

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public int getReceiveEmail() {
        return ReceiveEmail;
    }

    public void setReceiveEmail(int receiveEmail) {
        ReceiveEmail = receiveEmail;
    }


}
