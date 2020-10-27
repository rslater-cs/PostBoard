package com.example.postboard.login.register;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.postboard.R;
import com.example.postboard.boarddata.API;
import com.example.postboard.errorhandler.ToastMaker;
import com.example.postboard.login.register.RegisterDetail;

import java.util.HashMap;
import java.util.Map;

public class register_screen extends Fragment {
    NavController navController;
    ToastMaker toastMaker;
    View superView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toastMaker = new ToastMaker(getContext());

        navController = Navigation.findNavController(view);
        this.superView = view;

        view.findViewById(R.id.regsiter_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailField = superView.findViewById(R.id.email_input);
                EditText passwordField = superView.findViewById(R.id.first_password_input);
                EditText secondPasswordField = superView.findViewById(R.id.second_password_input);

                RegisterDetail registerDetails = new RegisterDetail(emailField, passwordField, secondPasswordField);

                if(!registerDetails.verifyEmail()) toastMaker.newToast("Err: The email field is empty");
                if(!registerDetails.verifyPassword()) toastMaker.newToast("Err: Your password must be 8 or larger letters");
                if(!registerDetails.verifyPasswords()) toastMaker.newToast("Err: Both passwords must match");

                API.register(registerDetails, getContext(), navController);
            }
        });
    }
}