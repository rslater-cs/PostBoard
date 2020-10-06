package com.example.postboard.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.postboard.R;
import com.example.postboard.boarddata.Database;
import com.example.postboard.boarddata.PasswordSecurity;

public class login_screen extends Fragment {
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final View mainView = view;

        view.findViewById(R.id.login_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                int logkey = attemptSignIn(mainView);
                if(logkey != -1) navController.navigate(R.id.action_login_screen_to_summary_screen);
            }
        });
    }



    private int attemptSignIn(View view){
        EditText emailField = view.findViewById(R.id.email_field);
        EditText passwordField = view.findViewById(R.id.password_field);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(email.length() == 0 || password.length() == 0) return -1;

        password = PasswordSecurity.hash(password);
        Database database = new Database(getContext());

        return database.signin(email, password);
    }
}