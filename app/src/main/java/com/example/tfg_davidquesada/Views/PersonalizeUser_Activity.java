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

import io.github.muddz.styleabletoast.StyleableToast;

public class PersonalizeUser_Activity extends AppCompatActivity {
    EditText textName;
    EditText textPassword;
    EditText textNumber;
    EditText textAdress;
    Button buttonConfirm;
    User user;

    DB_Management db_management = new DB_Management(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalize_user_activity);
        getSupportActionBar().hide();

        textName = findViewById(R.id.textNameR2);
        textName.setEnabled(false);
        textPassword = findViewById(R.id.textPasswordR2);
        textNumber = findViewById(R.id.textNumberR2);
        textAdress = findViewById(R.id.textAdressR2);

        buttonConfirm = findViewById(R.id.buttonConfirm);
        user = db_management.getUser(getIntent().getStringExtra("userName"));

        if(user.getName().equals("Invitado001")){
            textPassword.setEnabled(false);
            textNumber.setEnabled(false);
            textAdress.setEnabled(false);
        }

        textName.setText(user.getName());
        textPassword.setText(user.getPassword());
        textNumber.setText(user.getNumber());
        textAdress.setText(user.getAdress());

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalizeUser_Activity.this,Home_Activity.class);
                if(user.getName().equals("Invitado001")){
                    intent.putExtra("userName",user.getName());
                    startActivity(intent);
                }else{
                    if(!textPassword.getText().equals(user.getPassword()) || !textNumber.getText().equals(user.getNumber()) || !textAdress.getText().equals(user.getAdress())){
                        db_management.alterUser(textName.getText().toString(), textPassword.getText().toString(), textNumber.getText().toString(), textAdress.getText().toString());

                        new StyleableToast.Builder(PersonalizeUser_Activity.this).text("Usuario modificado correctamente.") //Texto del Toast y vista del mismo
                                .backgroundColor(Color.GREEN).textColor(Color.BLACK) //Fondo y color de texto
                                .iconStart(R.drawable.tick).show(); //Indicamos el icono del toast y lo mostramos
                    }
                    intent.putExtra("userName", user.getName());
                    startActivity(intent);
                }
            }
        });

    }
}
