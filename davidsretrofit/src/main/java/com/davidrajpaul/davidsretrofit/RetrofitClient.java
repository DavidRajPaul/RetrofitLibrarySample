package com.davidrajpaul.davidsretrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DavidRaj Paul on 18-10-2018.
 */
public class RetrofitClient {
    private static RetrofitClient instance = null;

    public static RetrofitClient getInstance() {
        if (instance == null) {
        instance = new RetrofitClient();
        }
        return instance;
    }

    public Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl(RetrofitBase.instance().getBaseURL())
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//Log level
        int TIMEOUT = 2 * 60 * 1000;//120 seconds timeout
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }
}
