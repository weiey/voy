package com.voy.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class ServiceManager {
    private String TAG = ServiceManager.class.getSimpleName();
//    private static final String BASE_URL = "http://112.74.76.53/mrmy/";
    private static final String BASE_URL = "http://192.168.1.100:8080/mrmy/";
    private Cache cache;
//    private HttpLoggingInterceptor mHttpLogInterceptor;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    private Retrofit mRetrofit;

    private ServiceManager() {
    }
    public void init(Context context){
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        File cacheFile = new File(context.getCacheDir(), "HttpCache");
        Log.d("zgx", "cacheFile=====" + cacheFile.getAbsolutePath());
        Cache cache  = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        initHttpClient(context);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(mOkHttpClient)
                .build();
    }

    /**
     * 只需要一个这样的实例
     */
    private static volatile ServiceManager instance;
    public static ServiceManager getInstance() {
        if (instance == null) {
            synchronized (ServiceManager.class) {
                if (instance == null) {
                    instance = new ServiceManager();
                }
            }
        }
        return instance;
    }

    private void  initHttpClient(Context context) {
//        mHttpLogInterceptor = new HttpLoggingInterceptor();
//        mHttpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Map<String, String> tempParams = getBaseParams(context);
        BasicParamsInterceptor mBaseParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addParamsMap(tempParams)
                .build();
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggerInterceptor(TAG,true))
//                    .addInterceptor(mBaseParamsInterceptor)
//                    .addInterceptor(mUrlInterceptor)
                .cache(cache)
                .build();
    }
    public <T> T getService(Class<T> service) {
        if (null != mRetrofit) {
            return mRetrofit.create(service);
        } else {
            throw new IllegalArgumentException("service is not initialized.");
        }
    }
    public ApiService getService() {
        if (null != mRetrofit) {
            return mRetrofit.create(ApiService.class);
        } else {
            throw new IllegalArgumentException("service is not initialized.");
        }
    }
    public void release() {
        mRetrofit = null;
        mOkHttpClient = null;
        instance = null;
    }

    private Map<String, String> getBaseParams(Context context) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "324353");
        params.put("sessionToken", "434334");
        params.put("q_version", "1.1");
        params.put("device_id", "android7.0");
        params.put("device_os", "android");
        params.put("device_type", "android");
        params.put("device_osversion", "android");
        params.put("req_timestamp", System.currentTimeMillis() + "");
        params.put("app_name", "forums");
        String sign = makeSign(params);
        params.put("sign", sign);
        return params;
    }

    private String makeSign(Map<String, String> params) {
        final String signSalt = "fe#%d8ec93a1159a2a3";
        TreeMap<String, Object> sorted = new TreeMap<String, Object>();
        for (Map.Entry<String, String> kv : params.entrySet()) {
            sorted.put(kv.getKey(), kv.getValue());
        }
        StringBuilder sb = new StringBuilder(signSalt);
        for (String key : sorted.keySet()) {
            if (!"sign".equals(key) && !key.startsWith("file_")) {
                sb.append(key).append(sorted.get(key));
            }
        }
        sb.append(signSalt);
        return MD5.md5(sb.toString()).toUpperCase();
    }

    public Cache getCache(){
        return cache;
    }
    public void clearCache() throws IOException {
        cache.delete();
    }
}
