package com.campbellapps.christiancampbell.restaurant;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //Declaring variables.

    ImageView magoffin;
    ImageView johnson;
    ImageView floyd;
    ImageView pike;
    ImageView help;
    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes variables by county.
        magoffin = (ImageView) findViewById(R.id.magoffin_county);
        johnson = (ImageView) findViewById(R.id.johnson_county);
        floyd = (ImageView) findViewById(R.id.floyd_county);
        pike = (ImageView) findViewById(R.id.pike_county);
        help = (ImageView) findViewById(R.id.help);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.blip1);

        //Calls clicking methods
        magoffinClick();
        johnsonClick();
        floydClick();
        pikeClick();
        helpClick();
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    //Sets permissions
    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            if (!(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
            if (!(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //sets county in populateArray() equal to "magoffin" so that it only searches Magoffin restaurants.
    // starts method that sets everything in motion.
    public void magoffinClick() {
        magoffin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra("county", "magoffin");
                startActivity(intent);
            }
        });
    }

    //sets county in populateArray() equal to "johnson" so that it only searches Johnson restaurants.
    // starts method that sets everything in motion.
    public void johnsonClick() {
        johnson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra("county", "johnson");
                startActivity(intent);
            }
        });
    }

    //sets county in populateArray() equal to "floyd" so that it only searches Floyd restaurants.
    // starts method that sets everything in motion.
    public void floydClick() {
        floyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra("county", "floyd");
                startActivity(intent);
            }
        });
    }

    public void pikeClick() {
        pike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                intent.putExtra("county", "pike");
                startActivity(intent);
            }
        });
    }

    public void helpClick() {
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this,
                R.style.Theme_AppInvite_Preview_Base);
                alertBuilder.setNegativeButton("Close", null);
                alertBuilder.setTitle("Hungry?");
                alertBuilder.setMessage("1. Pick the County You're eating in." + "\n" +
                "2. Pick the food you're craving." + "\n" +
                "3. Pick the restaurant you wish to eat at." + "\n" +
                "4. Follow the directions to your restaurant!" + "\n" +
                "It's that easy! Enjoy, and Happy Eating!");
                alertBuilder.create().show();
            }
        });
    }
}
