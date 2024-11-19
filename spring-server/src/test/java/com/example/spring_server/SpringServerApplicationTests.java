package com.example.spring_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestSecurityConfig.class)
class SpringServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
