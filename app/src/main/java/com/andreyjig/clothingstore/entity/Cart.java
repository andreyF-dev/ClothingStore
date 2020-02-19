package com.andreyjig.clothingstore.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class Cart implements RealmModel {
    @SerializedName("items")
    @Expose
    private RealmList<ItemCard> items = null;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public List<ItemCard> getItems() {
        return items;
    }

    public void setItems(RealmList<ItemCard> items) {
        this.items = items;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart_{" +
                "items=" + items.toString() +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
