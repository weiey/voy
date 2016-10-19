package com.voy.net;



public interface ResponseCallback<T>{
    void onResponse(T body);
    void onFailure();
}
