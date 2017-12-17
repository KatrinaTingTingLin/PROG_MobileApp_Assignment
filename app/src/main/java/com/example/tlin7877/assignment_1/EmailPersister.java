package com.example.tlin7877.assignment_1;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tlin7877.assignment_1.database.AppDatabase;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tlin7877 on 12/7/2017.
 */

public class EmailPersister {
    private String EMAIL = "Email";
    //key for preferences
    private String PREFERENCES="emailSession";
    SharedPreferences sessionPrefer;
    SharedPreferences.Editor editor;

    public EmailPersister(Context context){
        sessionPrefer = context.getSharedPreferences(PREFERENCES,Context.MODE_PRIVATE);
        editor = sessionPrefer.edit();
    }

    public void storeUser(String email){
        editor.putString(EMAIL,email);
        editor.commit();
    }

    //to get useremail
    public String getUserEmail()
    {
        return sessionPrefer.getString(EMAIL,"");
    }

    public void logOutUser()
    {
        editor.clear();
        editor.commit();
    }
}
