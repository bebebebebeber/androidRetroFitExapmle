package com.example.androidretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    public void saveJWTToken(String token) {
        SharedPreferences prefs;
        SharedPreferences.Editor edit;
        prefs=this.getSharedPreferences("jwtStore", Context.MODE_PRIVATE);
        edit=prefs.edit();
        try {
            edit.putString("token",token);
            Log.i("Login",token);
            edit.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final EditText password = findViewById(R.id.password);
                final EditText email = findViewById(R.id.email);
                Login m =new Login();
                m.setEmail(email.getText().toString());
                m.setPassword(password.getText().toString());
                NetworkService.getInstance()
                        .getJSONApi()
                        .login(m)
                        .enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                                Post post = response.body();
                                //textView.append(post.getId() + "\n");
                                //textView.append(post.getUserId() + "\n");
                                //textView.append(post.getTitle() + "\n");
                                //textView.append(post.getBody() + "\n");
                                Toast toast=Toast.makeText(getApplicationContext(),"All done! your ref token :"+post.getRefreshToken(),Toast.LENGTH_LONG);
                                toast.show();
                                saveJWTToken(post.getToken());

                            }

                            @Override
                            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {

                                //textView.append("Error occurred while getting request!");
                                t.printStackTrace();
                            }
                        });
            }
        });
    }


}
