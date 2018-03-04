package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 23-Jan-18.
 */

public class RepositoryImpl implements Repository {

    private static RepositoryImpl INSTANCE = null;
    private final Repository remoteDataSource;
    private final Repository inMemoryDataSource;

    private RepositoryImpl(Repository remoteSource, Repository inMemorySource) {
        remoteDataSource = remoteSource;
        inMemoryDataSource = inMemorySource;
    }

    public static RepositoryImpl getInstance(Repository remoteSource, Repository inMemorySource) {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryImpl(remoteSource, inMemorySource);
        }

        return INSTANCE;
    }

    @Override
    public void loadData(String base, final String input, final LoadListCallback callback) {
        remoteDataSource.loadData(base, input, new LoadListCallback() {
            @Override
            public void onLoaded(List<ListItem> list, String input) {
                storeData(input, list);
                callback.onLoaded(list, input);
            }

            @Override
            public void onListNotAvailable() {
                callback.onLoaded(restoreRepositories(), restoreAuthor());
            }
        });
    }

    @Override
    public void storeData(String input, List<ListItem> loadedData) {
        inMemoryDataSource.storeData(input, loadedData);
    }

    @Override
    public String restoreAuthor() {
        return inMemoryDataSource.restoreAuthor();
    }

    @Override
    public List<ListItem> restoreRepositories() {
        return inMemoryDataSource.restoreRepositories();
    }
}
