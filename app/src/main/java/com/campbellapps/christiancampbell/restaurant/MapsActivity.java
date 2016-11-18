package com.campbellapps.christiancampbell.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, DirectionFinderListener {

    Button directions;
    LatLng loc;
    String address;
    private GoogleMap mMap;
    private ProgressDialog progressDialog;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    Place currentPlace = null;

    //Google Directions API Key


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        directions = (Button) findViewById(R.id.buttonDawg);

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

         //fragment listener variable.

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

        Intent intent = getIntent();
        address = intent.getStringExtra("address");

        mMap = googleMap;
        mMap.getUiSettings().isMyLocationButtonEnabled();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {

        }

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {

            loc = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 18));




        }
    };

    private void sendRequest() {
        Double lat = loc.latitude;
        Double lng = loc.longitude;

        String lati = lat.toString();
        String longi = lng.toString();
        String origin = lati + "," + longi;

        String destination = address;

        try{
            new DirectionFinder(this, origin, destination).execute();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding directions!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route3 : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route3.startLocation, 16));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.destination)).position(route3.endLocation)));


            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(getApplicationContext().getResources().getColor(R.color.appColor)).
                    width(30);

            for (int i = 0; i < route3.points.size(); i++)
                polylineOptions.add(route3.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }
}

