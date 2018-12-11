package com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;


/**
 * Created by S.Nur Uysal on 5.12.2018.
 */
public class RowViewModel extends BaseViewModel{
    ItemsItem mItems;
    public RowViewModel(ItemsItem item) {
       mItems=item;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }

    public String getName(){
        return mItems.getName();

    }
    public String getTitle(){
        return mItems.getName();

    }

    public String getAvatarUrl(){
        return mItems.getOwner().getAvatarUrl();

    }

    public String getForkCount(){
        return mItems.getForks()+"";

    }

    public String getDescription(){
        return mItems.getDescription();

    }

    public String getLanguage(){
        return mItems.getLanguage();

    }

    public String getScore(){
        return mItems.getScore()+"";

    }
    public String getOwner(){
        return mItems.getOwner().getLogin();

    }
}
