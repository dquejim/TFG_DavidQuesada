package com.example.tfg_davidquesada.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.Control.DB_Management;
import com.example.tfg_davidquesada.Control.Utils;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import io.github.muddz.styleabletoast.StyleableToast;

public class PersonalizeUser_Activity extends AppCompatActivity {
    EditText textName;
    EditText textPassword;
    EditText textNumber;
    EditText textAdress;
    Button buttonConfirm;
    BottomNavigationView bottomNavigationView;
    User user;
    Utils utils = new Utils();
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
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        buttonConfirm = findViewById(R.id.buttonConfirm);
        user = db_management.getUser(utils.getPreferences(sharedPreferences));

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
                    createToast("No puedes modificar un usuario como invitado!",R.drawable.cross,Color.RED);
                    startActivity(intent);
                }else{
                    if(!textPassword.getText().equals(user.getPassword()) || !textNumber.getText().equals(user.getNumber()) || !textAdress.getText().equals(user.getAdress())){
                        db_management.alterUser(textName.getText().toString(), textPassword.getText().toString(), textNumber.getText().toString(), textAdress.getText().toString());
                        createToast("Usuario modificado correctamente!",R.drawable.tick,Color.GREEN);
                    }
                    startActivity(intent);
                }
            }
        });

        bottomNavigationView = findViewById(R.id.menu);
        bottomNavigationView.setSelectedItemId(R.id.home_option);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;

                switch (item.getItemId()){
                    case R.id.user_option:
                        intent = new Intent(PersonalizeUser_Activity.this, PersonalizeUser_Activity.class);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.home_option:
                        intent = new Intent(PersonalizeUser_Activity.this, Home_Activity.class);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.food_option:
                        intent = new Intent(PersonalizeUser_Activity.this, Food_Activity.class);
                        overridePendingTransition(0,0);
                        break;
                }

                startActivity(intent);

                return true;
            }
        });
    }

    public void createToast(String title, int icon,int backgroundcolor){
        new StyleableToast.Builder(PersonalizeUser_Activity.this).text(title) //Texto del Toast y vista del mismo
                .backgroundColor(backgroundcolor).textColor(Color.BLACK) //Fondo y color de texto
                .iconStart(icon).show(); //Indicamos el icono del toast y lo mostramos
    }
}
