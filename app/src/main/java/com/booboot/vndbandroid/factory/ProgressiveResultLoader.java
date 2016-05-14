package com.booboot.vndbandroid.factory;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.booboot.vndbandroid.R;
import com.booboot.vndbandroid.activity.VNDetailsActivity;
import com.booboot.vndbandroid.activity.VNTypeFragment;
import com.booboot.vndbandroid.adapter.materiallistview.MaterialListView;
import com.booboot.vndbandroid.api.Cache;
import com.booboot.vndbandroid.api.VNDBServer;
import com.booboot.vndbandroid.api.bean.Item;
import com.booboot.vndbandroid.api.bean.Options;
import com.booboot.vndbandroid.util.Callback;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;

/**
 * Created by od on 13/05/2016.
 */
public class ProgressiveResultLoader {
    private Activity activity;
    private View rootView;
    private MaterialListView materialListView;
    private ProgressBar progressBar;
    private int currentPage = 1;
    private String filters;
    private Options options;
    private boolean moreResults;

    private boolean showFullDate;
    private boolean showRank;

    public void init() {
        progressBar = (ProgressBar) (rootView == null ? activity.findViewById(R.id.progressBar) : rootView.findViewById(R.id.progressBar));
        materialListView = (MaterialListView) (rootView == null ? activity.findViewById(R.id.materialListView) : rootView.findViewById(R.id.materialListView));

        if (rootView == null) {
            /* [Fix] Set the background color to match the default one (not the case by default) */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                materialListView.getRootView().setBackgroundColor(activity.getResources().getColor(R.color.windowBackground, activity.getTheme()));
            } else {
                materialListView.getRootView().setBackgroundColor(activity.getResources().getColor(R.color.windowBackground));
            }
        }

        materialListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(Card card, int position) {
                Intent intent = new Intent(activity, VNDetailsActivity.class);
                intent.putExtra(VNTypeFragment.VN_ARG, (Item) card.getTag());
                activity.startActivity(intent);
            }

            @Override
            public void onItemLongClick(Card card, int position) {
            }
        });

        materialListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && !progressBar.isShown() && moreResults) {
                    int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                    int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                    int pastVisiblesItems = 0;
                    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        /* Computing past visible items in portrait (1 column) */
                        pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    } else {
                        /* Computing past visible items in landscape (> 1 column) */
                        int[] firstVisibleItemPositions = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPositions(null);
                        for (int i = 0; i < firstVisibleItemPositions.length - 1; i++)
                            pastVisiblesItems += firstVisibleItemPositions[i];
                    }

                    if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                        currentPage++;
                        loadResults(false);
                    }
                }
            }
        });

        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) (rootView == null ? activity.findViewById(R.id.refreshLayout) : rootView.findViewById(R.id.refreshLayout));
        refreshLayout.setEnabled(false);
    }

    public void loadResults(final boolean clearData) {
        options.setPage(currentPage);
        progressBar.setVisibility(View.VISIBLE);
        VNDBServer.get("vn", Cache.VN_FLAGS, filters, options, activity, new Callback() {
            @Override
            protected void config() {
                moreResults = results.isMore();
                if (clearData)
                    materialListView.getAdapter().clearAll();

                for (final Item vn : results.getItems()) {
                    VNCardFactory.buildCard(activity, vn, materialListView, showFullDate, showRank);
                }

                if (currentPage == 1)
                    materialListView.scrollToPosition(0);
                progressBar.setVisibility(View.GONE);
            }
        }, new Callback() {
            @Override
            public void config() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public void setMaterialListView(MaterialListView materialListView) {
        this.materialListView = materialListView;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void setMoreResults(boolean moreResults) {
        this.moreResults = moreResults;
    }

    public void setShowFullDate(boolean showFullDate) {
        this.showFullDate = showFullDate;
    }

    public void setShowRank(boolean showRank) {
        this.showRank = showRank;
    }
}
