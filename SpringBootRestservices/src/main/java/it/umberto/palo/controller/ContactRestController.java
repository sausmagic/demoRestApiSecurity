package it.umberto.palo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.umberto.palo.model.Contact;
import it.umberto.palo.repository.ContactRepository;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {
	  
	  @Autowired
	  private ContactRepository repo;
	  
	  @RequestMapping(name="getall", method=RequestMethod.GET, value="getAll")
	  public List<Contact> getAll() {
	    return repo.findAll();
	  }
	  
	  @RequestMapping(name="add", method=RequestMethod.POST, value="/add")
	  public Contact create(@RequestBody Contact contact) {
	    return repo.save(contact);
	  }
	  
	  @RequestMapping(name="delete",method=RequestMethod.DELETE, value="/delete/{id}")
	  public void delete(@PathVariable String id) {
	    repo.delete(id);
	  }
	  
	  @RequestMapping(name="getContact",method=RequestMethod.GET, value="/get/{id}")
	  public Contact getContact(@PathVariable String id) throws NullPointerException{
		  List<Contact> all = repo.findAll();
		  for (Contact contact : all) {
			if(contact.getId().equals(id)) {
				return contact;
			}
		}
		return null;
	  }
	  
	  @RequestMapping(name="update", method=RequestMethod.PUT, value="/update/{id}")
	  public Contact update(@PathVariable String id, @RequestBody Contact contact) {
	    Contact update = repo.findOne(id);
	    update.setAddress(contact.getAddress());
	    update.setEmail(contact.getEmail());
	    update.setFacebookProfile(contact.getFacebookProfile());
	    update.setFirstName(contact.getFirstName());
	    update.setGooglePlusProfile(contact.getGooglePlusProfile());
	    update.setLastName(contact.getLastName());
	    update.setLinkedInProfile(contact.getLinkedInProfile());
	    update.setPhoneNumber(contact.getPhoneNumber());
	    update.setTwitterHandle(contact.getTwitterHandle());
	    return repo.save(update);
	  }
}
