package dev.wayne.contacts.controller.impl;


import dev.wayne.contacts.controller.ContactController;
import dev.wayne.contacts.entity.Contact;
import dev.wayne.contacts.service.ContactService;
import dev.wayne.contacts.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactControllerImpl implements ContactController {

    private final ContactService contactService;

    @Override
    public Contact create(Contact contact) {
        return contactService.create(contact);
    }

    @Override
    public List<Contact> findAll(Long clientId, String type, Integer pageNum, Integer pageSize, String sortField, PageableUtil.SortDirection sortDirection) {
        return contactService.findAllByClientId(clientId, type, PageableUtil.generatePageable(pageNum, pageSize, sortField, sortDirection));
    }
}
