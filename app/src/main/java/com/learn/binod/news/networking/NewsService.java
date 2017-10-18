package com.learn.binod.news.networking;

import com.learn.binod.news.model.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.learn.binod.news.networking.NewsService.APIKEY;

/**
 * Created by binod on 10/3/2017.
 */

public interface NewsService {

     static final String APIKEY="ee2cf24986c0487e92185556cd7a38ea";

    @GET("articles?apiKey="+APIKEY)
    Call<GetArticlesResponse>getArticles(@Query("source") String source,@Query("sortBy")String sortBy);
}
