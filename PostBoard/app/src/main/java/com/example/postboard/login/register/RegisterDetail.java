package com.example.postboard.login.register;

import android.widget.EditText;

import com.example.postboard.login.LoginDetail;

public class RegisterDetail extends LoginDetail {
    private String passwordCheck;

    public RegisterDetail(EditText emailField, EditText passwordField, EditText passwordCheck) {
        super(emailField, passwordField);
        this.passwordCheck = passwordCheck.getText().toString();
    }

    public boolean verifyPasswords(){
        return this.password.equals(this.passwordCheck);
    }
}
