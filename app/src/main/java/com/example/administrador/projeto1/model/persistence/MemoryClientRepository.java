package com.example.administrador.projeto1.model.persistence;

import com.example.administrador.projeto1.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class MemoryClientRepository implements ClientRepository {

    private static MemoryClientRepository singletonInstance;
    private List<Client> clients;

    private MemoryClientRepository() {
        super();
        clients = new ArrayList<Client>();
    }

    public static ClientRepository getInstance() {
        if (MemoryClientRepository.singletonInstance == null) {
            MemoryClientRepository.singletonInstance = new MemoryClientRepository();
        }
        return MemoryClientRepository.singletonInstance;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryClientRepository)) return false;

        MemoryClientRepository that = (MemoryClientRepository) o;

        return !(clients != null ? !clients.equals(that.clients) : that.clients != null);

    }

    @Override
    public int hashCode() {
        return clients != null ? clients.hashCode() : 0;
    }
}
