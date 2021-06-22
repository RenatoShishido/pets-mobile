package com.example.petlife.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petlife.R;
import com.example.petlife.dao.FavoritoDAO;
import com.example.petlife.dao.PetDAO;
import com.example.petlife.entities.Favorito;
import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Session;
import com.example.petlife.utils.Utils;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<Pet> pets;
    private List<Favorito> favoritos;


    public CardAdapter(List<Pet> pets, List<Favorito> favoritos) {
        this.pets = pets;
        this.favoritos = favoritos;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_pet, parent, false);

        return new MyViewHolder(itemLista);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, int position) {

        Pet pet = pets.get(position);

        holder.petNome.setText(pet.getNome());
        holder.petDescricao.setText(pet.getTipo());
        holder.petSexo.setText(pet.getRaca());


        if (pet.getPetPictureUrl() != null && !pet.getPetPictureUrl().isEmpty()) {
            byte[] imagemBytes = Base64.decode(pet.getPetPictureUrl(), Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imagemBytes, 0, imagemBytes.length);
            holder.petImage.setImageBitmap(imagemDecodificada);

        }
        if (Session.getSession().isLogged()) {
            List<Favorito> favs = favoritos.stream().filter(f -> f.getPetId() == pet.getId()).collect(Collectors.toList());
            if (favs.size() > 0) {
                holder
                .petFavorito
                .setCompoundDrawablesRelativeWithIntrinsicBounds
                (0, R.drawable.ic_baseline_favorite_24, 0, 0);

                holder.petFavorito.setText("Desfavoritar");

            }
            else {
                holder
                .petFavorito
                .setCompoundDrawablesRelativeWithIntrinsicBounds
                (0, R.drawable.ic_baseline_favorite_border_24, 0, 0);

                holder.petFavorito.setText("Favoritar");
            }
        }
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView petNome, petDescricao, petSexo;
        private ImageButton petImage;
        private Button petFavorito;
        private FavoritoDAO favoritoDAO;
        private int userId;
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            petNome = itemView.findViewById(R.id.petNome);
            petSexo = itemView.findViewById(R.id.petSexo);
            petDescricao = itemView.findViewById(R.id.petDescricao);
            petImage = itemView.findViewById(R.id.petImage);
            petFavorito = itemView.findViewById(R.id.petFavorito);
            favoritoDAO = new FavoritoDAO(view.getContext());

            petImage.setOnClickListener(v -> {
                onClickBtnImage();
            });
            petFavorito.setOnClickListener(v -> {
                onClickBtnFavorito();
            });

        }


        public void onClickBtnImage() {
            try {
                Pet pet = pets.get(getAdapterPosition());
                Intent it = new Intent(view.getContext(), PetActivity.class);
                it.putExtra("pet", pet);
                view.getContext().startActivity(it);
            } catch (Exception exception) {
                Log.d("teste", exception.getMessage());

            }

        }

        public void onClickBtnFavorito() {

            try {
                if (!Session.getSession().isLogged()) {
                    Utils.aviso(view.getContext(), "É precisso estar " +
                            "logado para realizar essa ação");
                    return;
                }

                Pet pet = pets.get(getAdapterPosition());

                List<Favorito> favs = favoritos.stream().filter(f -> f.getPetId() == pet.getId()).collect(Collectors.toList());
                if (favs.size() > 0) {
                    Desfavoritar(pet);
                } else {
                    Favoritar(pet);
                }

            } catch (Exception exception) {
                Log.d("teste", exception.getMessage());

            }
        }

        public void Favoritar(Pet pet) {


            userId = Session.getSession().getUsuario().getId();

            Favorito favorito = new Favorito();
            favorito.setPetId(pet.getId());
            favorito.setUserId(Session.getSession().getUsuario().getId());
            try {
                petFavorito.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0, R.drawable.ic_baseline_favorite_24, 0, 0);
                petFavorito.setText("Desfavoritar");
                favoritoDAO.insert(favorito);
            } catch (Exception ex) {

            }
            finally {
                favoritos = favoritoDAO.getFavoritosByUser(userId);
            }
        }

        public void Desfavoritar(Pet pet) {


            userId = Session.getSession().getUsuario().getId();
            try {
                petFavorito.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0, R.drawable.ic_baseline_favorite_border_24, 0, 0);
                petFavorito.setText("Favoritar");

                List<Favorito> favs =
                        favoritos.stream().filter(f -> f.getPetId() == pet.getId() &&
                        f.getUserId() == userId).collect(Collectors.toList());

                if(favs.size() > 0) {
                    for (Favorito item:favs)
                          {
                              favoritoDAO.delete(item.getId());
                    }
                }

            } catch (Exception ex) {

            }
            finally {
                favoritos = favoritoDAO.getFavoritosByUser(userId);
            }
        }


    }
}


