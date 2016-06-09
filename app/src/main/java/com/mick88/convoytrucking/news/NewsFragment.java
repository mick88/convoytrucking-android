package com.mick88.convoytrucking.news;

import android.support.v7.widget.RecyclerView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.schema.feeds.NewsFeed;
import com.mick88.convoytrucking.api.schema.models.NewsArticle;
import com.mick88.convoytrucking.base.ApiFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michal on 09/06/2016.
 */
public class NewsFragment extends ApiFragment<NewsFeed> {
    public NewsFragment() {
        this.url = ApiConstants.API_NEWS;
    }

    @Override
    protected Class<NewsFeed> getModelClass() {
        return NewsFeed.class;
    }

    @Override
    public void onResponse(NewsFeed response) {
        super.onResponse(response);
        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        NewsAdapter adapter = (NewsAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new NewsAdapter(response.getResults());
            recyclerView.setAdapter(adapter);
        } else {
            final List<NewsArticle> items = Arrays.asList(response.getResults());
            adapter.addItems(items);
        }
    }
}
