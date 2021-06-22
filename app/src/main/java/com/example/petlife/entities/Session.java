package com.example.petlife.entities;

public class Session {

    private static Session session;
    private Usuario usuario;
    private boolean isLogged;

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
