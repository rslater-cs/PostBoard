package com.example.postboard.boarddata;

import android.content.Context;

import androidx.navigation.NavController;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

        JsonObjectRequest request = new JsonObjectRequest(URL+"log_in.php", loginDetails.bundle(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        toastMaker.newToast(response.toString());
                        int pk = -1;
                        String error = "Err: the server did not respond";
                        try {
                            pk = response.getInt("key");
                            error = response.getString("message");
                        }catch(Exception e){
                            toastMaker.newToast(e.getMessage());
                        }
                        if(pk == -1){
                            toastMaker.newToast(error);
                            return;
                        }
                        navController.navigate(R.id.action_login_screen_to_summary_screen);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMaker.newToast(error.getMessage());
            }
        }){
            /*@Override
            public byte[] getBody() {
                return loginDetails.bundle();
            }*/

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

        JsonObjectRequest request = new JsonObjectRequest(URL+"register.php", registerDetails.bundle(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        toastMaker.newToast(response.toString());
                        int pk = -1;
                        String error = "Err: the server did not respond";
                        //response = response.substring(2, response.length()-1);
                        System.out.println(response);
                        try {
                            pk = response.getInt("key");
                            error = response.getString("message");
                        }catch(Exception e){
                            toastMaker.newToast(e.getMessage());
                        }
                        if(pk == -1){
                            toastMaker.newToast(error);
                            return;
                        }
                        navController.navigate(R.id.action_register_screen_to_summary_screen);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMaker.newToast(error.getMessage());
            }
        }){
            /*@Override
            public byte[] getBody() {
                return registerDetails.bundle();
            }*/

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
