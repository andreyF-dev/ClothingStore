package com.andreyjig.clothingstore.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.product.Image;
import com.andreyjig.clothingstore.ui.adapter.holder.ImageHolder;
import com.andreyjig.clothingstore.ui.adapter.holder.NoImageHolder;
import com.andreyjig.clothingstore.ui.adapter.model.NoImage;
import java.util.ArrayList;

public class ProductDescriptionImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_IMAGE = 0;
    private final int TYPE_NO_IMAGE = 1;
    private Context context;
    private ArrayList<Object> adapterList;

    public ProductDescriptionImageAdapter(Context context, ArrayList<Image> images) {
        this.context = context;
        createAdapterList(images);
    }

    private void createAdapterList(ArrayList<Image> images){
        adapterList = new ArrayList<>();
        if (images != null && images.size() > 0){
            adapterList.addAll(images);
        } else {
            adapterList.add(new NoImage());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_IMAGE:
                view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_image, parent, false);
                return new ImageHolder(view, context);
            case TYPE_NO_IMAGE:
                view = LayoutInflater.from(context).inflate(R.layout.item_price, parent, false);
                return new NoImageHolder(view, context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_IMAGE:
                ((ImageHolder)holder).bind((Image) adapterList.get(position));
                break;
            case TYPE_NO_IMAGE:
                ((NoImageHolder)holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        if (adapterList != null){
            return adapterList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Object object = adapterList.get(position);
        if (object instanceof Image){
            return TYPE_IMAGE;
        } else if (object instanceof NoImage) {
            return TYPE_NO_IMAGE;
        }
        return 0;
    }
}
