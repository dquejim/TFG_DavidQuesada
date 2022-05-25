package com.example.tfg_davidquesada.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg_davidquesada.Control.DB_Management;
import com.example.tfg_davidquesada.Control.Utils;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.models.Offer;
import com.example.tfg_davidquesada.models.User;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity{

    DB_Management db_management = new DB_Management(this);
    User user;
    TextView userNameView;
    ImageButton exitButton;
    ImageButton fakeMapsView;

    Utils utils = new Utils();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getSupportActionBar().hide();

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        user = db_management.getUser(utils.getPreferences(sharedPreferences));
        userNameView = findViewById(R.id.userNameView);
        userNameView.setText("Bienvenido " + user.getName()+ "!");

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });

        fakeMapsView = findViewById(R.id.fakeMapView);
        fakeMapsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utils.comprobarInternet(getBaseContext())) {
                    Uri _link = Uri.parse("https://goo.gl/maps/5MdAZttVLEe5jzr5A");
                    Intent i = new Intent(Intent.ACTION_VIEW, _link);
                    startActivity(i);
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
                        intent = new Intent(Home_Activity.this, PersonalizeUser_Activity.class);
                        break;

                    case R.id.home_option:
                        intent = new Intent(Home_Activity.this, Home_Activity.class);
                        break;

                    case R.id.food_option:
                        intent = new Intent(Home_Activity.this, Food_Activity.class);
                        break;
                }

                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            }
        });
    }



}
