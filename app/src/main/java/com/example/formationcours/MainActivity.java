package com.example.formationcours;

import android.os.Bundle;

import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

// import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Villager> villagerList;

    private ExecutorService executorService;

    private final String API_URL = "https://acnhapi.com/v1/villagers/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        villagerList = getAPIVillagersList();

        System.out.println("##### Taille de la liste : " + villagerList.size());

        showList();
    }

    private void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        /*
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
         */

        mAdapter = new ListAdapter(villagerList);
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Villager> getAPIVillagersList() {
        ArrayList<Villager> listRes = new ArrayList<Villager>();
        String strVillager = "";

        int i = 1;
        while(strVillager != null) {
            try {
                strVillager = null;
                strVillager = jsonGetVillagerRequest(API_URL + i);
            } catch(Exception e) {
                break;
            }

            Gson gson = new Gson();
            Villager villager = gson.fromJson(strVillager, Villager.class);
            listRes.add(villager);
            i++;
        }
        for(Villager v : listRes) {
            if(v == null)
                listRes.remove(v);
        }
        return listRes;
    }

    private String jsonGetVillagerRequest(String urlQueryString) {
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
