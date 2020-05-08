package com.example.retrofit_get_thing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rentbaaz.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<List<Post>>call = retrofitInterface.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code: "+response.code());
                    return;
                }
                List<Post>posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += "Name: "+post.getName()+ "\n";
                    content += "Realname: "+post.getRealname()+ "\n";
                    content += "Team: "+post.getTeam()+ "\n";
                    content += "First Appearance: "+post.getFirstappearance()+ "\n";
                    content += "Created By: "+post.getCreatedby()+ "\n";
                    content += "Publisher: "+post.getPublisher()+ "\n";
                    content += "Image URL: "+post.getImageurl()+ "\n";
                    content += "Bio: "+post.getText()+ "\n";
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
