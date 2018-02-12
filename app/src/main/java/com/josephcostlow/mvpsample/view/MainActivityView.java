package com.josephcostlow.mvpsample.view;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 22-Jan-18.
 */

public interface MainActivityView {
    String getInput();
    void displayResult(String input);
    void displayInputError();
    void setupAdapter(List<ListItem> dataList);
    void updateRecycler(ListItem listItem);
    void showRecycler();
    void showEmptyRecycler();
}
