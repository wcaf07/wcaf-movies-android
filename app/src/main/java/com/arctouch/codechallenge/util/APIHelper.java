package com.arctouch.codechallenge.util;

import com.arctouch.codechallenge.api.TmdbApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by wanderleyfilho on 7/22/18.
 */

public class APIHelper {

    public static TmdbApi api;

    public static TmdbApi getApi() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(TmdbApi.URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(TmdbApi.class);
        }
        return api;
    }
}
