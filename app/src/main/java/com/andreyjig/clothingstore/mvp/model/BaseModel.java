package com.andreyjig.clothingstore.mvp.model;

import com.andreyjig.clothingstore.database.RealmHelper;
import com.andreyjig.clothingstore.mvp.model.handler.DataHandler;

public abstract class BaseModel<T> {

    public static RealmHelper realmHelper = RealmHelper.getInstance();

    abstract public void downloadData(DataHandler<T> handler);
    abstract public T getCachedData();
    abstract public void setDataToCache(T data);

}
