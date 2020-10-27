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
import com.example.postboard.boarddata.API;
import com.example.postboard.errorhandler.ToastMaker;

public class login_screen extends Fragment {
    NavController navController;
    ToastMaker toastMaker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toastMaker = new ToastMaker(getContext());

        navController = Navigation.findNavController(view);

        final View mainView = view;

        view.findViewById(R.id.login_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                EditText email = mainView.findViewById(R.id.email_field);
                EditText password = mainView.findViewById(R.id.password_field);

                LoginDetail loginDetails = new LoginDetail(email, password);

                if(!loginDetails.verifyEmail()) toastMaker.newToast("Err: email must not be empty");
                if(!loginDetails.verifyPassword()) toastMaker.newToast("Err: password must be longer than 8 letters");

                API.log_in(loginDetails, getContext(), navController);
            }
        });
    }
}