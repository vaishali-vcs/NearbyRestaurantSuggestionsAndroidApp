package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    public static final String EXTRA_ADDRESS = "com.example.vaishali_tatsat_COMP304Sec001_Lab5.EXTRA_ADDRESS";
    public static final String EXTRA_NAME = "com.example.vaishali_tatsat_COMP304Sec001_Lab5.EXTRA_NAME";
    public static final String EXTRA_CUISINE = "com.example.vaishali_tatsat_COMP304Sec001_Lab5.EXTRA_CUISINE";
    public static final String EXTRA_LAT = "com.example.vaishali_tatsat_COMP304Sec001_Lab5.EXTRA_LAT";
    public static final String EXTRA_LNG = "com.example.vaishali_tatsat_COMP304Sec001_Lab5.EXTRA_LNG";
    LatLng toronto = new LatLng(43.6, -79.4);
    double latitiude, lonitude;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        latitiude = intent.getDoubleExtra(EXTRA_LAT, 43.6);
        lonitude = intent.getDoubleExtra(EXTRA_LNG, -79.4);
        name = intent.getStringExtra(EXTRA_NAME);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Toronto and move the camera
        LatLng location = new LatLng(latitiude, lonitude);

        mMap.addMarker(new MarkerOptions().position(location).title(name)).showInfoWindow();

       mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
       mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
