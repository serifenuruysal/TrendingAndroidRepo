package com.soulkitchen.serifenuruysal.trendgithubandroidapp.api;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by S.Nur Uysal on 5.12.2018.
 */
public class ApiClient {


    private static final String TAG = "ApiClient";
    private static final String BASE_URL = "https://api.github.com/";
    private static final OkHttpClient client;
    private static ApiService mService;
    public static final int API_DEFAULT_PAGE_KEY = 1;
    static {

        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new AuthInterceptor())
                .build();

    }

    public static ApiService getInstance() {
        synchronized (new Object()) {
            if (mService == null) {
                mService = getRetrofitInstance().create(ApiService.class);
            }
            return mService;
        }
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public ApiService getService() {
        return mService;
    }

    public static class AuthInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            HttpUrl url = request.url().newBuilder()
//                    .addQueryParameter("api_key", BuildConfig.API_KEY)
//                    .addQueryParameter("language", "en-US")
//                    .addQueryParameter("region", "en-US")
                    .build();

            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }


    }

}
