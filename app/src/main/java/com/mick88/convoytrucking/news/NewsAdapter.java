package com.mick88.convoytrucking.news;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.schema.models.NewsArticle;
import com.mick88.convoytrucking.utils.BaseAdapter;
import com.mick88.convoytrucking.utils.HtmlTagHandler;

/**
 * Created by Michal on 09/06/2016.
 */
public class NewsAdapter extends BaseAdapter<NewsArticle, NewsViewHolder> {
    public NewsAdapter(NewsArticle[] items) {
        super(items);
    }

    @Override
    protected NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater layoutInflater) {
        final View view = layoutInflater.inflate(R.layout.card_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsArticle newsArticle = getItem(position);

        holder.tvTitle.setText(newsArticle.getTitle());
        final String content = newsArticle.getContent();
        final Spanned html = Html.fromHtml(content, null, new HtmlTagHandler());
        holder.tvContent.setText(html);
    }
}
