package com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;


/**
 * Created by S.Nur Uysal on 5.12.2018.
 */
public abstract class BaseViewModel extends ViewModel implements Observable {

    protected PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    /**
     * download image from url and set imageView. This method is in use by xml
     *
     * @param view imageView
     * @param url  image url
     */
    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView view, String url) {
        RequestManager requestManager = Glide.with(view.getContext());
        RequestBuilder requestBuilder = requestManager.load(url);
        requestBuilder.into(view);
    }

    @BindingAdapter({"backgroundResource"})
    public static void setBackgroundResource(View view, int resId) {
        view.setBackgroundResource(resId);
    }


}

