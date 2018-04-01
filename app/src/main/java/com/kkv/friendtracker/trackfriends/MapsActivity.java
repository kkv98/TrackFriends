package com.kkv.friendtracker.trackfriends;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener {

    private GoogleMap mMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng chennai = new LatLng(13.0827, 80.2707);
        marker = mMap.addMarker(new MarkerOptions().position(chennai).title("Chennai"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chennai));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            /*ActivityCompat.requestPermissions(MapsActivity.this, new String[]
                    {
                            ACCESS_NETWORK_STATE,ACCESS_WIFI_STATE,INTERNET,ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION
                    }, 7);*/

            return;
        }
        mMap.setOnMyLocationChangeListener(this);
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onMyLocationChange(Location location) {
        double lon = location.getLongitude();
        double lat = location.getLatitude();
        LatLng current=new LatLng(lon,lat);
       // mMap.addMarker(new MarkerOptions().position(current).title("My Location"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
