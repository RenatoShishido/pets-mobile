package com.example.petlife.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;


import com.example.petlife.R;
import com.example.petlife.fragment.*;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Button btnAdotar, btnDoar, btnFavoritos;
    boolean isLogged = false;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        ListagemPetFragment adotarFragment = new ListagemPetFragment();
        fragmentTransaction.replace(R.id.fragment_container, adotarFragment);
        fragmentTransaction.commit();



        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (view.equals(btnDoar)) {
            if (isLogged) {


                CadastroPetFragment doarFragment = new CadastroPetFragment();//add fragment to transaction
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_container, doarFragment);
                fragmentTransaction.commit();
            } else {
                HandleLoginFragment handleLoginFragment = new HandleLoginFragment();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_container, handleLoginFragment);
                fragmentTransaction.commit();

            }

        } else if (view.equals(btnFavoritos)) {
            if (isLogged) {

                ListagemFavoritoFragment favoritoFragment = new ListagemFavoritoFragment();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_container, favoritoFragment);
                fragmentTransaction.commit();
            } else {
                HandleLoginFragment handleLoginFragment = new HandleLoginFragment();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_container, handleLoginFragment);
                fragmentTransaction.commit();
            }
        } else if (view.equals(btnAdotar)) {


            ListagemPetFragment adotarFragment = new ListagemPetFragment();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container, adotarFragment);
            fragmentTransaction.commit();

    }


}


    @Override
    public void onBackPressed() {


        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_adotar:
                this.onClick(btnAdotar);

                break;
            case R.id.nav_doar:

                this.onClick(btnDoar);
                break;

            case R.id.nav_login:

                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
//            case R.id.nav_login: menu.findItem(R.id.nav_logout).setVisible(true);
//                menu.findItem(R.id.nav_profile).setVisible(true);
//                menu.findItem(R.id.nav_login).setVisible(false);
//                break;
//            case R.id.nav_logout: menu.findItem(R.id.nav_logout).setVisible(false);
//                menu.findItem(R.id.nav_profile).setVisible(false);
//                menu.findItem(R.id.nav_login).setVisible(true);
//                break;
//            case R.id.nav_share: Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;

        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }
}