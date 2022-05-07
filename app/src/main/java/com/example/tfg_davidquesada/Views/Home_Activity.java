package com.example.tfg_davidquesada.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg_davidquesada.Control.DB_Management;
import com.example.tfg_davidquesada.R;
import com.example.tfg_davidquesada.models.User;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class Home_Activity extends AppCompatActivity {
    DB_Management db_management = new DB_Management(this);
    User user;
    Button testButton;
    MapView mapView;
    GoogleMapOptions options = new GoogleMapOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getSupportActionBar().hide();

        user = db_management.getUser(getIntent().getStringExtra("userName"));

        testButton = findViewById(R.id.button2);

        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(false)
                .rotateGesturesEnabled(false)
                .tiltGesturesEnabled(false);

        mapView = new MapView(this,options);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this, PersonalizeUser_Activity.class);
                intent.putExtra("userName",user.getName());
                startActivity(intent);
            }
        });
    }
}
