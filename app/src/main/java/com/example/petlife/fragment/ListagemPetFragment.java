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
import com.example.petlife.entities.Pet;

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

        List<Pet> pets = new ArrayList<>();

        Pet p = new Pet();
        p.setNome("Mel");
        p.setId(R.drawable.dog);
        pets.add(p);

        Pet p1 = new Pet();
        p1.setNome("Lett");
        p1.setId(R.drawable.gato2);
        pets.add(p1);

        Pet p2 = new Pet();
        p2.setNome("trim");
        p2.setId(R.drawable.gato);
        pets.add(p2);

        Pet p3 = new Pet();
        p3.setNome("Guto");

        p3.setId(R.drawable.dog2);
        pets.add(p3);

        CardAdapter cardAdapter = new CardAdapter(pets);
        rcvCardPets.setAdapter(cardAdapter);

        return view;
    }
}