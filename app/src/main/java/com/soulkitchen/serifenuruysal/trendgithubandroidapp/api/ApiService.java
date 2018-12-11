package com.soulkitchen.serifenuruysal.trendgithubandroidapp.api;


import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.GitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by S.Nur Uysal on 5.12.2018.
 */
public interface ApiService {

    //?q=android%20language:java&sort=stars&order=desc
    ///// This query fetches most trending android repository /////
    ///// For demo purpose we would call it directly
    //?page=2&per_page=100'
    @GET("search/repositories?q=android%20language:kotlin&sort=stars&order=desc&per_page=10")
    Call<GitResponse> getRepositories(@Query("page") long page);

}
