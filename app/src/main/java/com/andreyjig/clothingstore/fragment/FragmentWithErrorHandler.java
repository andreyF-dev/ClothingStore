package com.andreyjig.clothingstore.fragment;

import android.view.View;
import com.andreyjig.clothingstore.R;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.google.android.material.snackbar.Snackbar;

public class FragmentWithErrorHandler extends MvpAppCompatFragment{

    private Snackbar snackbar;

    @Override
    public void onDetach() {
        super.onDetach();
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    public void setErrorDialog(View.OnClickListener listener) {
        String text = getString(R.string.error_download);
        snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.download_now), listener);
        int color = getResources().getColor(R.color.colorPrimary);
        snackbar.setActionTextColor(color);
        snackbar.show();
    }
}
