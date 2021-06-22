package com.example.petlife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.petlife.database.ConectionSqliteHelper;
import com.example.petlife.entities.Usuario;
import com.example.petlife.validators.Validator;
import com.example.petlife.validators.exception.ArgumentInvalidException;

import org.mindrot.jbcrypt.BCrypt;


public class UsuarioDAO {

    SQLiteDatabase db;

    public UsuarioDAO(Context context) {
        db = ConectionSqliteHelper.getInstance(context).getWritableDatabase();
    }

    private void encodeHashPassword(Usuario usuario) {

        String salt = BCrypt.gensalt();
        String passwordHash = BCrypt.hashpw(usuario.getPassword(), salt);

        usuario.setPassword(passwordHash);

    }


    public long insert(Usuario usuario) {
        try {
            Validator.checkUser(usuario);

            encodeHashPassword(usuario);

            ContentValues values  = new ContentValues();
            values.put("nome", usuario.getNome());
            values.put("email", usuario.getEmail());
            values.put("telefone", usuario.getTelefone());
            values.put("password", usuario.getPassword());
            values.put("endereco", usuario.getEndereco());
            values.put("userPictureUrl", usuario.getUserPictureUrl());

            return db.insertOrThrow("usuario", null, values);

        } catch (SQLiteException e) {
            throw new IllegalArgumentException("Erro ao cadastrar");
        } finally {
            db.close();
        }
    }

    public long update(Usuario usuario) {
        try {
            ContentValues values  = new ContentValues();
            values.put("nome", usuario.getNome());
            values.put("email", usuario.getEmail());
            values.put("telefone", usuario.getTelefone());
            values.put("password", usuario.getPassword());
            values.put("endereco", usuario.getEndereco());
            values.put("userPictureUrl", usuario.getUserPictureUrl());
            String[] args = {String.valueOf(usuario.getId())};
            return db.update("usuario",values, "id=?", args);

        } catch (SQLiteException e) {
            throw new IllegalArgumentException("Erro ao cadastrar");
        } finally {
            db.close();
        }
    }

    public Usuario getByEmail(String email) {
        try {
            String queryUser =
                    String.format("SELECT * FROM usuario WHERE email = \""+ email + "\"" );
            Cursor cursor = db.rawQuery(queryUser, null);

            if(cursor == null) {
                throw new ArgumentInvalidException("Usuario nao encontrado");
            }

            Usuario usuario = new Usuario();
            while(cursor.moveToNext()){
                usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
                usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                usuario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                usuario.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                usuario.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                usuario.setUserPictureUrl(cursor.getString(cursor.getColumnIndex("userPictureUrl")));
            }

            return usuario;
        } catch (SQLiteException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public Usuario getById(Integer id) {
        try {
            String queryUser =
                    String.format("SELECT * FROM usuario WHERE id = \""+ id + "\"" );
            Cursor cursor = db.rawQuery(queryUser, null);

            if(cursor == null) {
                throw new ArgumentInvalidException("Usuario nao encontrado");
            }

            Usuario usuario = new Usuario();
            while(cursor.moveToNext()){
                usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
                usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                usuario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                usuario.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                usuario.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                usuario.setUserPictureUrl(cursor.getString(cursor.getColumnIndex("userPictureUrl")));
            }

            return usuario;
        } catch (SQLiteException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }


    public boolean auth(Usuario usuario) {
        Validator.checkAuth(usuario);
        String password = usuario.getPassword();

        Usuario usuarioBanco = this.getByEmail(usuario.getEmail());
        String senhaBanco = usuarioBanco.getPassword();

        return BCrypt.checkpw(password, senhaBanco);
    }

}
