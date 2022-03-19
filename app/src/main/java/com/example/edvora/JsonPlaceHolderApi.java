package com.example.edvora;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonPlaceHolderApi {

    @GET("rides")
    Call<List<Ride>> getRides();

    @GET("user")
    Call<User> getUser();

}
