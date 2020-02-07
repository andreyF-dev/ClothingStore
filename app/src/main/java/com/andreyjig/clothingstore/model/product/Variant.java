
package com.andreyjig.clothingstore.model.product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant extends Properties{

    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("size_id")
    @Expose
    private Integer sizeId;
    @SerializedName("size")
    @Expose
    private Size size;
    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("photos")
    @Expose
    private ArrayList<Image> photos = null;


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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Image> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Image> photos) {
        this.photos = photos;
    }

}
