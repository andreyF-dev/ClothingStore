package com.andreyjig.clothingstore.ui.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.product.Image;
import com.squareup.picasso.Picasso;

public class ImageHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_image_view);
    }

    public void bind(Image image) {
        String imageUrl = image.getBig();
        Picasso.get()
                .load(imageUrl)
                .noPlaceholder()
                .error(R.drawable.ic_photo)
                .into(imageView);
    }
}
