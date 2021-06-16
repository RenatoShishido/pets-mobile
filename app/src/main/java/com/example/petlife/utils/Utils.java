package com.example.petlife.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

    public static boolean isLogged;

    public static boolean getLogged() {
        return isLogged;
    }


    public static void aviso(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
