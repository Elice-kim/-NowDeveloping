package com.example.elice.speshopapp.apimodule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elice.kim on 2017. 5. 27..
 */

public class DefaultRestClient<T> {

    private T service;
    public String baseUrl = "http://52.78.137.204:3000/";

    public T getService(Class<? extends T> type) {

        if (service == null) {


            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient okHttpClient
                    = new OkHttpClient
                    .Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new Interceptor() {

                        @Override
                        public Response intercept(Chain chain) throws IOException {

                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header("Content-Type", "application/x-www-form-urlencoded")
                                    .method(original.method(), original.body())
                                    .build();

                            return chain.proceed(request);
                        }
                    }).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = client.create(type);
        }
        return service;
    }
}