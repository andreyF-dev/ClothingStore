package com.andreyjig.clothingstore.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {

    private String name;
    private String articleNumber;
    private String manufacturer;
    private String description;
    private String previewPhotoUrl;
    private ArrayList<String> photosUrl;
    private HashMap<PriceProperties, Double> prices;

    public Product() {
    }

    public Product(String name, String articleNumber, String manufacturer, String description, String previewPhotoUrl, ArrayList<String> photosUrl, HashMap<PriceProperties, Double> prices) {
        this.name = name;
        this.articleNumber = articleNumber;
        this.manufacturer = manufacturer;
        this.description = description;
        this.previewPhotoUrl = previewPhotoUrl;
        this.photosUrl = photosUrl;
        this.prices = prices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPreviewPhotoUrl() {
        return previewPhotoUrl;
    }

    public void setPreviewPhotoUrl(String previewPhotoUrl) {
        this.previewPhotoUrl = previewPhotoUrl;
    }

    public ArrayList<String> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(ArrayList<String> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public HashMap<PriceProperties, Double> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<PriceProperties, Double> prices) {
        this.prices = prices;
    }
}
