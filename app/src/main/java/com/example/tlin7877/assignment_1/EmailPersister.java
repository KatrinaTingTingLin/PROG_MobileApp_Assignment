package com.example.tlin7877.assignment_1;


import android.app.Application;
import android.content.Context;

import com.example.tlin7877.assignment_1.database.AppDatabase;

/**
 * Created by tlin7877 on 12/7/2017.
 */

public class EmailPersister {
    private static EmailPersister instance = new EmailPersister();
    private static String Email;

    public EmailPersister(){
        this.Email = null;
    }

    public static EmailPersister getInstance(){
        return instance;
    }

    public static void setInstance(EmailPersister instance){
        EmailPersister.instance = instance;
    }

    public void setEmail(String email){
        this.Email = email;
    }

    public String getEmail(){
        return this.Email;
    }
}
