package com.falconmena.rx.eyestore.rrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;


//import com.falconmena.rx.eyestore.rrx.Model.Post;
//import com.falconmena.rx.eyestore.rrx.Retrofit.IMyAPI;
//import com.falconmena.rx.eyestore.rrx.Retrofit.RetrofitClient;

//import java.util.List;


import com.falconmena.rx.eyestore.rrx.Model.Post;
import com.falconmena.rx.eyestore.rrx.Retrofit.IMyAPI;
import com.falconmena.rx.eyestore.rrx.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.functions.Consumer;
//import io.reactivex.schedulers.Schedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
//import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity {


    private TextView clickTextView;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickTextView = (TextView)findViewById(R.id.clickTextView);
        Retrofit retrofit =  new RetrofitClient().getInstance();
        iMyAPI = retrofit.create(IMyAPI.class);
        clickTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    compositeDisposable.add(iMyAPI.getPosts().subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Post>>() {
                        @Override
                        public void accept(List<Post> posts) throws Exception {
                            Log.e("Tag", "value");
                        }
                    }));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


//        clickTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Observable.from(new Integer[]{1,2,3}).subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                      Log.d("onNext",String.valueOf(integer));
//                    }
//                });
//
//
//
//            }
//        });


    }


    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
