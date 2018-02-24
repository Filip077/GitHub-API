package com.example.korisnik.githubapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.korisnik.githubapi.Adapter.ReposAdapter;
import com.example.korisnik.githubapi.GET.UserInterface;
import com.example.korisnik.githubapi.Model.User;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayRepos extends AppCompatActivity {
    private EditText search_edit_text;
    private List<User> userList;
    private RecyclerView mRecyclerView;
    private ReposAdapter adapter;
    private String BASE_URL = "https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_repos);
        Bundle i = getIntent().getExtras();
        String name = i.getString("name");

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(new ReposAdapter(userList,getApplicationContext()));

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor =new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG){
            okHttpClient.addInterceptor(interceptor);
        }


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build());

        Retrofit retrofit = builder.build();

        UserInterface user = retrofit.create(UserInterface.class);
        Call<List<User>> call = user.getUserRepos(name);
    call.enqueue(new Callback<List<User>>() {
        @Override
        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
            mRecyclerView.setAdapter(new ReposAdapter(response.body(),getApplicationContext()));
        }

        @Override
        public void onFailure(Call<List<User>> call, Throwable t) {
            Toast.makeText(DisplayRepos.this,"Failed",Toast.LENGTH_LONG).show();
        }
    });




    }


}
