package com.davidrajpaul.davidsretrofit;

/**
 * Created by DavidRaj Paul on 18-10-2018.
 */
public class RetrofitBase {
    private static RetrofitBase instance;
    private String baseURL;
    private Class ApiInterface;

    public static RetrofitBase instance() {
        if (instance == null)
            instance = new RetrofitBase();

        return instance;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public Class getApiInterface() {
        return ApiInterface;
    }

    public void setApiInterface(Class apiInterface) {
        ApiInterface = apiInterface;
    }
}
