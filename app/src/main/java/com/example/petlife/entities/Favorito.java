package com.example.petlife.entities;

import java.io.Serializable;

public class Favorito implements Serializable {
    private Integer id;
    private Integer petId;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Favorito{" +
                "id=" + id +
                ", petId=" + petId +
                ", userId=" + userId +
                '}';
    }
}
