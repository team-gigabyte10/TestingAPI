package com.example.testingapi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MapTesting extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private static final float DEFAULT_ZOOM = 15f;
    private boolean mLocationPermissionGranted = false;
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_testing);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocationPermission();
        updateLocationUI();
        startTimer();
        //getDeviceLocation();
    }
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private void getLocationPermission() {

        String[] permission = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 0, 10000); // 10 seconds delay
    }


    private void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        // Call your Google Maps API code here
                        // For example:
                        try {
                            if (mLocationPermissionGranted) {
                                mFusedLocationProviderClient.getLastLocation()
                                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                                            @Override
                                            public void onSuccess(Location location) {
                                                if (location != null) {

                                                    mLastKnownLocation = location;
                                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                                            new LatLng(mLastKnownLocation.getLatitude(),
                                                                    mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                                                    LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                                                    MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("My Location");
                                                    mMap.addMarker(markerOptions);
                                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));


                                                } else {
                                                    LocationRequest locationRequest = LocationRequest.create();
                                                    locationRequest.setInterval(10000);
                                                    locationRequest.setFastestInterval(5000);
                                                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                                                    LocationCallback locationCallback = new LocationCallback() {
                                                        @Override
                                                        public void onLocationResult(@NonNull LocationResult locationResult) {
                                                            super.onLocationResult(locationResult);
                                                            mLastKnownLocation = locationResult.getLastLocation();



                                                            moveCamera(new LatLng(location.getLatitude(), location.getLongitude()), DEFAULT_ZOOM, "My Location");

                                                            mFusedLocationProviderClient.removeLocationUpdates(this);
                                                        }
                                                    };
                                                    mFusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                                            locationCallback, null);

                                                }
                                            }
                                        });
                            }
                        } catch (SecurityException e) {
                            Log.e("Exception: %s", e.getMessage());
                        }
                    }
                });
            }
        };
    }


    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {


    }


    private void moveCamera(LatLng latLng, float zoom, String title){
        assert mLastKnownLocation != null;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(mLastKnownLocation.getLatitude(),
                        mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
        if (!title.equals("You are here...!!")){
            MarkerOptions markerOptions=new MarkerOptions().position(latLng).title(title);
            mMap.addMarker(markerOptions);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        }

    }

}