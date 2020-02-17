package com.andreyjig.clothingstore.fragment;

import android.util.Log;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.presenters.ErrorHandlerPresenter;
import com.andreyjig.clothingstore.presenters.TitleHandlerPresenter;
import com.andreyjig.clothingstore.views.ErrorHandlerView;
import com.andreyjig.clothingstore.views.TitleHandlerView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseHandlerFragment extends MvpAppCompatFragment
        implements ErrorHandlerView, TitleHandlerView {

    @InjectPresenter
    ErrorHandlerPresenter errorHandlerPresenter;

    @InjectPresenter
    TitleHandlerPresenter titleHandlerPresenter;

    private Snackbar snackbar;

    @Override
    public void onDetach() {
        super.onDetach();
        hideErrorDialog();
    }

    @Override
    public void setShowErrorDialog(String errorText) {
        errorHandlerPresenter.setErrorDialog(errorText);
    }

    @Override
    public void showErrorDialog(String errorText) {
        if (getView() != null) {
            String text = getString(R.string.error_download) + " " + errorText;
            snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.download_now), v -> {
                errorDialogOnClick();
            });
            int color = getResources().getColor(R.color.colorPrimary);
            snackbar.setActionTextColor(color);
            snackbar.show();
        }
    }

    @Override
    public void setHideErrorDialog() {
        errorHandlerPresenter.setHideErrorDialog();
    }

    @Override
    public void hideErrorDialog() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    @Override
    public void setTitle(int id) {
        titleHandlerPresenter.setTitle(getString(id));
    }

    @Override
    public void setTitle(String title) {
        titleHandlerPresenter.setTitle(title);
    }

    @Override
    public void updateTitle(String title) {
        Log.d("Retrofit", "update Title");
        if (getActivity() != null){
            getActivity().setTitle(title);
        }
    }

    public abstract void errorDialogOnClick();
}
