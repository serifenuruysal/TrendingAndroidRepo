package com.soulkitchen.serifenuruysal.trendgithubandroidapp.view;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.R;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.adapter.TrendRecyclerAdapter;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.api.NetworkState;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.databinding.MainActivityBinding;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel.ListViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TrendRecyclerAdapter.OnItemClickListener {
    public static final String TAG=MainActivity.class.getSimpleName();
    MainActivityBinding binding;
    TrendRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new ListViewModel(getBaseContext(), this));
        binding.setLifecycleOwner(this);
        adapter=new TrendRecyclerAdapter(this,  binding.getViewModel().getItems(), this);
        binding.rvMostPopular.setAdapter(adapter);
        binding.rvMostPopular.setLayoutManager(new LinearLayoutManager(this));
        binding.getViewModel().getItems().observe(this, new Observer<PagedList<ItemsItem>>() {
            @Override
            public void onChanged(PagedList<ItemsItem> itemsItems) {
                Log.d(TAG, "onChanged: "+itemsItems.size());
                adapter.submitList(itemsItems);
            }
        });

        binding.getViewModel().getNetworkStateLiveData().observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
//                binding.progress.setVisibility(networkState.getStatus()==NetworkState.Status.RUNNING?View.VISIBLE:View.GONE);
            }
        });
    }

    @Override
    public void onItemClicked(ItemsItem wrapper) {

        Intent intent =new Intent(this,DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ITEM",wrapper);
        startActivity(intent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
