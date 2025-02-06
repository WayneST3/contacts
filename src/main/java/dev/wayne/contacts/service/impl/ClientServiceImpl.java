package dev.wayne.contacts.service.impl;

import dev.wayne.contacts.entity.Client;
import dev.wayne.contacts.exception.ApiException;
import dev.wayne.contacts.repository.ClientRepository;
import dev.wayne.contacts.service.ClientService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public Client create(Client client) {
        if ((client.getName() == null) || client.getName().isBlank()) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Имя клиента не задано");
        }
        client.setId(null);
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll(Pageable pageable) {
        return (pageable == null)
                ? clientRepository.findAll()
                : clientRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        if (id == null) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Идентификатор не задан");
        }
        return clientRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpServletResponse.SC_NOT_FOUND, "Клиент не найден"));
    }
}
