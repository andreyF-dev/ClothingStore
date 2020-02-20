package com.andreyjig.clothingstore.entity.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class Color implements RealmModel {

    @SerializedName("hash_code")
    @Expose
    private String hashCode;
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

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(hashCode, color.hashCode) &&
                Objects.equals(id, color.id) &&
                Objects.equals(name, color.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCode, id, name);
    }
}
