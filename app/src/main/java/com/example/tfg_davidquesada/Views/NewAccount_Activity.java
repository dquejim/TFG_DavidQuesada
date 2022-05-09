package com.example.tfg_davidquesada.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.Control.Utils;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.Control.DB_Management;

import io.github.muddz.styleabletoast.StyleableToast;

public class NewAccount_Activity extends AppCompatActivity {

    //Declaracion de variables
    Button bRegister;
    EditText textNameR;
    EditText textPasswordR;
    EditText textNumberR;
    EditText textAdressR;

    DB_Management db_management = new DB_Management(this);
    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount_activity);
        //Escondemos el actionBar
        getSupportActionBar().hide();

        //"Enlazamos" los componentes graficos con las variables creadas anteriormente
        bRegister = (Button) findViewById(R.id.button);
        textNameR = (EditText) findViewById(R.id.textNameR);
        textPasswordR = (EditText) findViewById(R.id.textPasswordR);
        textNumberR = (EditText) findViewById(R.id.textNumberR);
        textAdressR = (EditText) findViewById(R.id.textDireccionR);

        //Añadimos una accion al pulsar el boton para registrarse
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Declaramos un intent desde esta actividad hasta la principal
                Intent intent = new Intent(NewAccount_Activity.this, Home_Activity.class);

                //Si tenemos conexion a internet obtenemos los datos que el usuario ha introducido en las cajas de texto
                if(utils.comprobarInternet(getBaseContext())){
                    if(!textNameR.getText().toString().isEmpty() && !textPasswordR.getText().toString().isEmpty()  && !textNumberR.getText().toString().isEmpty()  && !textAdressR.getText().toString().isEmpty() ) {
                        String user = textNameR.getText().toString();
                        String password = textPasswordR.getText().toString();
                        String number = textNumberR.getText().toString();
                        String adress = textAdressR.getText().toString();

                        //Llamamos al metodo checkUser con opcion 2, que nos dirá si el usuario ya existe
                        String userCheck = db_management.checkUser(user, password, 2);

                        //Si el usuario no existe, lo añade a la BBDD , lanza un toast e inicia el intent
                        if(userCheck == null){
                            if(db_management.insertUser(user,password,number,adress) != -1){
                                new StyleableToast.Builder(NewAccount_Activity.this).text("Usuario creado correctamente.") //Texto del Toast y vista del mismo
                                        .backgroundColor(Color.GREEN).textColor(Color.BLACK) //Fondo y color de texto
                                        .iconStart(R.drawable.tick).show(); //Indicamos el icono del toast y lo mostramos

                                intent.putExtra("userName",user);
                                startActivity(intent);
                            }
                        }else{
                            new StyleableToast.Builder(NewAccount_Activity.this).text("El usuario ya existe.") //Texto del Toast y vista del mismo
                                    .backgroundColor(Color.RED).textColor(Color.BLACK) //Fondo y color de texto
                                    .iconStart(R.drawable.cross).show(); //Indicamos el icono del toast y lo mostramos
                        }
                    }else{
                        new StyleableToast.Builder(NewAccount_Activity.this).text("Debes rellenar todos los campos.") //Texto del Toast y vista del mismo
                                .backgroundColor(Color.RED).textColor(Color.BLACK) //Fondo y color de texto
                                .iconStart(R.drawable.cross).show(); //Indicamos el icono del toast y lo mostramos
                    }

                    //Si no dispone de conexion a internet, nos lanza un alertDialog que nos pregunta si queremos iniciar sesion como invitado
                }else{
                    createAlertDialog("Parece que está sin conexión, ¿desea acceder como invitado?",intent);
                }

            }
        });

    }

    //Método para crear un alertDialog en esta actividad
    public void createAlertDialog(String title,Intent intent){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final CharSequence[] opciones = {"Aceptar","Cancelar"};
        alertDialog.setTitle(title);
        alertDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Si pulsamos el boton de aceptar
                if(opciones[i].equals("Aceptar")){
                    intent.putExtra("userName","Invitado001");
                    startActivity(intent);
                }
            }
        });

        alertDialog.show();
    }
}