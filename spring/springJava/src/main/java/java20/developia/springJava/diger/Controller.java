package java20.developia.springJava.diger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/first-controller")
public class Controller {
	@GetMapping(path = "/car")
	Car Car() {
		return new Car(" ", "Focus", 12345);

	}

	@GetMapping()
	String soz() {
		return "soz deyildi";
	}

	@GetMapping(path = "/mesaj")
	String mesaj() {
		return "hell olundu";
	}
}
