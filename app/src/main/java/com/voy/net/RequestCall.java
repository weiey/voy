package com.voy.net;


import retrofit2.Call;

public class RequestCall<T>
{
    private Call<T> call;
    private String tag;

    public RequestCall(Call<T> call, String tag) {
        this.call = call;
        this.tag = tag;
    }

    public void cancel()
    {
        if (call != null)
        {
            call.cancel();
        }
    }

    public Call<T> getCall() {
        return call;
    }

    public void setCall(Call<T> call) {
        this.call = call;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
