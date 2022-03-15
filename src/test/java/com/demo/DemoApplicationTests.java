package com.demo;

import com.repositories.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UtilisateurRepository userRepository;

	@Test
	void testCreationUtilisateur() {
		assertNotEquals(userRepository.count(), 0);



	}

}
