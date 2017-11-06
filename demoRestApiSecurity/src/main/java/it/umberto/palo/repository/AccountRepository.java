package it.umberto.palo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.umberto.palo.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

	public Account findByUsername(String username);

}