package com.learn.binod.news.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by binod on 10/3/2017.
 */

public class NewsAPI {
    private static final String APIPATH="https://newsapi.org/v1/";
    private static Retrofit retrofit=null;

    public  static Retrofit getApi(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
