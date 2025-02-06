package dev.wayne.contacts.service.impl;

import dev.wayne.contacts.entity.Contact;
import dev.wayne.contacts.exception.ApiException;
import dev.wayne.contacts.repository.ContactRepository;
import dev.wayne.contacts.service.ClientService;
import dev.wayne.contacts.service.ContactService;
import dev.wayne.contacts.util.PhoneCheck;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ClientService clientService;

    @Override
    @Transactional
    public Contact create(Contact contact) {
        if ((contact.getType() == null) || contact.getType().isBlank()) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Тип контакта не задан");
        }
        validateType(contact.getType());
        if (contact.getType().equals(Contact.ContactType.phone.name())) {
            contact.setValue(PhoneCheck.validatePhoneNumber(contact.getValue()));
        } else if ((contact.getValue() == null) || contact.getValue().isBlank()) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Данные контакта не заданы");
        }

        if (contact.getClientId() == null) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Идентификатор клиента не задан");
        }
        clientService.findById(contact.getClientId());

        return contactRepository.save(contact);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllByClientId(Long clientId, String type, Pageable pageable) {
        if (clientId == null) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Идентификатор клиента не задан");
        }
        clientService.findById(clientId);

        if ((type != null) && !type.isBlank()) {
            validateType(type);
        } else {
            // защита от пустых String ("")
            type = null;
        }

        return (pageable == null)
                ? contactRepository.findByClientIdAndType(clientId, type)
                : contactRepository.findByClientIdAndType(clientId, type, pageable).getContent();
    }

    private void validateType(String contactType) {
        for (Contact.ContactType type : Contact.ContactType.values()) {
            if (type.name().equals(contactType)) {
                return;
            }
        }

        throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "Указанный тип контакта " + contactType + " не предусмотрен системой");
    }
}
