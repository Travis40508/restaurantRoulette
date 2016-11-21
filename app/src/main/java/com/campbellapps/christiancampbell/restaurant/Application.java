package com.campbellapps.christiancampbell.restaurant;

import com.firebase.client.Firebase;

/**
 * Created by rodneytressler on 11/16/16.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
