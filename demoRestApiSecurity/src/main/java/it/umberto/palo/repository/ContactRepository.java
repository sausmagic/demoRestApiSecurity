package it.umberto.palo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.umberto.palo.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
