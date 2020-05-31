package com.example.formationcours;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    static final String BASE_URL = "https://acnhapi.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        // makeAPIcall();
    }

    private void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AcnhAPI acnhAPI = retrofit.create(AcnhAPI.class);

        Call<RestAcnhAPIResponse> call = acnhAPI.getAcnhResponse();
        // call.enqueue(this);

    }

    /*
    private void makeAPIcall () {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://www.instagram.com/ihanan95/?__a=1", String.class);
        System.out.println("####### REPONSE API :\n" + response);
    }
     */

}
