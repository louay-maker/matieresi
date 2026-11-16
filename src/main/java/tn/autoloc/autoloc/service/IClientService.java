package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> afficherTousClients();
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    Client afficherClientById(Long id);
    void supprimerClient(Long id);
}
