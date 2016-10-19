package com.voy.net;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonInterceptor implements Interceptor {

    private String access;

    public CommonInterceptor(String access_token) {
        access = access_token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder().header("Authorization", "Bearer " + access)
                .header("Accept", "application/json").method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
