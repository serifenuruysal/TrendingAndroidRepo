package com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;


/**
 * Created by S.Nur Uysal on 6.12.2018.
 */
public class DetailViewModel extends BaseViewModel {
    MutableLiveData<ItemsItem> mItems;
    Context mContext;

    public DetailViewModel(Context contex) {

        mContext = contex;
    }

    public MutableLiveData<ItemsItem> getmItems() {
        return mItems;
    }

    public void setmItems(ItemsItem item) {

        mItems = new MutableLiveData<>();
        mItems.setValue(item);
    }

    public String getName() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getName();

    }

    public String getTitle() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getName();

    }

    public String getAvatarUrl() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getOwner() != null ? mItems.getValue().getOwner().getAvatarUrl() : "";

    }

    public String getForkCount() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getForks() + "";

    }

    public String getDescription() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getDescription();

    }

    public String getLanguage() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getLanguage();

    }

    public String getScore() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getScore() + "";

    }

    public String getOwner() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getOwner() != null ? mItems.getValue().getOwner().getLogin() : "";

    }

    public String getUrl() {
        if (mItems == null || mItems.getValue() == null) return null;
        return mItems.getValue().getUrl();

    }

    public String getInfoo() {
        if (mItems == null || mItems.getValue() == null) return null;
        return "Open Issue Count: " + mItems.getValue().getOpenIssues() + "     Watcher Count: " + mItems.getValue().getWatchersCount();

    }


    public String getInfo2() {
        if (mItems == null || mItems.getValue() == null) return null;
        return "Fork Count: " + mItems.getValue().getForks() + "     Licance: " + (mItems.getValue().getLicense() != null ? mItems.getValue().getLicense().getName() : "-");

    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
