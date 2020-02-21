package com.andreyjig.clothingstore.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.andreyjig.clothingstore.R;

public class NoImageHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private Context context;

    public NoImageHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        imageView = itemView.findViewById(R.id.item_image_view);
    }

    public void bind(){
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_photo));
    }
}
