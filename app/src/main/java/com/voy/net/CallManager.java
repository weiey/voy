package com.voy.net;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class CallManager {
    private static final LinkedHashMap<String, Call> callMap = new LinkedHashMap<>();

    private CallManager() {
    }

    /**
     * 只需要一个这样的实例
     */
    private static CallManager instance;
    public static CallManager getInstance() {
        if (instance == null) {
            synchronized (CallManager.class) {
                if (instance == null) {
                    instance = new CallManager();
                }
            }
        }
        return instance;
    }


    /**
     *
     * @param requestCall
     * @param callback
     * @param <T>
     */
    public <T extends Result> void callResult(final @NonNull RequestCall<T> requestCall,final ResponseCallback<T> callback){

        Call<T> call = requestCall.getCall();
        final String key;
        if (requestCall.getTag()!= null) {
            key =  requestCall.getTag();
            callMap.put(key, call);
        } else {
            key = null;
        }
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                int code = response.code();
                callMap.remove(key);
                if (200 == code) {
                    T body = response.body();
                    if(body.getCode() ==1){
                        if(callback!=null){
                            callback.onResponse(body);
                        }
                    }
                }else{
                    if(callback!=null){
                        callback.onFailure();
                    }
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callMap.remove(key);
                if(callback!=null){
                    callback.onFailure();
                }
            }
        });
    }

    public <T> void enqueue(final @NonNull RequestCall<T> requestCall,final ResponseCallback<T> callback){
        Call<T> call = requestCall.getCall();
        final String key;
        if (requestCall.getTag()!= null) {
            key =  requestCall.getTag();
            callMap.put(key, call);
        } else {
            key = null;
        }
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                int code = response.code();
                callMap.remove(key);
                if (200 == code) {
                    T body = response.body();
                    if(callback!=null){
                        callback.onResponse(body);
                    }
                }else{
                    if(callback!=null){
                        callback.onFailure();
                    }
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callMap.remove(key);
                if(callback!=null){
                    callback.onFailure();
                }
            }
        });


    }

    public static void cancel(String key) {
        Call call = null;
        try {
            call = callMap.remove(key);
            if(call != null){
                call.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param baseKey
     */
    public static void cancelByBasicKey(String baseKey) {
        Set<Map.Entry<String, Call>> entries = callMap.entrySet();
        if (entries == null || entries.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, Call>> it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Call> next = it.next();
            String key = next.getKey();
            if (key.startsWith(baseKey)) {
                Call call = next.getValue();
                call.cancel();
                it.remove();
            }
        }
    }


}
