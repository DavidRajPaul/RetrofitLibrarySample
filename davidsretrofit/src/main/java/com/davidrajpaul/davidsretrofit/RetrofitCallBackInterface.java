package com.davidrajpaul.davidsretrofit;

import retrofit2.Response;

/**
 * Created by DavidRaj Paul
 */
public interface RetrofitCallBackInterface<T> {

    void onSuccess(Response<T> body);

    void onError(int code, String error);
}
