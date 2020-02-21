package com.andreyjig.clothingstore.database;

import com.andreyjig.clothingstore.entity.Cart;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmCartHelper {

    private static RealmCartHelper instance;
    private Realm realm;

    public static RealmCartHelper getInstance(){
        if (instance == null) {
            instance = new RealmCartHelper();
        }
        return instance;
    }

    private RealmCartHelper(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("cart.realm")
                .schemaVersion(1)
                .build();
        realm = Realm.getInstance(config);
    }

    public Cart getCachedCart(){
        return realm.where(Cart.class).findFirst();
    }

    public void setCashedCart(Cart cart){
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(cart);
        });
    }

    public void close(){
        realm.close();
        instance = null;
    }
}
