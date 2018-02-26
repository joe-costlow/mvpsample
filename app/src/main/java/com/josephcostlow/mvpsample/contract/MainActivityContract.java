package com.josephcostlow.mvpsample.contract;

import com.josephcostlow.mvpsample.BasePresenter;
import com.josephcostlow.mvpsample.BaseView;
import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 18-Feb-18.
 */

public interface MainActivityContract {

    interface View extends BaseView<Presenter> {
        void displayResult(String input);
        void displayInputError();
        void setupAdapter(List<ListItem> dataList);
        void showRecycler();
        void showEmptyRecycler();
    }

    interface Presenter extends BasePresenter {
        void onSearchButtonClick(String baseUrl, String input);
    }
}
