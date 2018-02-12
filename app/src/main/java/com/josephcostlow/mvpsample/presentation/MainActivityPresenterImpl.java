package com.josephcostlow.mvpsample.presentation;

import com.josephcostlow.mvpsample.model.ListItem;
import com.josephcostlow.mvpsample.repository.RepositoryImpl;
import com.josephcostlow.mvpsample.view.MainActivityView;

import java.util.List;

/**
 * Created by Joseph Costlow on 22-Jan-18.
 */

public class MainActivityPresenterImpl implements MainActivityPresenter {

    MainActivityView view;
    RepositoryImpl dataSource;


    public MainActivityPresenterImpl(MainActivityView view, RepositoryImpl dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getInitialData();
    }

    private void getInitialData() {

        List<ListItem> list =  dataSource.getListOfData();
        view.setupAdapter(list);

        if (list.size() != 0) {
            view.showRecycler();
        } else {
            view.showEmptyRecycler();
        }

    }

    @Override
    public void onAddButtonClick() {
        String result = view.getInput();
        boolean isValid = validateInput(result);

        if (isValid) {
            view.displayResult(result);
            ListItem listItem = dataSource.addItemToList(result);
            view.showRecycler();
            view.updateRecycler(listItem);
        } else {
            view.displayInputError();
        }
    }

    private boolean validateInput(String result) {
        return result.length() > 0;
    }
}
