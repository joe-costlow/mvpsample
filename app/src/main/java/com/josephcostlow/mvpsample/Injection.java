package com.josephcostlow.mvpsample;

import com.josephcostlow.mvpsample.repository.InMemoryDataSource;
import com.josephcostlow.mvpsample.repository.RemoteDataSource;
import com.josephcostlow.mvpsample.repository.RepositoryImpl;

/**
 * Created by Joseph Costlow on 19-Feb-18.
 */

public class Injection {

    public static RepositoryImpl provideRepository() {
        return RepositoryImpl.getInstance(RemoteDataSource.getInstance(), InMemoryDataSource.getInstance());
    }
}
