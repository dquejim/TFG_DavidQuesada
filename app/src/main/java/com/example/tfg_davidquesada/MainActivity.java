package com.example.tfg_davidquesada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView bRegister;
    Button bLogin;
    EditText textName;
    EditText textPassword;
    DB_Management db_management = new DB_Management(this);
    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        bRegister = (TextView) findViewById(R.id.bRegister);
        bLogin = (Button) findViewById(R.id.bLogin);
        textName = (EditText) findViewById(R.id.textName);
        textPassword = (EditText) findViewById(R.id.textPassword);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utils.comprobarInternet()){
                    Intent intent = new Intent(MainActivity.this,NewAccountActivity.class);
                    startActivity(intent);
                }else{
                    System.out.println("Entrando como invitado");
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utils.comprobarInternet()){
                    String user = textName.getText().toString();
                    String password = textPassword.getText().toString();

                    if(db_management.login_insert(user,password) != -1){
                        System.out.println("Insertado");
                    }else{
                        System.out.println("No insertado");;
                    }
                }else{
                    System.out.println("Entrando como invitado");
                }

            }
        });
    }
}