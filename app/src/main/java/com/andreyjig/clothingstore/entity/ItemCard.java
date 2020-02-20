
package com.andreyjig.clothingstore.entity;

import com.andreyjig.clothingstore.entity.product.Variant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class ItemCard implements RealmModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("product_variant_id")
    @Expose
    private Integer productVariantId;
    @SerializedName("product_variant")
    @Expose
    private Variant variant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Integer productVariantId) {
        this.productVariantId = productVariantId;
    }

    public Variant getProductVariant() {
        return variant;
    }

    public void setProductVariant(Variant productVariant) {
        this.variant = productVariant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCard itemCard = (ItemCard) o;
        return Objects.equals(id, itemCard.id) &&
                Objects.equals(count, itemCard.count) &&
                Objects.equals(productId, itemCard.productId) &&
                Objects.equals(product, itemCard.product) &&
                Objects.equals(productVariantId, itemCard.productVariantId) &&
                Objects.equals(variant, itemCard.variant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, productId, product, productVariantId, variant);
    }
}
