package com.andreyjig.clothingstore.entity.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class Properties {

    @SerializedName("id")
    @Expose
    protected Integer id;
    @SerializedName("name")
    @Expose
    protected String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
