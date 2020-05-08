package com.example.retrofit_get_thing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("adgMarvel")
    Call<List<Post>> getPosts();
}
