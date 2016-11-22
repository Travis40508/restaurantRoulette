package com.campbellapps.christiancampbell.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class RestaurantActivity extends AppCompatActivity {
    //Creating instance of Firebase.
    Firebase mRootRef;

    //This will allow us to search by county on ButtonClick.
    String county;

    //Variable declaration;
    ImageView burger;
    ImageView bbq;
    ImageView chicken;
    ImageView chinese;
    ImageView fish;
    ImageView homecooking;
    ImageView italian;
    ImageView mexican;
    ImageView pizza;
    ImageView random;
    ImageView sandwich;
    ImageView taco;
    ImageView steak;


    //Sets ArrayLists based on food preference. These will be used for adapter later on.
    ArrayList<Restaurant> burgerList = new ArrayList<>();
    ArrayList<Restaurant> tacoList = new ArrayList<>();
    ArrayList<Restaurant> mexicanList = new ArrayList<>();
    ArrayList<Restaurant> italianList = new ArrayList<>();
    ArrayList<Restaurant> barbequeList = new ArrayList<>();
    ArrayList<Restaurant> chickenList = new ArrayList<>();
    ArrayList<Restaurant> pizzaList = new ArrayList<>();
    ArrayList<Restaurant> sandwichList = new ArrayList<>();
    ArrayList<Restaurant> chineseList = new ArrayList<>();
    ArrayList<Restaurant> fishList = new ArrayList<>();
    ArrayList<Restaurant> homecookingList = new ArrayList<>();
    ArrayList<Restaurant> steakList = new ArrayList<>();
    ArrayList<Restaurant> randomList = new ArrayList<>();
    ArrayList<Restaurant> randomItem = new ArrayList<>();

    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Intent intent = getIntent();
        county = intent.getStringExtra("county");

        //Sets Firebase
        Firebase.setAndroidContext(this);


        //Initializes variables with layout
        burger = (ImageView) findViewById(R.id.burger);
        bbq = (ImageView) findViewById(R.id.bbq);
        chicken = (ImageView) findViewById(R.id.chicken);
        chinese = (ImageView) findViewById(R.id.chinese);
        fish = (ImageView) findViewById(R.id.fish);
        homecooking = (ImageView) findViewById(R.id.home_cooking);
        italian = (ImageView) findViewById(R.id.italian);
        mexican = (ImageView) findViewById(R.id.mexican);
        pizza = (ImageView) findViewById(R.id.pizza);
        random = (ImageView) findViewById(R.id.random);
        sandwich = (ImageView) findViewById(R.id.sandwich);
        taco = (ImageView) findViewById(R.id.taco);
        steak = (ImageView) findViewById(R.id.steak);


        //Creates an instance of Firebase
        mRootRef = new Firebase("https://restaurantroulette-89089.firebaseio.com/restaurants");
        mRootRef.keepSynced(false);

        //starts grab fire method
        grabFireBase();

        //makes clickable methods function.
        burgerClick();
        tacoClick();
        mexicanClick();
        italianClick();
        barbequeClick();
        chickenClick();
        pizzaClick();
        sandwichClick();
        chineseClick();
        fishClick();
        homecookingClick();
        steakClick();
        randomClick();
        

        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    //Accesses information from firebase instance.
    protected void grabFireBase() {
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Calls method for grabbing info.
                getInfo(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    //Grabs every name, address, county, and food of Firebase objects. Assigns it to our Java model
    public void getInfo(DataSnapshot dataSnapshot) {
        for(DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
            String name = (String) messageSnapshot.child("name").getValue();
            String address = (String) messageSnapshot.child("address").getValue();
            String county = (String) messageSnapshot.child("county").getValue();
            String food = (String) messageSnapshot.child("food").getValue();

            // Takes information retrieved from Firebase and sets Java model equal to it.
            Restaurant restaurant = new Restaurant(name, address, food, county);

            //Starts method to populate arrays based on food preference and county selected earlier.
            populateArrays(restaurant);
        }
    }

    //Sends restaurant object to proper array for restaurant suggestions later on.
    //Only shows restaurants for county, with county being selected on click earlier.
    //also populates random list with every restaurant from said-county to be used on button press.
    public void populateArrays(Restaurant restaurant) {
        if(restaurant.getCounty().equals(county)) {
            switch (restaurant.getFood()) {
                case "burger":
                    burgerList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "taco":
                    tacoList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "mexican":
                    mexicanList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "italian":
                    italianList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "barbeque":
                    barbequeList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "chicken":
                    chickenList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "pizza":
                    pizzaList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "sandwich":
                    sandwichList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "chinese":
                    chineseList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "fish":
                    fishList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "home cooking":
                    homecookingList.add(restaurant);
                    randomList.add(restaurant);
                    break;
                case "steak":
                    steakList.add(restaurant);
                    randomList.add(restaurant);
            }
        }
    }

    //When clicked, takes burger list to populate a listview on the next screen for suggestions.
    public void burgerClick() {
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "burger");
                intent.putExtra("burger", burgerList);

                if(!(burgerList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void tacoClick() {
        taco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "taco");
                intent.putExtra("taco", tacoList);

                if(!(tacoList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void mexicanClick() {
        mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "mexican");
                intent.putExtra("mexican", mexicanList);

                if(!(mexicanList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void italianClick() {
        italian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "italian");
                intent.putExtra("italian", italianList);

                if(!(italianList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void barbequeClick() {
        bbq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "barbeque");
                intent.putExtra("barbeque", barbequeList);

                if(!(barbequeList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void chickenClick() {
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "chicken");
                intent.putExtra("chicken", chickenList);

                if(!(chickenList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void pizzaClick() {
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "pizza");
                intent.putExtra("pizza", pizzaList);

                if(!(pizzaList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sandwichClick() {
        sandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "sandwich");
                intent.putExtra("sandwich", sandwichList);

                if(!(sandwichList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void chineseClick() {
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "chinese");
                intent.putExtra("chinese", chineseList);

                if(!(chineseList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void fishClick() {
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "fish");
                intent.putExtra("fish", fishList);

                if(!(fishList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void homecookingClick() {
        homecooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "homecooking");
                intent.putExtra("homecooking", homecookingList);

                if(!(homecookingList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void steakClick() {
        steak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "steak");
                intent.putExtra("steak", steakList);

                if(!(steakList.size() == 0)){
                    startActivity(intent);}
                else{
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //when clicked generates random restaurant from county to recommend. builds list to give selections.
    public void randomClick() {
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restaurant restaurant;
                Random randomGenerator = new Random();
                int randomNumber = randomGenerator.nextInt(randomList.size());
                restaurant = randomList.get(randomNumber);
                randomItem.add(restaurant);
                Intent intent = new Intent(RestaurantActivity.this, ListActivity.class);
                intent.putExtra("choice", "random");
                intent.putExtra("random", randomItem);

                if(!(randomList.size() == 0)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(RestaurantActivity.this, "Could not find any restaurants of this type. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
