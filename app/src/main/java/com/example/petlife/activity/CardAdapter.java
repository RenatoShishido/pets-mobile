package com.example.petlife.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petlife.R;
import com.example.petlife.entities.Pet;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<Pet> pets;


    public CardAdapter(List<Pet> pets) {
        this.pets = pets;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_pet, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull  CardAdapter.MyViewHolder holder, int position) {

        Pet pet = pets.get(position);

        holder.petNome.setText(pet.getNome());
        holder.petDescricao.setText("qualquerCoisa");
        holder.petImage.setImageResource(pet.getId());

    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView petNome, petDescricao;
        private ImageView petImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            petNome = itemView.findViewById(R.id.petNome);
            petDescricao = itemView.findViewById(R.id.petDescricao);
            petImage = itemView.findViewById(R.id.petImage);
        }

    }

}
