package com.example.petlife;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import com.example.petlife.fragment.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdotar,btnDoar,btnFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarItens();

        btnAdotar.setOnClickListener(this);
        btnDoar.setOnClickListener(this);
        btnFavoritos.setOnClickListener(this);

    }

    private void inicializarItens() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        btnAdotar = findViewById(R.id.btnAdotar);
        btnDoar = findViewById(R.id.btnDoar);
        btnFavoritos = findViewById(R.id.btnFavoritos);
        CadastroDoacaoFragment doarFragment = new CadastroDoacaoFragment();//add fragment to transaction
        fragmentTransaction.replace(R.id.fragment_container,doarFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(view.equals(btnDoar)){

            CadastroDoacaoFragment doarFragment = new CadastroDoacaoFragment();//add fragment to transaction
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container,doarFragment);
            fragmentTransaction.commit();




        }else if(view.equals(btnFavoritos)){

            ListagemFavoritoFragment favoritoFragment = new ListagemFavoritoFragment();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container,favoritoFragment);
            fragmentTransaction.commit();

        }else if(view.equals(btnAdotar)){

            ListagemPetFragment adotarFragment = new ListagemPetFragment();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container,adotarFragment);
            fragmentTransaction.commit();
        }
    }
}