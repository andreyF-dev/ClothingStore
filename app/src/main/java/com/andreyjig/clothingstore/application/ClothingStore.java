package com.andreyjig.clothingstore.application;

import android.app.Application;

import io.realm.Realm;

public class ClothingStore extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
