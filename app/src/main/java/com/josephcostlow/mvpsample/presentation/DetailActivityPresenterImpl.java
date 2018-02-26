package com.josephcostlow.mvpsample.presentation;

import com.josephcostlow.mvpsample.contract.DetailActivityContract;

/**
 * Created by Joseph Costlow on 29-Jan-18.
 */

public class DetailActivityPresenterImpl implements DetailActivityContract.Presenter {

    DetailActivityContract.View view;

    public DetailActivityPresenterImpl(DetailActivityContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }


    @Override
    public String getIntent() {
        return view.getClickedItemIntent();
    }

    @Override
    public void utilizeIntent(String intentExtra) {
        view.setDetailText(intentExtra);
    }

    @Override
    public void start() {

    }
}
