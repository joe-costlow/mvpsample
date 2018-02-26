package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 23-Jan-18.
 */

public class RepositoryImpl implements Repository {

    private static RepositoryImpl INSTANCE = null;
    private final Repository remoteDataSource;

    private RepositoryImpl(Repository remoteSource) {
        remoteDataSource = remoteSource;
    }

    public static RepositoryImpl getInstance(Repository remoteSource) {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryImpl(remoteSource);
        }

        return INSTANCE;
    }

    @Override
    public void loadData(String base, String input, final LoadListCallback callback) {
        remoteDataSource.loadData(base, input, new LoadListCallback() {
            @Override
            public void onLoaded(List<ListItem> list) {
                callback.onLoaded(list);
            }

            @Override
            public void onListNotAvailable() {

            }
        });
    }
}
