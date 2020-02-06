package com.andreyjig.clothingstore.model.product;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {

    private String defaultName;
    private String articleNumber;
    private String manufacturer;
    private String description;
    private ArrayList<ProductImage> photosUrl;
    private HashMap<ProductProperties, Double> prices;

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ProductImage> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(ArrayList<ProductImage> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public HashMap<ProductProperties, Double> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<ProductProperties, Double> prices) {
        this.prices = prices;
    }
}
