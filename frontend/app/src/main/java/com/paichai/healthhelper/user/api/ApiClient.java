package com.paichai.healthhelper.user.api;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private static Retrofit retrofit;

    public static UserApi getUserApi() {
        if (retrofit == null) {
            Log.d("ApiClient", ">> Building new Retrofit instance with BASE_URL=" + BASE_URL);
            // 1) 로깅 인터셉터 세팅
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // 2) OkHttpClient 에 로깅 인터셉터 추가
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            // 3) Retrofit 빌더에 client()로 등록
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)  // 여기에 추가된 client 사용
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(UserApi.class);
    }

}
