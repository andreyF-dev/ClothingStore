
package com.andreyjig.clothingstore.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color extends Properties{

    @SerializedName("hash_code")
    @Expose
    private String hashCode;

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public String toString() {
        return name;
    }
}
