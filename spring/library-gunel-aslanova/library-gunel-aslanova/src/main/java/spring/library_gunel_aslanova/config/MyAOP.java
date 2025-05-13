package spring.library_gunel_aslanova.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAOP {

	@Before(value = "execution(public * spring.library_gunel_aslanova.service.UserService.*(..))")
	public void aopMethod() {
		System.out.println("Method başlayır");
	}

}