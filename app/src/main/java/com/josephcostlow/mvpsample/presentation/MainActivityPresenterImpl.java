package com.josephcostlow.mvpsample.presentation;

import com.josephcostlow.mvpsample.model.ListItem;
import com.josephcostlow.mvpsample.repository.RepositoryImpl;
import com.josephcostlow.mvpsample.view.MainActivityView;

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
        view.setupAdapter(dataSource.getListOfData());
    }

    @Override
    public void onAddButtonClick() {
        String result = view.getInput();
        boolean isValid = validateInput(result);

        if (isValid) {
            view.displayResult(result);
            ListItem listItem = dataSource.addItemToList(result);
            view.updateRecycler(listItem);
        } else {
            view.displayError();
        }
    }

    private boolean validateInput(String result) {
        return result.length() > 0;
    }
}
