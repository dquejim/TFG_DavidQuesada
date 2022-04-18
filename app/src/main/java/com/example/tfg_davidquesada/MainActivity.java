package com.example.tfg_davidquesada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bRegister;
    EditText textName;
    EditText textPassword;
    DB_Management db_management = new DB_Management(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bRegister = (Button) findViewById(R.id.registerButton);
        textName = (EditText) findViewById(R.id.textName);
        textPassword = (EditText) findViewById(R.id.textPassword);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = textName.getText().toString();
                String password = textPassword.getText().toString();

                if(db_management.login_insert(user,password) != -1){
                    System.out.println("Insertado");
                }else{
                    System.out.println("No insertado");;
                }
            }
        });
    }
}