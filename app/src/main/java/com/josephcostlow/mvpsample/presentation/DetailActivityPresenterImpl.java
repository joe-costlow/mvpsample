package com.josephcostlow.mvpsample.presentation;

import com.josephcostlow.mvpsample.contract.DetailActivityContract;
import com.josephcostlow.mvpsample.repository.RepositoryImpl;

/**
 * Created by Joseph Costlow on 29-Jan-18.
 */

public class DetailActivityPresenterImpl implements DetailActivityContract.Presenter {

    DetailActivityContract.View view;
    private final RepositoryImpl dataSource;

    public DetailActivityPresenterImpl(DetailActivityContract.View view, RepositoryImpl repository) {
        this.view = view;
        this.dataSource = repository;

        view.setPresenter(this);
    }


    @Override
    public void getIntent() {
        view.getClickedItemIntent();
    }

    @Override
    public void utilizeIntent(String author, int position) {
        view.setTitle(author);
        String repository = dataSource.restoreRepositories().get(position).getName();
        view.setDetailText(repository);
    }

    @Override
    public void start() {
        getIntent();
    }
}
