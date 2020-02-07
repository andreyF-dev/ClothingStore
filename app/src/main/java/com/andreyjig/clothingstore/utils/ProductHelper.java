package com.andreyjig.clothingstore.utils;

import android.util.Log;

import com.andreyjig.clothingstore.model.Product;
import com.andreyjig.clothingstore.model.product.Color;
import com.andreyjig.clothingstore.model.product.Size;
import com.andreyjig.clothingstore.model.product.Variant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ProductHelper {

    public static ArrayList<Color> getAllColor (Product product){

        HashMap<Integer, Color> colors = new HashMap<>();
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            colors.put(variant.getColorId(), variant.getColor());
        }
        Collection<Color> colorArrayList = colors.values();
        return new ArrayList<>(colorArrayList);
    }

    public static ArrayList<String> getColorString(ArrayList<Color> colors){
        ArrayList<String> strings = new ArrayList<>();
        for (Color color: colors){
            strings.add(color.getName());
        }
        return strings;
    }

    public static String getColorCode(ArrayList<Color> colors, int id){
        for (Color color: colors){
            if (color.getId() == id){
                return color.getHashCode();
            }
        }
        return null;
    }

    public static ArrayList<Integer> getColorsId(ArrayList<Color> colors){
        ArrayList<Integer> integers = new ArrayList<>();
        for (Color color: colors){
            integers.add(color.getId());
        }
        return integers;
    }
    public static ArrayList<Size> getSizes (Product product, int colorId){

        ArrayList<Size> sizes = new ArrayList<>();
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            if (colorId == variant.getColorId()){
                sizes.add(variant.getSize());
            }
        }
        return sizes;
    }

    public static ArrayList<Integer> getSizesId (ArrayList<Size> sizes){
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Size size: sizes){
            numbers.add(size.getId());
        }
        return numbers;
    }

    public static ArrayList<String> getSizesString(ArrayList<Size> sizes){
        ArrayList<String> strings = new ArrayList<>();
        for (Size size: sizes){
            strings.add(size.getName());
        }
        return strings;
    }

    public static Variant getVariant(Product product, int colorId, int sizeId){
        ArrayList<Variant> variants = product.getVariants();
        for (Variant variant: variants){
            Log.d("MyRetrofit", "colorId = " + colorId + " sizeId = " + sizeId +
                    " variant " + variant.getColorId() + " " + variant.getSizeId());
            if (colorId == variant.getColorId() && sizeId == variant.getSizeId()){
                return variant;
            }
        }
        return null;
    }
}
