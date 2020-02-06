package com.andreyjig.clothingstore.model.product;

import android.graphics.Color;

public class ProductColor {

    private String name;
    private int code;

    public void setCode(String stringCode){
        code = Color.parseColor(stringCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
