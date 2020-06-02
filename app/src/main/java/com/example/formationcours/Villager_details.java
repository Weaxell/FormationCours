package com.example.formationcours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Villager_details extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewBirthDay;
    private TextView textViewSpecies;
    private TextView textViewGender;
    private TextView textViewPersonality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villager_details);

        imageView = findViewById(R.id.imageView);
        textViewName = findViewById(R.id.txtDetailName);
        textViewBirthDay = findViewById(R.id.txtDetailBirthday);
        textViewSpecies = findViewById(R.id.txtDetailSpecies);
        textViewGender = findViewById(R.id.txtDetailGender);
        textViewPersonality = findViewById(R.id.txtDetailPersonality);

        Villager villager = (Villager) getIntent().getSerializableExtra("villager");

        new DownloadImageTask(imageView).execute(villager.getIcon_uri());
        textViewName.setText(villager.getNameFR());
        textViewBirthDay.setText(villager.getBirthday());
        textViewSpecies.setText(villager.getSpecies());
        textViewGender.setText(villager.getGender());
        textViewPersonality.setText(villager.getPersonality());
    }
}
