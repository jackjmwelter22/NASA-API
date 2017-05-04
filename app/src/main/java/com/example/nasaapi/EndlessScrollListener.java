package com.example.nasaapi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager linearLayoutManager;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        totalItemCount = linearLayoutManager.getItemCount();

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            onLoadMore();
            loading = true;
        }
    }
    public abstract void onLoadMore();

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
