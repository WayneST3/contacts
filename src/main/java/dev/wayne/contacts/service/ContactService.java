package dev.wayne.contacts.service;

import dev.wayne.contacts.entity.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    Contact create(Contact contact);

    List<Contact> findAllByClientId(Long clientId, String type, Pageable pageable);
}
