package com.example.quizzy;

public class User {

    String username;
    String password;
    String password2;
    String email;
    String usertype;

    public User(){
        this.usertype = "player";
    }

    public User(String username, String password, String password2, String email)
    {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
