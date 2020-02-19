package com.andreyjig.clothingstore.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Cart {
    @SerializedName("items")
    @Expose
    private List<ItemCard> items = null;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public List<ItemCard> getItems() {
        return items;
    }

    public void setItems(List<ItemCard> items) {
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
