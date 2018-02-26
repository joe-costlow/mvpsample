package com.josephcostlow.mvpsample.repository;

import com.josephcostlow.mvpsample.model.ListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Joseph Costlow on 14-Feb-18.
 */

public interface GitHubClient {

    @GET ("/users/{user}/repos")
    Call<List<ListItem>> getUserRepos(@Path("user") String user);
}
