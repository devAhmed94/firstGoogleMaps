package com.example.simplemarkerapp.activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.simplemarkerapp.R;
import com.example.simplemarkerapp.db.DatabaseHelper;
import com.example.simplemarkerapp.db.Model;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this::onMapLongClick);
        mMap.setOnMarkerClickListener(this::onMarkerClick);
        ArrayList<Model> allData = databaseHelper.getAllData();
        for (Model model :allData){

            LatLng latLng = new LatLng(Double.parseDouble(model.getLatitude()), Double.parseDouble(model.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(latLng).title(model.getTitle()).zIndex(model.getId())).showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,6));
        }


    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        Intent intent = new Intent(MapsActivity.this, AddActivity.class);
        intent.putExtra("latitude",latLng.latitude);
        intent.putExtra("longitude",latLng.longitude);


        startActivity(intent);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra("latitude",marker.getPosition().latitude);
        intent.putExtra("longitude",marker.getPosition().longitude);
        intent.putExtra("title",marker.getTitle());
        intent.putExtra("id",String.valueOf(marker.getZIndex()));


        startActivity(intent);
        finish();
        return true;
    }
}