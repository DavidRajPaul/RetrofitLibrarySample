package com.davidrajpaul.retrofitlibrarysample;

import android.app.Application;

import com.davidrajpaul.davidsretrofit.RetrofitBase;

/**
 * Created by DavidRaj Paul on 18-10-2018.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitBase.instance().setBaseURL("https://gist.githubusercontent.com/DavidRajPaul/");
        RetrofitBase.instance().setApiInterface(APIInterface.class);
    }
}
