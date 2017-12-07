package com.example.tlin7877.assignment_1;

/**
 * Created by tlin7877 on 12/7/2017.
 */

public class AccountPersister {
    private User user;

    public AccountPersister(){
        this.user = null;
    }

    public AccountPersister(User tempUser){
        this.user = tempUser;
    }

    public void setUser(User tempUser){
        user = tempUser;
    }

    public User getUser(){
        return user;
    }
}
