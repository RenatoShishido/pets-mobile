package com.example.petlife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.petlife.database.ConectionSqliteHelper;
import com.example.petlife.entities.Pet;
import com.example.petlife.validators.Validator;
import com.example.petlife.validators.exception.ArgumentInvalidException;


import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    SQLiteDatabase db;

    public PetDAO(Context context) {
        db = ConectionSqliteHelper.getInstance(context).getWritableDatabase();
    }

    public long insert(Pet pet) {
        try {
            Validator.checkPet(pet);

            ContentValues values  = new ContentValues();
            values.put("nome", pet.getNome());
            values.put("idade", pet.getIdade());
            values.put("raca", pet.getRaca());
            values.put("tipo", pet.getTipo());
            values.put("sexo", pet.getSexo());
            values.put("castrado", pet.getCastrado());
            values.put("userId", pet.getUserId());
            values.put("petPictureUrl", pet.getPetPictureUrl());

            return db.insertOrThrow("pet", null, values);

        } catch (SQLiteException e) {
            throw new IllegalArgumentException("Erro ao cadastrar pet");
        } finally {
            db.close();
        }
    }

    public Pet getById(Integer id) {
        try {
            String query = String.format("SELECT * FROM pet WHERE id = " + id);
            Cursor cursor = db.rawQuery(query, null);

            if(cursor == null) {
                throw new ArgumentInvalidException("Pet nao encontrado");
            }

            Pet pet = new Pet();
            while(cursor.moveToNext()){
                pet.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pet.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                pet.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
                pet.setRaca(cursor.getString(cursor.getColumnIndex("raca")));
                pet.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                pet.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
                pet.setCastrado(cursor.getInt(cursor.getColumnIndex("castrado")));
                pet.setCastrado(cursor.getInt(cursor.getColumnIndex("vacinado")));
                pet.setUserId(cursor.getString(cursor.getColumnIndex("userId")));
                pet.setPetPictureUrl(cursor.getInt(cursor.getColumnIndex("petPictureUrl")));
            }

            return pet;
        } catch (SQLiteException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<Pet> getByNome(String nome) {
        try {
            String query = String.format("SELECT * FROM pet WHERE nome = " + nome);
            Cursor cursor = db.rawQuery(query, null);

            if(cursor == null) {
                throw new ArgumentInvalidException("Pets nao encontrados");
            }

            List<Pet> petList = new ArrayList<Pet>();
            while(cursor.moveToNext()){
                Pet pet = new Pet();
                pet.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pet.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                pet.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
                pet.setRaca(cursor.getString(cursor.getColumnIndex("raca")));
                pet.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                pet.setSexo(cursor.getString(cursor.getColumnIndex("sexo")));
                pet.setCastrado(cursor.getInt(cursor.getColumnIndex("castrado")));
                pet.setCastrado(cursor.getInt(cursor.getColumnIndex("vacinado")));
                pet.setUserId(cursor.getString(cursor.getColumnIndex("userId")));
                pet.setPetPictureUrl(cursor.getInt(cursor.getColumnIndex("petPictureUrl")));
            }

            return petList;
        } catch (SQLiteException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
