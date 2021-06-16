package com.example.petlife.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.petlife.R;
import com.example.petlife.activity.MainActivity;
import com.example.petlife.dao.PetDAO;
import com.example.petlife.entities.Pet;
import com.example.petlife.utils.Utils;


public class CadastroPetFragment extends Fragment {

    EditText nomePET, racaPET, tipoPET, idadePET;
    CheckBox castradoPET, vacinadoPET;
    Spinner sexoPET;
    Button doarBF;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doar,container,false);
        doarBF = view.findViewById(R.id.doarBF);


        doarBF.setOnClickListener(v -> {
            Pet pet = criarPet(view);
            PetDAO petDAO = new PetDAO(getActivity());

            try {
            Intent it = new Intent(getActivity(), MainActivity.class);
            startActivity(it);
            Utils.aviso(getActivity(), petDAO.getById(1).toString());


            }catch (Exception e) {
                Utils.aviso(getActivity(), e.getMessage());
            }

        });


        return view;
    }

    private Pet criarPet(View view) {
        nomePET = view.findViewById(R.id.nomePET);
        racaPET = view.findViewById(R.id.racaPET);
        tipoPET = view.findViewById(R.id.tipoPET);
        idadePET = view.findViewById(R.id.idadePET);
        castradoPET = view.findViewById(R.id.checkbox_castrado);
        vacinadoPET = view.findViewById(R.id.checkbox_vacinado);
        sexoPET = view.findViewById(R.id.spinner1);

        Pet pet = new Pet();
        pet.setIdade(Integer.parseInt(idadePET.getText().toString()));
        pet.setNome(nomePET.getText().toString());
        pet.setRaca(racaPET.getText().toString());
        pet.setTipo(tipoPET.getText().toString());
        pet.setCastrado(Integer.parseInt(castradoPET.getText().toString()));
        pet.setVacinado(Integer.parseInt(vacinadoPET.getText().toString()));
        pet.setSexo(vacinadoPET.getText().toString());

        return pet;
    }
}