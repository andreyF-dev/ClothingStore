package com.andreyjig.clothingstore.database;

import com.andreyjig.clothingstore.entity.Cart;
import com.andreyjig.clothingstore.entity.Product;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmHelper {
    private static RealmHelper instance;
    private Realm realm;

    public static RealmHelper getInstance(){
        if (instance == null) {
            instance = new RealmHelper();
        }
        return instance;
    }

    private RealmHelper(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("cart.realm")
                .schemaVersion(1)
                .build();
        realm = Realm.getInstance(config);
    }

    public Product getCachedProduct(int id){
        return realm.where(Product.class).equalTo("id", id).findFirst();
    }

    public void setCashedProduct(Product product){
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(product);
        });
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
