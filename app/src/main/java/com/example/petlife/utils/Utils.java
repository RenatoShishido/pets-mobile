package com.example.petlife.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

    public static void aviso(Context context, String message) {
       Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
