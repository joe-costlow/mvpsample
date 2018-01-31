package com.josephcostlow.mvpsample.presentation;

import com.josephcostlow.mvpsample.view.DetailActivityView;

/**
 * Created by Joseph Costlow on 29-Jan-18.
 */

public class DetailActivityPresenterImpl implements DetailActivityPresenter {

    DetailActivityView view;

    public DetailActivityPresenterImpl(DetailActivityView view) {
        this.view = view;
    }


    @Override
    public String getIntent() {
        return view.getClickedItemIntent();
    }

    @Override
    public void utilizeIntent(String intentExtra) {
        view.setDetailText(intentExtra);
    }
}
