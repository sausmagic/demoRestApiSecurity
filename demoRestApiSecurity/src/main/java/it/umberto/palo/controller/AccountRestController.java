package it.umberto.palo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.umberto.palo.model.Account;
import it.umberto.palo.model.Contact;
import it.umberto.palo.repository.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountRestController {
	
	@Autowired
	AccountRepository accountRepo;
	
	@RequestMapping(name="getall", method=RequestMethod.GET, value="getAll")
	  public List<Account> getAll() {
	    return accountRepo.findAll();
	  }
	  
	  @RequestMapping(name="add", method=RequestMethod.POST, value="/add")
	  public Account create(@RequestBody Account contact) {
	    return accountRepo.save(contact);
	  }
	  
	  @RequestMapping(name="delete",method=RequestMethod.DELETE, value="/delete/{id}")
	  public void delete(@PathVariable String id) {
	    accountRepo.delete(id);
	  }
	  
	  @RequestMapping(name="getContact",method=RequestMethod.GET, value="/get/{id}")
	  public Account getContact(@PathVariable String id) throws NullPointerException{
		  List<Account> all = accountRepo.findAll();
		  for (Account account : all) {
			if(account.getId().equals(id)) {
				return account;
			}
		}
		return null;
	  }
	  
	  @RequestMapping(name="update", method=RequestMethod.PUT, value="/update/{id}")
	  public Account update(@PathVariable String id, @RequestBody Account account) {
		Account update = accountRepo.findOne(id);
	    update.setPassword(account.getPassword());
	    update.setUsername(account.getUsername());
	    return accountRepo.save(update);
	  }

}
