package es.urjc.code.daw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SesionController {

	@Autowired
	private Usuario usuario;

	private String infoCompartida;

	@PostMapping(value = "/procesarFormulario")
	public String procesarFormulario(@RequestParam String info) {

		usuario.setInfo(info);
		infoCompartida = info;

		return "resultado_formulario";
	}

	@GetMapping("/mostrarDatos")
	public String mostrarDatos(Model model) {

		String infoUsuario = usuario.getInfo();

		model.addAttribute("infoUsuario", infoUsuario);
		model.addAttribute("infoCompartida", infoCompartida);

		return "datos";
	}
}
