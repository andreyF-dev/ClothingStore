package com.andreyjig.clothingstore.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.product.Image;
import com.squareup.picasso.Picasso;

public class ImageHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private Context context;

    public ImageHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        imageView = itemView.findViewById(R.id.item_image_view);
    }

    public void bind(Image image){
        try {
            String imageUrl = image.getBig();
            Picasso.get()
                    .load(imageUrl)
                    .noPlaceholder()
                    .into(imageView);
        } catch (Exception e){
            e.printStackTrace();
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_photo));
        }
    }
}
