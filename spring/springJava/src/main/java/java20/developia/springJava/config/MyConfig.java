package java20.developia.springJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java20.developia.springJava.service.BookService;

@Configuration
public class MyConfig {

	@Bean
	public BookService service() {
		return new BookService(2);
	}
}
