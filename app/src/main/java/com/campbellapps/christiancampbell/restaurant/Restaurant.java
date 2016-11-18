package com.campbellapps.christiancampbell.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rodneytressler on 11/15/16.
 */

public class Restaurant implements Parcelable{

    String name;

    String address;

    String food;

    String county;


    public Restaurant() {

    }

    public Restaurant(String name, String address, String food, String county) {
        this.name = name;
        this.address = address;
        this.food = food;
        this.county = county;

    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        food = in.readString();
        county = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(food);
        parcel.writeString(county);
    }
}
