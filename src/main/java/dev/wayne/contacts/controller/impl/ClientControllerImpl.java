package dev.wayne.contacts.controller.impl;

import dev.wayne.contacts.controller.ClientController;
import dev.wayne.contacts.entity.Client;
import dev.wayne.contacts.service.ClientService;
import dev.wayne.contacts.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @Override
    public Client create(Client client) {
        return clientService.create(client);
    }

    @Override
    public List<Client> findAll(Integer pageNum, Integer pageSize, String sortField, PageableUtil.SortDirection sortDirection) {
        return clientService.findAll(PageableUtil.generatePageable(pageNum, pageSize, sortField, sortDirection));
    }

    @Override
    public Client findById(Long id) {
        return clientService.findById(id);
    }
}
