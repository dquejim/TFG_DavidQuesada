package com.example.tfg_davidquesada.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.Control.Utils;
import com.example.tfg_davidquesada.Control.DB_Management;

import io.github.muddz.styleabletoast.StyleableToast;

public class Login_Activity extends AppCompatActivity {

    TextView bRegister;
    Button bLogin;
    EditText textName;
    EditText textPassword;
    DB_Management db_management = new DB_Management(this);
    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();

        bRegister = (TextView) findViewById(R.id.bRegister);
        bLogin = (Button) findViewById(R.id.bLogin);
        textName = (EditText) findViewById(R.id.textName);
        textPassword = (EditText) findViewById(R.id.textPassword);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utils.comprobarInternet(getBaseContext())){
                    Intent intent = new Intent(Login_Activity.this, NewAccount_Activity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
                    intent.putExtra("userName"," Invitado001");
                    startActivity(intent);
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Home_Activity.class);

                if(utils.comprobarInternet(getBaseContext())){
                    String user = textName.getText().toString();
                    String password = textPassword.getText().toString();

                    if(db_management.checkUser(user,password,1) != null){
                        new StyleableToast.Builder(Login_Activity.this).text("Bienvenido " + user + ".") //Texto del Toast y vista del mismo
                                .backgroundColor(Color.GREEN).textColor(Color.BLACK) //Fondo y color de texto
                                .iconStart(R.drawable.tick).show(); //Indicamos el icono del toast y lo mostramos

                        intent.putExtra("userName",user);
                        startActivity(intent);

                    }else{
                        new StyleableToast.Builder(Login_Activity.this).text("Usuario o contraseña incorrectos.") //Texto del Toast y vista del mismo
                                .backgroundColor(Color.RED).textColor(Color.BLACK) //Fondo y color de texto
                                .iconStart(R.drawable.tick).show(); //Indicamos el icono del toast y lo mostramos
                    }



                }else{
                    intent.putExtra("userName"," Invitado001");
                    startActivity(intent);
                }


            }
        });
    }
}