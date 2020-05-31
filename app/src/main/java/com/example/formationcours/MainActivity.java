package com.example.formationcours;

import android.os.Bundle;

import com.example.formationcours.unused.AcnhAPI;
import com.example.formationcours.unused.RestAcnhAPIResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ExecutorService executorService;

    static final String BASE_URL = "https://acnhapi.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        System.out.println("####### Avant la requete");
        System.out.println("####### Resultat de la requete :\n" + jsonGetRequest("https://acnhapi.com/v1/villagers/56"));
        System.out.println("####### Apres la requete");
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

    private String getVillagersJson() {
        return null;
    }

    private String jsonGetRequest(String urlQueryString) {
        CallAPI callableAPI = new CallAPI(urlQueryString);
        executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callableAPI);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }

        return "####### IL N Y A SUREMENT PAS INTERNET";
    }





    /*
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
     */


    /*
    private void makeAPIcall () {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://www.instagram.com/ihanan95/?__a=1", String.class);
        System.out.println("####### REPONSE API :\n" + response);
    }
     */

}
