package com.example.tfg_davidquesada;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewAccountActivity extends AppCompatActivity {

    Button bRegister;
    EditText textNameR;
    EditText textPasswordR;
    EditText textNumberR;
    EditText textAdressR;

    DB_Management db_management = new DB_Management(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount_activity);
        getSupportActionBar().hide();

        bRegister = (Button) findViewById(R.id.button);
        textNameR = (EditText) findViewById(R.id.textNameR);
        textPasswordR = (EditText) findViewById(R.id.textPasswordR);
        textNumberR = (EditText) findViewById(R.id.textNumberR);
        textAdressR = (EditText) findViewById(R.id.textDireccionR);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = textNameR.getText().toString();
                String password = textPasswordR.getText().toString();
                String number = textNumberR.getText().toString();
                String adress = textAdressR.getText().toString();

                if(db_management.login_insert(user,password,number,adress) != -1){
                    System.out.println("Insertado");
                }else{
                    System.out.println("No insertado");;
                }
            }
        });

    }
}