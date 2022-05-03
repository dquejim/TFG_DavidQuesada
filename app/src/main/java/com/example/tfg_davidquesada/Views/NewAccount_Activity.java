package com.example.tfg_davidquesada.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.Control.DB_Management;

import io.github.muddz.styleabletoast.StyleableToast;

public class NewAccount_Activity extends AppCompatActivity {

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
                String userCheck = db_management.checkUser(user,password,2);

                if( userCheck == null){
                    if(db_management.insertUser(user,password,number,adress) != -1){
                        new StyleableToast.Builder(NewAccount_Activity.this).text("Usuario creado correctamente.") //Texto del Toast y vista del mismo
                                .backgroundColor(Color.GREEN).textColor(Color.BLACK) //Fondo y color de texto
                                .iconStart(R.drawable.tick).show(); //Indicamos el icono del toast y lo mostramos

                        Intent intent = new Intent(NewAccount_Activity.this, Home_Activity.class);
                        intent.putExtra("userName",user);
                        startActivity(intent);
                    }
                }else{
                    new StyleableToast.Builder(NewAccount_Activity.this).text("El usuario ya existe.") //Texto del Toast y vista del mismo
                            .backgroundColor(Color.RED).textColor(Color.BLACK) //Fondo y color de texto
                            .iconStart(R.drawable.tick).show(); //Indicamos el icono del toast y lo mostramos
                    System.out.println(userCheck);
                }
            }
        });

    }
}