package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

/**
 * Created by Joseph Costlow on 23-Jan-18.
 */

public interface Repository {
    List<ListItem> getListOfData();
    ListItem addItemToList(String input);
}
