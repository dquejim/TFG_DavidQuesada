package com.example.tfg_davidquesada;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;

public class Utils {

    public boolean comprobarInternet(){
        try{
            Process p =  java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int valor = p.waitFor();
            boolean conectado = (valor == 0);
            return conectado;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
