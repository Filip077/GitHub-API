package com.example.korisnik.githubapi.GET;

import com.example.korisnik.githubapi.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Korisnik on 23.02.2018.
 */

public interface UserInterface {
    @GET("/users/{name}/repos")
    Call<List<User>> getUserRepos(@Path("name")String name);


}
