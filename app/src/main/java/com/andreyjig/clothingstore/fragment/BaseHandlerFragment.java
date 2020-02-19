package com.andreyjig.clothingstore.fragment;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.presenter.BaseHandlerPresenter;
import com.andreyjig.clothingstore.view.BaseHandlerView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseHandlerFragment extends MvpAppCompatFragment
        implements BaseHandlerView{

    @InjectPresenter
    BaseHandlerPresenter baseHandlerPresenter;

    private Snackbar snackbar;

    @Override
    public void onDetach() {
        super.onDetach();
        hideErrorDialog();
    }

    @Override
    public void setShowErrorDialog(int errorMessageId) {
        baseHandlerPresenter.setErrorDialog(errorMessageId);
    }

    @Override
    public void showErrorDialog(int errorMessageId) {
        if (getView() != null) {
            String text = getString(errorMessageId);
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
        baseHandlerPresenter.setHideErrorDialog();
    }

    @Override
    public void hideErrorDialog() {
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }

    @Override
    public void setTitle(int id) {
        baseHandlerPresenter.setTitle(getString(id));
    }

    @Override
    public void setTitle(String title) {
        baseHandlerPresenter.setTitle(title);
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() != null){
            getActivity().setTitle(title);
        }
    }

    public abstract void errorDialogOnClick();
}
