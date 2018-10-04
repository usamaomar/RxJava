package com.falconmena.rx.eyestore.rrx.Retrofit;

import com.falconmena.rx.eyestore.rrx.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyAPI {

    @GET("posts")
    Observable<List<Post>> getPosts();



}
