package com.learn.binod.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.learn.binod.news.model.Article;
import com.learn.binod.news.utils.DateUtils;

import java.util.List;

/**
 * Created by binod on 9/17/2017.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter <HomeNewsAdapter.HomeNewsViewHoder>{
    private List<Article> newsArticles;

    public HomeNewsAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @Override

    public HomeNewsViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news,parent,false);
        HomeNewsViewHoder homeNewsViewHoder=new HomeNewsViewHoder(view);
        return homeNewsViewHoder;
    }

    @Override
    public void onBindViewHolder(HomeNewsViewHoder holder, final int position) {
        Article newsArticle=newsArticles.get(position);
        Glide.with(holder.cardImageView.getContext()).load(newsArticles.get(position).getUrlToImage())
                .centerCrop()
                .into(holder.cardImageView);
        holder.cardTitleTextView.setText(newsArticle.getTitle());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle.getPublishedAt()));
        holder.cardContentTextView.setText((newsArticle.getDescription()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.launch(v.getContext(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public  static class HomeNewsViewHoder extends RecyclerView.ViewHolder{
        ImageView cardImageView;
        TextView cardTitleTextView,cardTimeTextView,cardContentTextView;

        public HomeNewsViewHoder(View itemView) {
            super(itemView);
            cardImageView=(ImageView) itemView.findViewById(R.id.card_news_image);
            cardTitleTextView=(TextView)itemView.findViewById(R.id.card_news_title);
            cardTimeTextView=(TextView)itemView.findViewById(R.id.card_news_time);
            cardContentTextView=(TextView)itemView.findViewById(R.id.card_news_content);
        }
    }
}
