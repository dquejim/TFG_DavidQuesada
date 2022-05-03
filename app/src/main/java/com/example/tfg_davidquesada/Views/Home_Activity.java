package com.example.tfg_davidquesada.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.Control.DB_Management;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.models.User;

public class Home_Activity extends AppCompatActivity {
    DB_Management db_management = new DB_Management(this);
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getSupportActionBar().hide();

        user = db_management.getUser(getIntent().getStringExtra("userName"));


    }
}
