package dev.wayne.contacts.service.impl;

import dev.wayne.contacts.repository.ContactRepository;
import dev.wayne.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
}
