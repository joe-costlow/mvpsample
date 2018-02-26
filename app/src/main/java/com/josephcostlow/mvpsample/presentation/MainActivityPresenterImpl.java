package com.josephcostlow.mvpsample.presentation;

import com.josephcostlow.mvpsample.contract.MainActivityContract;
import com.josephcostlow.mvpsample.model.ListItem;
import com.josephcostlow.mvpsample.repository.Repository;
import com.josephcostlow.mvpsample.repository.RepositoryImpl;

import java.util.List;

/**
 * Created by Joseph Costlow on 22-Jan-18.
 */

public class MainActivityPresenterImpl implements MainActivityContract.Presenter {

    private final MainActivityContract.View view;
    private final RepositoryImpl dataSource;

    public MainActivityPresenterImpl(MainActivityContract.View currentView, RepositoryImpl repository) {
        view = currentView;
        dataSource = repository;

        view.setPresenter(this);
    }

    @Override
    public void onSearchButtonClick(String baseUrl, String userTextInput) {

        boolean isValid = validateInput(userTextInput);

        if (isValid) {
            view.displayResult(userTextInput);
            view.showRecycler();
            dataSource.loadData(baseUrl, userTextInput, new Repository.LoadListCallback() {
                @Override
                public void onLoaded(List<ListItem> list) {
                    loadReturnedData(list);
                }

                @Override
                public void onListNotAvailable() {

                }
            });
        } else {
            view.displayInputError();
        }
    }

    public void loadReturnedData(List<ListItem> list) {
        view.setupAdapter(list);

        if (list.size() != 0) {
            view.showRecycler();
        } else {
            view.showEmptyRecycler();
        }
    }

    private boolean validateInput(String result) {
        return result.length() > 0;
    }

    @Override
    public void start() {

    }
}
