package com.andreyjig.clothingstore.entity;

import java.util.List;
import java.util.Objects;
import com.andreyjig.clothingstore.entity.product.Manufacturer;
import com.andreyjig.clothingstore.entity.product.Material;
import com.andreyjig.clothingstore.entity.product.Variant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Product implements RealmModel {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("manufacturer_id")
    @Expose
    private Integer manufacturerId;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("material_id")
    @Expose
    private Integer materialId;
    @SerializedName("material")
    @Expose
    private Material material;
    @SerializedName("variants")
    @Expose
    private RealmList<Variant> variants = null;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(RealmList<Variant> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer=" + manufacturer +
                ", materialId=" + materialId +
                ", material=" + material +
                ", variants=" + variants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description) &&
                Objects.equals(manufacturerId, product.manufacturerId) &&
                Objects.equals(manufacturer, product.manufacturer) &&
                Objects.equals(materialId, product.materialId) &&
                Objects.equals(material, product.material) &&
                Objects.equals(variants, product.variants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, manufacturerId, manufacturer, materialId, material, variants);
    }
}
