package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph Costlow on 23-Jan-18.
 */

public class RepositoryImpl implements Repository {

    ListItem listItem;

    public RepositoryImpl() {
    }

    @Override
    public List<ListItem> getListOfData() {
        ArrayList<ListItem> listOfData = new ArrayList<>();
        listItem = addItemToList("example");
        listOfData.add(listItem);
        return listOfData;
    }

    @Override
    public ListItem addItemToList(String input) {
        listItem = new ListItem(input);
        return listItem;
    }
}
