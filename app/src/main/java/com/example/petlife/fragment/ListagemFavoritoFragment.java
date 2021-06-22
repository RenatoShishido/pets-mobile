package com.example.petlife.fragment;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petlife.R;
import com.example.petlife.activity.CardAdapter;
import com.example.petlife.dao.FavoritoDAO;
import com.example.petlife.dao.PetDAO;
import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Session;
import com.example.petlife.entities.Usuario;


import java.util.ArrayList;
import java.util.List;


public class ListagemFavoritoFragment extends Fragment {

    private RecyclerView rcvCardPetsFavoritos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorito,container,false);

        rcvCardPetsFavoritos = view.findViewById(R.id.rcvCardPetsFavoritos);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvCardPetsFavoritos.setLayoutManager(layoutManager);
        PetDAO petDAO = new PetDAO(getActivity());
        Usuario usuario = Session.getSession().getUsuario();

        CardAdapter cardAdapter = new CardAdapter(petDAO.getPetsFavoritosByUserID(usuario.getId()), Session.getSession().getFavoritos());
        rcvCardPetsFavoritos.setAdapter(cardAdapter);

        return view;
    }
}