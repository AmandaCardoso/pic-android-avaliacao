package com.example.administrador.projeto1.model.entities;

/**
 * Created by Cardoso on 30/07/2015.
 */
public class Usuario {

    private String id;
    private String name;
    private String senha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
