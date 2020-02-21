package com.andreyjig.clothingstore.database;

import com.andreyjig.clothingstore.entity.Cart;
import com.andreyjig.clothingstore.entity.Product;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmProductHelper {
    private static RealmProductHelper instance;
    private Realm realm;

    public static RealmProductHelper getInstance(){
        if (instance == null) {
            instance = new RealmProductHelper();
        }
        return instance;
    }

    private RealmProductHelper(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("product.realm")
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

    public void close(){
        realm.close();
        instance = null;
    }
}
