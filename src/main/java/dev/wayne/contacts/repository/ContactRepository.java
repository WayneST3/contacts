package dev.wayne.contacts.repository;

import dev.wayne.contacts.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(nativeQuery = true, value = """
            select * from main.contacts
            where client_id = :clientId
                and (:type is null or type = :type)
            """)
    List<Contact> findByClientIdAndType(Long clientId, String type);

    @Query(nativeQuery = true, value = """
            select * from main.contacts
            where client_id = :clientId
                and (:type is null or type = :type)
            """)
    Page<Contact> findByClientIdAndType(Long clientId, String type, Pageable pageable);
}
