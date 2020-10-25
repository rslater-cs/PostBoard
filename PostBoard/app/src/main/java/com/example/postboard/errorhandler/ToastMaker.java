package com.example.postboard.errorhandler;

import android.content.Context;
import android.widget.Toast;

public class ToastMaker {
    private Context context;

    public ToastMaker(Context context){
        this.context = context;
    }

    public void newToast(String message){
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }
}
