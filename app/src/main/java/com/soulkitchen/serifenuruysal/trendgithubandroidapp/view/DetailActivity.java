package com.soulkitchen.serifenuruysal.trendgithubandroidapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.R;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.databinding.DetailActivityBinding;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel.DetailViewModel;


/**
 * Created by S.Nur Uysal on 6.12.2018.
 */
public class DetailActivity extends AppCompatActivity {
    DetailActivityBinding binding;
    ItemsItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent().getParcelableExtra("ITEM") != null) {
            item = getIntent().getParcelableExtra("ITEM");
            getSupportActionBar().setTitle(item.getFullName());
        }
        binding.setViewModel(new DetailViewModel(getBaseContext()));
        binding.getViewModel().setmItems(item);
        binding.setLifecycleOwner(this);

    }
}
