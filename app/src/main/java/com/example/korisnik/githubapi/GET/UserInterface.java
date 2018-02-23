package com.example.korisnik.githubapi.GET;

import com.example.korisnik.githubapi.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;

/**
 * Created by Korisnik on 23.02.2018.
 */

public interface UserInterface {
    @GET("/users/{name}")
    Call<User> getUser(@Path("name")String name);
}
