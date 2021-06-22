package com.example.petlife.entities;

import java.util.List;

public class Session {

    private static Session session;
    private Usuario usuario;
    private boolean isLogged;
    private List<Favorito> favoritos;

    private Session() {}

    public static synchronized Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Favorito> getFavoritos() {return favoritos; }

    public void setFavoritos(List<Favorito> favoritos) {this.favoritos = favoritos;}

    public void setUsuario(Usuario usuario) {
       this.usuario = usuario;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

}
