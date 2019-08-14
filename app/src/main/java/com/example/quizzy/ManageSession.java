package com.example.quizzy;

import android.content.Context;
import android.content.SharedPreferences;

public class ManageSession {

    Context context;
    String username;
    SharedPreferences preferences;

    public String getUsername() {
        username = preferences.getString("username","");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        preferences.edit().putString("username",username).commit();
    }



    public ManageSession(Context context){

        this.context = context;
        preferences = context.getSharedPreferences("user-pref",Context.MODE_PRIVATE);

    }

    public void remove()
    {
        preferences.edit().clear().commit();
    }
}
