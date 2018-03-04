package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Joseph Costlow on 19-Feb-18.
 */

public class RemoteDataSource implements Repository {

    private static RemoteDataSource INSTANCE = null;

    private RemoteDataSource() {
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void loadData(String base, final String input, final LoadListCallback callback) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<ListItem>> call = client.getUserRepos(input);
        call.enqueue(new Callback<List<ListItem>>() {
            @Override
            public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                callback.onLoaded(response.body(), input);
            }

            @Override
            public void onFailure(Call<List<ListItem>> call, Throwable t) {
                callback.onListNotAvailable();
            }
        });
    }

    @Override
    public void storeData(String input, List<ListItem> loadedData) {

    }

    @Override
    public String restoreAuthor() {
        return null;
    }

    @Override
    public List<ListItem> restoreRepositories() {
        return null;
    }
}
