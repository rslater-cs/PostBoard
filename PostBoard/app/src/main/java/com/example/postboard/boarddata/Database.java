package com.example.postboard.boarddata;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "test-db.cqmmammonmll.us-east-2.rds.amazonaws.com";
    private static final String USER = "SLTR";
    private static final String PASSWORD = "masterpass";
    private static final String ERROR_EMAIL = "ERROR: email does not exist";
    private static final String ERROR_PASSWORD = "ERROR: password is incorrect";
    private static final String ERROR_ALREADY_EXIST = "ERROR: email is already registered to an account";

    private Connection connection;
    private Statement statement;
    private Context context;

    public Database(Context context){
        this.context = context;
        try{
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
        }catch(Exception e){
            errorMessage(e.toString());
        }
    }

    public int register(String email, String hash){
        try{
            ResultSet emailResult = statement.executeQuery("SELECT COUNT(email) FROM users WHERE email = '" + email + "';");

            emailResult.next();

            if(emailResult.getInt(0) == 1){
                errorMessage(ERROR_ALREADY_EXIST);
                return -1;
            }
        }catch (Exception e){
            errorMessage(e.toString());
        }

        try{
            statement.executeQuery("INSERT INTO users (email, password) VALUES ('" + email + "', '" + hash + "')");
        }catch (Exception e){
            errorMessage(e.toString());
        }

        try{
            ResultSet result = statement.executeQuery("SELECT userid FROM users WHERE email = '" + email + "' AND password = '" + hash + "';");

            result.next();

            return result.getInt(0);
        }catch (Exception e){
            errorMessage(e.toString());
        }

        return -1;
    }

    public int signin(String email, String hash){
        try {
            ResultSet result = statement.executeQuery("SELECT COUNT(email) FROM users WHERE email = '" + email + "';");
            if(result.getInt(0) == 0){
                errorMessage(ERROR_EMAIL);
                return -1;
            }
        }catch (Exception e){
            errorMessage(e.toString());
        }

        try{
            ResultSet result = statement.executeQuery("SELECT userid FROM users WHERE email = '" + email + "' AND password = '" + hash + "';");

            result.next();
            return result.getInt(0);
        }catch(Exception e){
            errorMessage(ERROR_PASSWORD);
        }

        return -1;
    }

    public boolean endDB(){
        try {
            connection.close();
        }catch (Exception ignore){
            return false;
        }
        return true;
    }

    private void errorMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
