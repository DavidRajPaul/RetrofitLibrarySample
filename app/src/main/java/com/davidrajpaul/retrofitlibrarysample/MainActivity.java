package com.davidrajpaul.retrofitlibrarysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.davidrajpaul.davidsretrofit.RetrofitCallBackInterface;
import com.davidrajpaul.davidsretrofit.RetrofitClient;
import com.davidrajpaul.retrofitlibrarysample.model.MyDataClass;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkCallHandler.getMyData(new RetrofitCallBackInterface<MyDataClass>() {
            @Override
            public void onSuccess(Response<MyDataClass> response) {
                Toast.makeText(MainActivity.this, response.body().getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(int code, String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
