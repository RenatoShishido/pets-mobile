package com.example.petlife.activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.petlife.entities.Pet;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.petlife.R;

public class PetActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();
        Pet pet  = (Pet)  it.getSerializableExtra("pet");

    }

}