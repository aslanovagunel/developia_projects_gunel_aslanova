package springJava20.balance_management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.Data;

@Configuration
@Data
@EnableScheduling
public class MyConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}
