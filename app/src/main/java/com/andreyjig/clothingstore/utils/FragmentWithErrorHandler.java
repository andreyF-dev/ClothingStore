package com.andreyjig.clothingstore.utils;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.andreyjig.clothingstore.R;
import com.google.android.material.snackbar.Snackbar;

public class FragmentWithErrorHandler extends Fragment {

    private Snackbar snackbar;

    public void getErrorDialog(View.OnClickListener listener){
        String text = getContext().getString(R.string.error_download);
        snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_INDEFINITE)
                .setAction(getContext().getString(R.string.download_now), listener);
        int color = getContext().getResources().getColor(R.color.colorPrimary);
        snackbar.setActionTextColor(color);
        snackbar.show();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }
}
