package com.example.petlife.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Pet implements Serializable {

    private Integer id;
    private String nome;
    private String idade;
    private String raca;
    private String tipo;
    private String sexo;
    private String vacinado;
    private String castrado;
    private String userId;
    private String petPictureUrl;

    public Pet() {}

    public Pet(Integer id, String nome, String idade, String raca, String tipo, String sexo, String vacinado, String castrado, String userId, String petPictureUrl) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.tipo = tipo;
        this.sexo = sexo;
        this.vacinado = vacinado;
        this.castrado = castrado;
        this.userId = userId;
        this.petPictureUrl = petPictureUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getVacinado() {
        return vacinado;
    }

    public void setVacinado(String vacinado) {
        this.vacinado = vacinado;
    }

    public String getCastrado() {
        return castrado;
    }

    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetPictureUrl() {
        return petPictureUrl;
    }

    public void setPetPictureUrl(String petPictureUrl) {
        this.petPictureUrl = petPictureUrl;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
