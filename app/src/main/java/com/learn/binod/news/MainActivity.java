package com.learn.binod.news;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.learn.binod.news.model.GetArticlesResponse;
import com.learn.binod.news.networking.NewsAPI;
import com.learn.binod.news.networking.NewsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.activity_main);
        newsRecyclerView=(RecyclerView)findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        NewsService apiService= NewsAPI.getApi().create(NewsService.class);
        Call<GetArticlesResponse> call=apiService.getArticles("reuters","top");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                ShowNewsApiSnack();
                GetArticlesResponse getArticlesResponse=response.body();
                NewsStore.setNewsArticles(getArticlesResponse.getArticles());
              //  Toast.makeText(MainActivity.this,"Response Received",Toast.LENGTH_SHORT).show();
                HomeNewsAdapter homeNewsAdapter=new HomeNewsAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Response Not Received",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowNewsApiSnack(){
        Snackbar.make(coordinatorLayout,"Powered by Newsapi.org",Snackbar.LENGTH_LONG).setAction("Visit", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNewsApiWebsite();
            }
        }).show();
    }

    private void LoadNewsApiWebsite(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org")));
    }
}
