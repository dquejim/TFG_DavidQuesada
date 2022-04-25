package com.example.tfg_davidquesada;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_Activity extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        getSupportActionBar().hide();
        t = (TextView) findViewById(R.id.textView3);

        t.setText(getIntent().getStringExtra("userName"));
    }
}
