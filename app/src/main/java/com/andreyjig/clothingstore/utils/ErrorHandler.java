package com.andreyjig.clothingstore.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.andreyjig.clothingstore.R;
import com.google.android.material.snackbar.Snackbar;

public class ErrorHandler {

    private Snackbar snackbar;

    public ErrorHandler(Context context, View.OnClickListener listener){
        snackbar = getErrorDialog(context, listener);
    }

    private Snackbar getErrorDialog(Context context, View.OnClickListener listener){
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        String text = context.getString(R.string.error_download);
        Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_INDEFINITE)
                .setAction(context.getString(R.string.download_now), listener);
        int color = context.getResources().getColor(R.color.colorPrimary);
        snackbar.setActionTextColor(color);
        return snackbar;
    }

    public void show(){
        snackbar.show();
    }

    public void close(){
        snackbar.dismiss();
    }

    public boolean isShown(){
        return snackbar.isShown();
    }
}
