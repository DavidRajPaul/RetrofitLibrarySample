package com.davidrajpaul.davidsretrofit;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallBack<T> implements Callback<T> {

    private static String TAG = RetrofitCallBack.class.getSimpleName();

    private RetrofitCallBackInterface<T> mCallback;

    public RetrofitCallBack(RetrofitCallBackInterface<T> callback) {
        this.mCallback = callback;
    }

    RetrofitCallBack(RetrofitCallBackInterface<T> callback, boolean showProgressBar) {
        this.mCallback = callback;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.code() == 200) {
            if (mCallback != null)
                mCallback.onSuccess(response);
        } else if (response.code() == 400) {
            if (mCallback != null) {
                try {
                    mCallback.onError(response.code(), response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (response.code() == 401) {
            if (mCallback != null) {
                try {
                    mCallback.onError(response.code(), response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (response.code() == 503 || response.code() == 500) {
            if (mCallback != null) {
                try {
                    mCallback.onError(response.code(), response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                String res = response.body().toString();
                if (mCallback != null)
                    mCallback.onError(response.code(), res);
            } catch (NullPointerException e) {
                Log.i("TAG", "onResponse: " + call.request().url());
                e.printStackTrace();
                if (mCallback != null)
                    mCallback.onError(response.code(), e.getMessage());
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable error) {
        String message = null;
        if (error instanceof NetworkErrorException) {
            message = "Please check your internet connection";
        } else if (error instanceof ParseException) {
            message = "Parsing error! Please try again after some time!!";
        } else if (error instanceof TimeoutException) {
            message = "Connection TimeOut! Please check your internet connection.";
        } else if (error instanceof UnknownHostException) {
            message = "Unable to find Host. Please check your internet connection.";
        } else if (error instanceof Exception) {
            message = error.getMessage();
        }
        if (mCallback != null)
            mCallback.onError(-1, message);
    }
}