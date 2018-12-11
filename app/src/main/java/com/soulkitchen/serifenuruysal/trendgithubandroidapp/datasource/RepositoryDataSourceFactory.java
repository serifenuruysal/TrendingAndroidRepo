package com.soulkitchen.serifenuruysal.trendgithubandroidapp.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.util.Log;

import java.util.concurrent.Executor;

/**
 * Created by S.Nur Uysal on 11.12.2018.
 */
public class RepositoryDataSourceFactory extends DataSource.Factory {
    private static final String TAG = RepositoryDataSourceFactory.class.getSimpleName();
    RepositoryDataSource dataSource;
    MutableLiveData<RepositoryDataSource> mutableLiveData;

    public RepositoryDataSourceFactory() {

        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        Log.d(TAG, "create: ");
        dataSource = new RepositoryDataSource();
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<RepositoryDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}