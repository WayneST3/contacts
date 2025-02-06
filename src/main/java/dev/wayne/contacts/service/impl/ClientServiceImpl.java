package dev.wayne.contacts.service.impl;

import dev.wayne.contacts.repository.ClientRepository;
import dev.wayne.contacts.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
}
