package com.andreyjig.clothingstore.fragment;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.fragment.model.ErrorHandlerInterface;
import com.andreyjig.clothingstore.fragment.presenters.ErrorHandlerPresenter;
import com.andreyjig.clothingstore.fragment.views.ErrorHandlerView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;

public class FragmentWithErrorHandler extends MvpAppCompatFragment implements ErrorHandlerView{

    @InjectPresenter
    ErrorHandlerPresenter errorHandlerPresenter;
    private Snackbar snackbar;

    @Override
    public void onDetach() {
        super.onDetach();
        setHideErrorDialog();
    }

    @Override
    public void getShowErrorDialog(ErrorHandlerInterface listener) {
        errorHandlerPresenter.setErrorDialog(listener);
    }

    @Override
    public void setShowErrorDialog(ErrorHandlerInterface listener) {
        String text = getString(R.string.error_download);
        snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getString(R.string.download_now), v -> {
            listener.getErrorMethod();
            errorHandlerPresenter.onClick();
        });
        int color = getResources().getColor(R.color.colorPrimary);
        snackbar.setActionTextColor(color);
        snackbar.show();
    }

    @Override
    public void getHideErrorDialog() {
        errorHandlerPresenter.setHideErrorDialog();
    }

    @Override
    public void setHideErrorDialog() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }


}
