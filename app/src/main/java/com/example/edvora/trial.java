package com.example.edvora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class trial extends AppCompatActivity {

    TextView uName;

    CircleImageView profile_image;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ride_adapter mAdapter;
    List<Ride> rideList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial3);

        profile_image = findViewById(R.id.profile_image);
        uName = findViewById(R.id.uName);

         retrofit = new Retrofit.Builder()
                .baseUrl("https://assessment.api.vweb.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

         getUser();
         getRides();

    }

    private void getUser(){


        Call<User> call = jsonPlaceHolderApi.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                User user = response.body();


                String content = "";
                content += "Name : " + user.getName() + "\n";
                content += "station code : " + user.getStation_code() + "\n";
                content += "URL : " + user.getUrl() + "\n";

                //textView.append(content);
                Toast.makeText(trial.this, content, Toast.LENGTH_LONG).show();

                uName.setText(user.getName());

                if (user.getUrl().equals("default")||user.getUrl().isEmpty()){
                    profile_image.setImageResource(R.drawable.default_image);
                    //getSupportActionBar().setLogo(getDrawable(R.mipmap.ic_launcher));
                } else{
                    Glide.with(getApplicationContext()).load(user.getUrl()).into(profile_image);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //textView.setText(t.getMessage());
                Toast.makeText(trial.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getRides(){

        recyclerView = findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Ride>> call = jsonPlaceHolderApi.getRides();

        call.enqueue(new Callback<List<Ride>>() {
            @Override
            public void onResponse(Call<List<Ride>> call, Response<List<Ride>> response) {
                if(!response.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_LONG).show();
                    //textView.setText("Code : " + response.code());
                    return;
                }




                List<Ride> rides = response.body();


              Collections.sort(rides, new Comparator<Ride>() {
                    @Override
                    public int compare(Ride lhs, Ride rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return lhs.getId() > rhs.getId() ? 1 : (lhs.getId() < rhs.getId() ) ? 1 : 0;
                    }
                });

                mAdapter = new ride_adapter(getApplicationContext(),rides);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Ride>> call, Throwable t) {
                //textView.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}