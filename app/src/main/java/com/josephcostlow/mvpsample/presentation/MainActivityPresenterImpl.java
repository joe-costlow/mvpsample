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
            dataSource.loadData(baseUrl, userTextInput, new Repository.LoadListCallback() {
                @Override
                public void onLoaded(List<ListItem> list, String input) {
                    view.displayResult(input);
                    loadReturnedData(list);
                }

                @Override
                public void onListNotAvailable() {
                    view.showEmptyRecycler();
                }
            });
        } else {
            view.displayInputError();
        }
    }

    private void loadReturnedData(List<ListItem> list) {
        if (list.size() != 0) {
            view.showRecycler();
            view.setupAdapter(list);
        } else {
            view.showEmptyRecycler();
        }
    }

    private boolean validateInput(String result) {
        return result.length() > 0;
    }

    @Override
    public void start() {
        if (dataSource.restoreAuthor() != null && dataSource.restoreRepositories() != null) {
            view.displayResult(dataSource.restoreAuthor());
            loadReturnedData(dataSource.restoreRepositories());
        }
    }
}
