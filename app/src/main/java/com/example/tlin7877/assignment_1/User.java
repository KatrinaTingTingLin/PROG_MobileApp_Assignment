package com.example.tlin7877.assignment_1;

/**
 * Created by tlin7877 on 11/22/2017.
 */

public class User {
    private int UserID;
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String Province;
    private String PostalCode;
    private String Email;
    private String Password;
    private String Birthday;
    private int ReceiveEmail;

    public User()
    {

    }
    public User(String FirstName,String LastName,
                String Address, String City,String Province,
                String PostalCode,String Email,String Password,
                String Birthday,int ReceiveEmail)
    {
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Address=Address;
        this.City=City;
        this.Province=Province;
        this.PostalCode=PostalCode;
        this.Email=Email;
        this.Password=Password;
        this.Birthday=Birthday;
        this.ReceiveEmail=ReceiveEmail;
    }

    public int getUserID() {return UserID;}

    public void setUserID(int userID) {
        UserID = userID;
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
