package com.example.petlife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.petlife.database.ConectionSqliteHelper;
import com.example.petlife.entities.Favorito;
import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Usuario;
import com.example.petlife.validators.Validator;
import com.example.petlife.validators.exception.ArgumentInvalidException;


import java.util.ArrayList;
import java.util.List;

public class FavoritoDAO {

    SQLiteDatabase db;

    public FavoritoDAO(Context context) {
        db = ConectionSqliteHelper.getInstance(context).getWritableDatabase();
    }

    public long insert(Favorito favorito) {
        try {

            ContentValues values = new ContentValues();

            values.put("userId", favorito.getUserId());
            values.put("petId", favorito.getPetId());

            return db.insertOrThrow("favorito", null, values);

        } catch (SQLiteException e) {
            throw new IllegalArgumentException("Erro ao Favoritar");
        }
    }
    public List<Favorito> getFavoritosByUser (int userId) {
        try {
            String queryUser =
                    String.format("SELECT * FROM favorito " +
                            "WHERE favorito.userId =  " + userId);

            Cursor cursor = db.rawQuery(queryUser, null);

            if (cursor == null) {
                throw new ArgumentInvalidException("Nao existem favoritos");
            }

            List<Favorito> favoritoList = new ArrayList<Favorito>();
            while (cursor.moveToNext()) {
                Favorito favorito = new Favorito();

                favorito.setId(cursor.getInt(cursor.getColumnIndex("id")));
                favorito.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                favorito.setPetId(cursor.getInt(cursor.getColumnIndex("petId")));

                favoritoList.add(favorito);
            }

            return favoritoList;
        } catch (SQLiteException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public long delete(int favoritoId) {

        try {

            String[] args = {String.valueOf(favoritoId)};
            return  db.delete("favorito", "id" + "=?", args);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException("Erro ao Retirar Favorito");
        }
    }

}
