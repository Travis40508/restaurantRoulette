package com.campbellapps.christiancampbell.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    //sets up list to apply the adapter onto.
    ArrayList<Restaurant> list = new ArrayList<>();

    //Sets up variable to make sure only button clicked information comes through.
    String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        choice = intent.getStringExtra("choice");

        getExtras();
    }


    //Sets up on start animations.
    @Override
    protected void onStart() {
        super.onStart();
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                Intent intent = new Intent(ListActivity.this, MapsActivity.class);
                Restaurant restaurant = list.get(position);
                intent.putExtra("address", restaurant.getAddress());
                startActivity(intent);
            }
        });
    }

    //sets up backPress animations.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
