package com.campbellapps.christiancampbell.restaurant;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    //sets up list to apply the adapter onto.
    ArrayList<Restaurant> list = new ArrayList<>();

    //Sets up variable to make sure only button clicked information comes through.
    String choice;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        choice = intent.getStringExtra("choice");

        mp = MediaPlayer.create(getApplicationContext(), R.raw.blip1);

        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        getExtras();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }



    //retrieves arraylist from previous activity and adds it to list above to be used with the adapter.
    public void getExtras() {
        Bundle data = getIntent().getExtras();
        list = data.getParcelableArrayList(choice);

        restaurantAdapter adapter = new restaurantAdapter(this, list);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mp.start();
                Intent intent = new Intent(ListActivity.this, MapsActivity.class);
                Restaurant restaurant = list.get(position);
                Toast.makeText(ListActivity.this, "Finding Directions, Please Wait!", Toast.LENGTH_SHORT).show();
                intent.putExtra("address", restaurant.getAddress());
                startActivity(intent);
            }
        });
    }

}
