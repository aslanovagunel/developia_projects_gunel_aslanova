package java20.developia.springJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJavaApplication.class, args);
		ozunDaxilEt("basqa mesaj");
	}

	@GetMapping("/car")
	Car Car() {
		return new Car("Ford", "focus", 12345);

	}
	@GetMapping()
	String soz() {
		return "soz deyildi";
	}

	@GetMapping("/mesaj")
	String mesaj() {
		return "hell olundu";
	}

	@GetMapping("/mesaj/daxili")
	String mesaj(String message) {
		message = "daxili mesaj gonderildi";
		return message;
	}

	@GetMapping("/basqaMesaj")
	static String ozunDaxilEt(String message) {
		return message;
	}
}
