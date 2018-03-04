package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 23-Jan-18.
 */

public interface Repository {

    interface LoadListCallback {
        void onLoaded(List<ListItem> list, String input);
        void onListNotAvailable();
    }

    void loadData(String base, String input, LoadListCallback callback);
    void storeData(String input, List<ListItem> loadedData);
    String restoreAuthor();
    List<ListItem> restoreRepositories();
}
