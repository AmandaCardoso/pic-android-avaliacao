package com.example.administrador.projeto1.model.persistence;

import com.example.administrador.projeto1.model.entities.Client;

import java.util.List;

public interface ClientRepository {
    public abstract void save(Client client);
    public abstract List<Client> getAll();
    public abstract void delete(Client client);

}
