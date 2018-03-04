package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 03-Mar-18.
 */

public class InMemoryDataSource implements Repository {

    private static InMemoryDataSource INSTANCE;
    private static String author;
    private static List<ListItem> repositories;

    private InMemoryDataSource() {
    }

    public static InMemoryDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void loadData(String base, String input, LoadListCallback callback) {
        callback.onLoaded(repositories, author);
    }

    @Override
    public void storeData(String input, List<ListItem> loadedData) {
        author = input;
        repositories = loadedData;
    }

    @Override
    public String restoreAuthor() {
        return author;
    }

    @Override
    public List<ListItem> restoreRepositories() {
        return repositories;
    }
}
