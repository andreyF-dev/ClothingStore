package com.andreyjig.clothingstore.mvp.model;

import com.andreyjig.clothingstore.database.RealmHelper;
import com.andreyjig.clothingstore.mvp.model.handler.DataHandler;

public abstract class BaseModel<T> {

    static RealmHelper realmHelper = RealmHelper.getInstance();

    public abstract void downloadData(DataHandler<T> handler);
    public abstract T getCachedData();
    public abstract void setDataToCache(T data);

}
