package com.example.tfg_davidquesada.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.Control.DB_Management;
import com.example.tfg_davidquesada.Control.Utils;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.models.User;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home_Activity extends AppCompatActivity {

    DB_Management db_management = new DB_Management(this);
    User user;
    ImageButton exitButton;
    MapView mapView;
    Utils utils = new Utils();
    BottomNavigationView bottomNavigationView;
    GoogleMapOptions options = new GoogleMapOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getSupportActionBar().hide();

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        user = db_management.getUser(utils.getPreferences(sharedPreferences));

        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(false)
                .rotateGesturesEnabled(false)
                .tiltGesturesEnabled(false);

        mapView = new MapView(this,options);

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this,Login_Activity.class);
                startActivity(intent);
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
                        overridePendingTransition(0,0);
                        break;

                    case R.id.home_option:
                        intent = new Intent(Home_Activity.this, Home_Activity.class);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.food_option:
                        intent = new Intent(Home_Activity.this, Food_Activity.class);
                        overridePendingTransition(0,0);
                        break;
                }

                startActivity(intent);

                return true;
            }
        });




    }
}
