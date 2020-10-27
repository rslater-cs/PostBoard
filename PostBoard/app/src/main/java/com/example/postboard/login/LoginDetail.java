package com.example.postboard.login;

import android.widget.EditText;

import com.example.postboard.boarddata.Security;

import org.json.JSONObject;

public class LoginDetail {
    protected String email, password;

    public LoginDetail(EditText emailField, EditText passwordField){
        this.email = emailField.getText().toString();
        this.password = passwordField.getText().toString();
    }

    public boolean verifyEmail(){
        return !this.email.isEmpty();
    }

    public boolean verifyPassword(){
        return this.password.length() > 8;
    }

    public String getHash(){
        return Security.hash(password);
    }

    public String getEmail(){
        return email;
    }

    public JSONObject bundle() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", email);
            jsonObject.put("hash", getHash());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(jsonObject.toString());

        return jsonObject;
    }
}
