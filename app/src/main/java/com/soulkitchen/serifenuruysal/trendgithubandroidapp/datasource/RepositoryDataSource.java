package com.soulkitchen.serifenuruysal.trendgithubandroidapp.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.api.ApiClient;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.api.NetworkState;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.GitResponse;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soulkitchen.serifenuruysal.trendgithubandroidapp.api.ApiClient.API_DEFAULT_PAGE_KEY;

/**
 * Created by S.Nur Uysal on 11.12.2018.
 */
public class RepositoryDataSource extends PageKeyedDataSource<Long, ItemsItem> {

    private static final long FIRST_PAGE = 1;
    private static final String TAG = RepositoryDataSource.class.getSimpleName();

    /*
     *  Initialize the ApiService.
     * The networkState and initialLoading variables
     * are for updating the UI when data is being fetched
     * by displaying a progress bar
     */

    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public RepositoryDataSource() {
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }

    /*
     *  This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, final @NonNull LoadInitialCallback<Long, ItemsItem> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        ApiClient.getInstance().getRepositories(FIRST_PAGE).enqueue(new Callback<GitResponse>() {
            @Override
            public void onResponse(Call<GitResponse> call, Response<GitResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    GitResponse result = response.body();
                    List<ItemsItem> list = result != null ? result.getItems() : Collections.<ItemsItem>emptyList();
                    callback.onResult(list, null, FIRST_PAGE+1);
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);

                } else {
                    initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }

            }

            @Override
            public void onFailure(Call<GitResponse> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ItemsItem> callback) {

    }

    /*
     * This method it is responsible for the subsequent call to load the data page wise.
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */
    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, ItemsItem> callback) {
        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);

        ApiClient.getInstance().getRepositories(params.key).enqueue(new Callback<GitResponse>() {
            @Override
            public void onResponse(Call<GitResponse> call, Response<GitResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    GitResponse result = response.body();
                    callback.onResult(result.getItems(), (params.key) + 1);


                } else {
                    Log.e(TAG, "onResponse error " + response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }

            }

            @Override
            public void onFailure(Call<GitResponse> call, Throwable t) {
                String errorMessage = t.getMessage();
                Log.e(TAG, "onFailure: " + errorMessage);
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }


    public MutableLiveData getNetworkState() {
        return networkState;
    }



    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }


}
