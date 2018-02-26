package com.josephcostlow.mvpsample.contract;

import com.josephcostlow.mvpsample.BasePresenter;
import com.josephcostlow.mvpsample.BaseView;

/**
 * Created by Joseph Costlow on 26-Feb-18.
 */

public interface DetailActivityContract {

    interface View extends BaseView<Presenter> {
        String getClickedItemIntent();
        void setDetailText(String intentExtra);
    }

    interface Presenter extends BasePresenter {
        String getIntent();
        void utilizeIntent(String intentExtra);
    }
}
