package com.example.petlife.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Pet implements Serializable {

    private Integer id;
    private String nome;
    private Integer idade;
    private String raca;
    private String tipo;
    private String sexo;
    private Integer vacinado;
    private Integer castrado;
    private Integer userId;
    private String petPictureUrl;

    public Pet() {}

    public Pet(Integer id, String nome, Integer idade, String raca, String tipo, String sexo, Integer vacinado, Integer castrado, Integer userId, String petPictureUrl) {
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
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

    public Integer getVacinado() {
        return vacinado;
    }

    public void setVacinado(Integer vacinado) {
        this.vacinado = vacinado;
    }

    public Integer getCastrado() {
        return castrado;
    }

    public void setCastrado(Integer castrado) {
        this.castrado = castrado;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", raca='" + raca + '\'' +
                ", tipo='" + tipo + '\'' +
                ", sexo='" + sexo + '\'' +
                ", vacinado=" + vacinado +
                ", castrado=" + castrado +
                ", userId=" + userId +
                ", petPictureUrl=" + petPictureUrl +
                '}';
    }
}
