package spring.library_gunel_aslanova.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.Data;

@Configuration
@Data
@EnableScheduling
public class MyConfig {


	@Value(value = "${row.count.limit}")
	private Integer rowCountLimit;

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
}
