package com.paichai.healthhelper.common.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.paichai.healthhelper.user.api.UserApi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private static Retrofit retrofit;

    public static UserApi getUserApi(Context context) {
        return getInstance(context).create(UserApi.class);
    }

    public static Retrofit getInstance(Context context) {
        if (retrofit == null) {
            Log.d("ApiClient", ">> Building new Retrofit instance with BASE_URL=" + BASE_URL);

            // SharedPreferences에서 토큰 가져오기
            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            String token = prefs.getString("AUTH_TOKEN", null);

            // 로그용 인터셉터
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // 토큰 자동 추가 인터셉터
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request.Builder builder = original.newBuilder()
                                .method(original.method(), original.body());

                        if (token != null) {
                            builder.header("Authorization", "Bearer " + token);
                        }

                        return chain.proceed(builder.build());
                    })
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
