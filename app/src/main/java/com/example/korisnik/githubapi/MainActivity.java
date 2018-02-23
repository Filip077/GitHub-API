package com.example.korisnik.githubapi;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korisnik.githubapi.GET.UserInterface;
import com.example.korisnik.githubapi.Model.User;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button search_button;
    private TextView user_name;
    private EditText search_edit_text;
    private ImageView user_photo;
    private String BASE_URL = "https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_button = findViewById(R.id.search_button);
        user_name = findViewById(R.id.user_name);
        user_photo = findViewById(R.id.user_photo);
        search_edit_text =findViewById(R.id.search_edit_text);


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Call<User> call = user.getUser(search_edit_text.getText().toString());
                    call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                            User u = response.body();
                            if(u != null) {
                                user_name.setText(u.getLogin());
                                Picasso.with(MainActivity.this).load(u.getAvatar_url()).into(user_photo);
                            }else {
                                Toast.makeText(MainActivity.this,"User not found",Toast.LENGTH_LONG).show();
                            }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"User not found",Toast.LENGTH_LONG).show();
                }
            });
            }
        });
    }
}
