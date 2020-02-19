package com.andreyjig.clothingstore.util;

import com.andreyjig.clothingstore.entity.Product;
import com.andreyjig.clothingstore.entity.product.Color;
import com.andreyjig.clothingstore.entity.product.Properties;
import com.andreyjig.clothingstore.entity.product.Size;
import com.andreyjig.clothingstore.entity.product.Variant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductHelper {

    public static ArrayList<Color> getAllColor(Product product){

        HashMap<Integer, Color> colors = new HashMap<>();
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            colors.put(variant.getColorId(), variant.getColor());
        }
        return new ArrayList<>(colors.values());
    }

    public static ArrayList<Size> getAllSizes(Product product, int colorId){
        ArrayList<Size> sizes = new ArrayList<>();
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            if (colorId == variant.getColorId()){
                sizes.add(variant.getSize());
            }
        }
        return sizes;
    }

    public static Variant getVariant(Product product, int colorId, int sizeId){
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            if (colorId == variant.getColorId() && sizeId == variant.getSizeId()){
                return variant;
            }
        }
        return null;
    }

    public static Variant getVariant(Product product, int id){
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            if (id == variant.getId()){
                return variant;
            }
        }
        return null;
    }
    public static int getIndexById(List<Properties> properties, int id){
        for (int index = 0; index < properties.size(); index++){
            if (properties.get(index).getId() == id){
                return index;
            }
        }
        return 0;
    }
}
