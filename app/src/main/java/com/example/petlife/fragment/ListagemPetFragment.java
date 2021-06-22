package com.example.petlife.fragment;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petlife.R;
import com.example.petlife.activity.CardAdapter;
import com.example.petlife.dao.PetDAO;
import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Session;

import java.util.ArrayList;
import java.util.List;

public class ListagemPetFragment extends Fragment {

    private RecyclerView rcvCardPets;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adotar,container,false);

        rcvCardPets = view.findViewById(R.id.rcvCardPets);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvCardPets.setLayoutManager(layoutManager);
        PetDAO petDAO = new PetDAO(getActivity());


        List<Pet> pets = new ArrayList<>();
        CardAdapter cardAdapter;
        if(Session.getSession().isLogged()) {
             cardAdapter = new CardAdapter(petDAO.getAll(), Session.getSession().getFavoritos() );
        }
        else {
             cardAdapter = new CardAdapter(petDAO.getAll(), null);
        }

        rcvCardPets.setAdapter(cardAdapter);

        return view;
    }
}