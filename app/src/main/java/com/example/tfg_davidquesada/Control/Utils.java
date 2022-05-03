package com.example.tfg_davidquesada.Control;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.io.IOException;

public class Utils {

    public boolean comprobarInternet(Context context){
        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo red = connectivityManager.getActiveNetworkInfo();

        if(red != null && red.isConnected()) {
            connected = true;
        }

        return connected;
    }
}
