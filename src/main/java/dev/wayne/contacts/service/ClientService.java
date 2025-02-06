package dev.wayne.contacts.service;

import dev.wayne.contacts.entity.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    Client create(Client client);

    List<Client> findAll(Pageable pageable);

    Client findById(Long id);
}
