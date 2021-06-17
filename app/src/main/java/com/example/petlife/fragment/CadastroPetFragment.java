package com.example.petlife.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.petlife.R;
import com.example.petlife.activity.MainActivity;
import com.example.petlife.dao.PetDAO;
import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Session;
import com.example.petlife.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CadastroPetFragment extends Fragment {

    EditText nomePET, racaPET, tipoPET, idadePET;
    ImageButton imageButtonPET;
    CheckBox castradoPET, vacinadoPET;
    Spinner sexoPET;
    Button doarBF;

    Pet pet = new Pet();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doar,container,false);
        doarBF = view.findViewById(R.id.doarBF);

        nomePET = view.findViewById(R.id.nomePET);
        racaPET = view.findViewById(R.id.racaPET);
        tipoPET = view.findViewById(R.id.tipoPET);
        idadePET = view.findViewById(R.id.idadePET);
        castradoPET = view.findViewById(R.id.checkbox_castrado);
        vacinadoPET = view.findViewById(R.id.checkbox_vacinado);
        imageButtonPET = view.findViewById(R.id.imageButtonPET);


        Spinner sexoPET = (Spinner) view.findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sexo, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexoPET.setAdapter(adapter);


        imageButtonPET.setOnClickListener(v -> {
            Intent intentPegaFoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intentPegaFoto, "Selecione uma imagem"), 123);
        });
        doarBF.setOnClickListener(v -> {
            if(nomePET.getText().toString().isEmpty())
                return;
            if(racaPET.getText().toString().isEmpty())
                return;
            if(idadePET.getText().toString().isEmpty())
                return;
            if(tipoPET.getText().toString().isEmpty())
                return;

            pet.setIdade(Integer.parseInt(idadePET.getText().toString()));
            pet.setNome(nomePET.getText().toString());
            pet.setRaca(racaPET.getText().toString());
            pet.setTipo(tipoPET.getText().toString());
            pet.setSexo(sexoPET.getSelectedItem().toString());
            pet.setVacinado(0);
            pet.setCastrado(0);
            pet.setUserId(Session.getSession().getUsuario().getId());

            PetDAO petDAO = new PetDAO(getActivity());

            try {
                petDAO.insert(pet);
                Utils.aviso(getActivity(),"Pet cadastrado com sucesso");
            }catch (Exception e) {
                Utils.aviso(getActivity(), e.getMessage());
            }

        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123){
                Uri imagemSelecionada = data.getData();
                imageButtonPET.setImageURI(Uri.parse(imagemSelecionada.toString()));
            }
        }
    }
}