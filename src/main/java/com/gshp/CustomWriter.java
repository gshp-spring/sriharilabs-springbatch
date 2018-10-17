package com.gshp;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.gshp.cosmo.UserRepository;
import com.gshp.cosmo.Users;

public class CustomWriter implements ItemWriter{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Users users;
	
	@Override
	public void write(List items) throws Exception {
		
		System.out.println(" Loggging...........1111");
		
		users.setCount(111l);
		users.setEmail("myenmam");
		users.setId("123");
		users.setName("srihri");
		userRepository.save(users);
	}

}
