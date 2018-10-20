package com.davidrajpaul.retrofitlibrarysample;


import com.davidrajpaul.davidsretrofit.RetrofitCallBack;
import com.davidrajpaul.davidsretrofit.RetrofitCallBackInterface;
import com.davidrajpaul.davidsretrofit.RetrofitClient;
import com.davidrajpaul.retrofitlibrarysample.model.MyDataClass;

/**
 * Created by DavidRaj Paul on 06-07-2018.
 */
public class NetworkCallHandler {

    private static NetworkCallHandler instance;

    public static NetworkCallHandler getInstance() {
        if (instance == null) {
            instance = new NetworkCallHandler();
        }
        return instance;
    }

    public static void getMyData(RetrofitCallBackInterface<MyDataClass> callBack){

        RetrofitClient.getInstance().getClient().create(APIInterface.class).getMydata()
                .enqueue(new RetrofitCallBack<>(callBack));
    }


}
