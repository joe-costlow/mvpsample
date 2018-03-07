package com.josephcostlow.mvpsample.contract;

import com.josephcostlow.mvpsample.BasePresenter;
import com.josephcostlow.mvpsample.BaseView;

/**
 * Created by Joseph Costlow on 26-Feb-18.
 */

public interface DetailActivityContract {

    interface View extends BaseView<Presenter> {
        void getClickedItemIntent();
        void setDetailText(String intentExtra);
        void setTitle(String author);
    }

    interface Presenter extends BasePresenter {
        void getIntent();
        void utilizeIntent(String author, int position);
    }
}
