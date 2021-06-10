package com.example.petlife.validators;

import com.example.petlife.entities.Pet;
import com.example.petlife.entities.Usuario;
import com.example.petlife.validators.exception.ArgumentInvalidException;

public class Validator {
    public static void checkUser(Usuario usuario) {
        checkAuth(usuario);

        if(usuario.getNome() == null || usuario.getNome().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");

    }

    public static void checkAuth(Usuario usuario) {
        if(usuario.getEmail() == null || usuario.getEmail().isEmpty())
            throw new ArgumentInvalidException("Email é invalido");

        if(usuario.getPassword() == null || usuario.getPassword().isEmpty())
            throw new ArgumentInvalidException("Password é invalida");
    }

    public static void checkPet(Pet pet) {
        if(pet.getNome() == null || pet.getNome().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");

        if(pet.getRaca() == null || pet.getRaca().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");

        if(pet.getTipo() == null || pet.getTipo().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");

        if(pet.getSexo() == null || pet.getSexo().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");

        if(pet.getVacinado() == null || pet.getVacinado().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");

        if(pet.getCastrado() == null || pet.getCastrado().isEmpty())
            throw new ArgumentInvalidException("Nome é invalido");
    }
}
