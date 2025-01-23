package java20.developia.springJava.diger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/second-controller")
public class SecondController {
	@GetMapping(path = "/mesaj")
	String mesaj() {
		return "hell olundu";
	}

	String[] soz = { "kitab", "qelem", "defter", "acar", "qapi" };

	@GetMapping(path = "mini-google")
	public List<String> m1(@RequestParam(name = "sorgu", required = false, defaultValue = "qe") String a) {
		List<String> axtarilan = new ArrayList<String>();
		for (String s : soz) {
			if (s.contains(a)) {
				axtarilan.add(s);
			}
		}

		return axtarilan;
	}
}
