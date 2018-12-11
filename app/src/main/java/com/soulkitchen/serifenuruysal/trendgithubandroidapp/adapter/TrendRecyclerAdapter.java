package com.soulkitchen.serifenuruysal.trendgithubandroidapp.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soulkitchen.serifenuruysal.trendgithubandroidapp.R;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.databinding.RowBinding;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.models.ItemsItem;
import com.soulkitchen.serifenuruysal.trendgithubandroidapp.viewModel.RowViewModel;


/**
 * Created by S.Nur Uysal on 5.12.2018.
 */
public class TrendRecyclerAdapter extends PagedListAdapter<ItemsItem, TrendRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private LiveData<PagedList<ItemsItem>> mItems;
    private OnItemClickListener mListener;

    public TrendRecyclerAdapter(Context context, LiveData<PagedList<ItemsItem>> items, OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<ItemsItem>() {
            @Override
            public boolean areItemsTheSame(ItemsItem oldItem, ItemsItem newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(ItemsItem oldItem, ItemsItem newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
        mContext = context;
        mItems = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.row_recycle, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendRecyclerAdapter.ViewHolder holder, int i) {
        ItemsItem item = getItem(i);
        holder.setViewModel(new RowViewModel(item));
    }


    @Override
    public int getItemCount() {
        return mItems.getValue() != null ? mItems.getValue().size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private RowBinding mBinding;

        public ViewHolder(RowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(mItems.getValue().get(getAdapterPosition()));
                }
            });
        }

        public void setViewModel(RowViewModel viewModel) {
            mBinding.setViewModel(viewModel);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(ItemsItem wrapper);
    }

}
