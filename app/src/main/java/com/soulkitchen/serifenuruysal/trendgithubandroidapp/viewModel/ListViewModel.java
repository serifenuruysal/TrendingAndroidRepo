package com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.databinding.Observable;
import android.util.Log;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.adapter.TrendRecyclerAdapter;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.api.NetworkState;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.datasource.RepositoryDataSource;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.datasource.RepositoryDataSourceFactory;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by S.Nur Uysal on 5.12.2018.
 */
public class ListViewModel extends BaseViewModel {
    public static final String TAG=ListViewModel.class.getSimpleName();
    Context mContext;
    LiveData<PagedList<ItemsItem>> items ;
    private TrendRecyclerAdapter.OnItemClickListener mListener;

    private LiveData<NetworkState> networkStateLiveData;
    private LiveData<RepositoryDataSource> dataSource;

    public ListViewModel(Context baseContext, TrendRecyclerAdapter.OnItemClickListener listener) {
        mContext = baseContext;
        mListener = listener;
        RepositoryDataSourceFactory factory = new RepositoryDataSourceFactory();
        dataSource =  factory.getMutableLiveData();

        networkStateLiveData = Transformations.switchMap(factory.getMutableLiveData(), new Function<RepositoryDataSource, LiveData<NetworkState>>() {
            @Override
            public LiveData<NetworkState> apply(RepositoryDataSource source) {
                Log.d(TAG, "apply: network change");
                return source.getNetworkState();
            }
        });

        PagedList.Config pageConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(12)
                .setPageSize(12).build();

        items = (new LivePagedListBuilder<Long,ItemsItem>(factory,pageConfig))
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build();

    }

    public LiveData<PagedList<ItemsItem>> getItems() {
        return items;
    }


    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }



    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {

    }
}
