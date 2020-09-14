package com.example.androidretrofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast=Toast.makeText(getApplicationContext(),"Btn clicked",Toast. LENGTH_SHORT);
                toast.show();
                final TextView textView = findViewById(R.id.textView);
                NetworkService.getInstance()
                        .getJSONApi()
                        .getPostWithID(1)
                        .enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                                Post post = response.body();

                                textView.append(post.getId() + "\n");
                                textView.append(post.getUserId() + "\n");
                                textView.append(post.getTitle() + "\n");
                                textView.append(post.getBody() + "\n");
                            }

                            @Override
                            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {

                                textView.append("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
            }
        });
    }


}
