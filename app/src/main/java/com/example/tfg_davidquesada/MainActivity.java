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
                if(utils.comprobarInternet(getBaseContext())){
                    Intent intent = new Intent(MainActivity.this,NewAccountActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this,Menu_Activity.class);
                    intent.putExtra("userName"," Invitado001");
                    startActivity(intent);
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Menu_Activity.class);

                if(utils.comprobarInternet(getBaseContext())){
                    String user = textName.getText().toString();
                    String password = textPassword.getText().toString();
                    intent.putExtra("userName",user);
                }else{
                    System.out.println("Entrando como invitado");
                    intent.putExtra("userName"," Invitado001");
                }

                startActivity(intent);
            }
        });
    }
}