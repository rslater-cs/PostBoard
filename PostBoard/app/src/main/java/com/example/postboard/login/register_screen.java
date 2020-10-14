package com.example.postboard.login;

import android.os.AsyncTask;
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
import android.widget.Toast;

import com.example.postboard.R;
import com.example.postboard.boarddata.RESTAPIInfo;

import org.json.JSONObject;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class register_screen extends Fragment {
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        view.findViewById(R.id.regsiter_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailField = view.findViewById(R.id.email_input);
                EditText passwordField = view.findViewById(R.id.first_password_input);
                EditText secondPasswordField = view.findViewById(R.id.second_password_input);

                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String secondPassword = secondPasswordField.getText().toString();

                if(email.isEmpty()) return;
                if(password.length() < 8) return;
                if(!password.equals(secondPassword)) return;

                JSONObject result = new POSTQuery.execute(RESTAPIInfo.URL, postData);

                navController.navigate(R.id.action_register_screen_to_summary_screen);
            }
        });
    }

    private class POSTQuery extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {

            String returnData = "";

            HttpURLConnection httpURLConnection = null;

            try{
                httpURLConnection = (HttpURLConnection) new URL(strings[0]).openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                DataOutputStream output = new DataOutputStream(httpURLConnection.getOutputStream());
                output.writeBytes(strings[1]);
                output.flush();
                output.close();

                InputStream input = httpURLConnection.getInputStream();
                InputStreamReader inputReader = new InputStreamReader(input);

                int currChar = inputReader.read();

                while(currChar != -1){
                    returnData += (char) currChar;
                    currChar = inputReader.read();
                }
            }catch (Exception e){
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }finally {
                if(httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }

            if(returnData.isEmpty()) return null;
             try{
                 return new JSONObject(returnData);
             }catch (Exception e){
                 return null;
             }
        }
    }
}