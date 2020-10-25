package com.example.postboard.boarddata;

import android.content.Context;

import androidx.navigation.NavController;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.postboard.R;
import com.example.postboard.errorhandler.ToastMaker;
import com.example.postboard.login.LoginDetail;
import com.example.postboard.login.register.RegisterDetail;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class API {
    public static final String URL = "http://192.168.0.20/PostBoardPHP/api/";

    public static void log_in(final LoginDetail loginDetails, Context context, final NavController navController){
        final ToastMaker toastMaker = new ToastMaker(context);
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, URL+"log_in.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        toastMaker.newToast(response);
                        navController.navigate(R.id.action_register_screen_to_summary_screen);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMaker.newToast(error.getMessage());
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return loginDetails.bundle();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(request);
    }

    public static void register(final RegisterDetail registerDetails, Context context, final NavController navController){
        final ToastMaker toastMaker = new ToastMaker(context);
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, URL+"register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        toastMaker.newToast(response);
                        navController.navigate(R.id.action_register_screen_to_summary_screen);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMaker.newToast(error.getMessage());
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return registerDetails.bundle();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(request);
    }
}
