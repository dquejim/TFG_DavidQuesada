package com.example.tfg_davidquesada.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.Control.DB_Management;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.models.User;

public class PersonalizeUser_Activity extends AppCompatActivity {
    EditText textName;
    Button buttonConfirm;
    User user;

    DB_Management db_management = new DB_Management(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();

        textName = findViewById(R.id.textNameR2);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        user = db_management.getUser(getIntent().getStringExtra("userName"));

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textName.setText(user.getName());
            }
        });

    }
}
