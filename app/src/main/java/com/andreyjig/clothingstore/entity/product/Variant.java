package com.andreyjig.clothingstore.entity.product;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class Variant implements RealmModel {

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
    private RealmList<Image> photos;
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

    public List<Image> getPhotos() {
        return photos;
    }

    public void setPhotos(RealmList<Image> photos) {
        this.photos = photos;
    }

}
