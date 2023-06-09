package com.bolsadeideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Pedro");
		usuario.setApellido("Flores");
		usuario.setIdentificador("123.654-pfc");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario",usuario);
		return "form";
	}
	/*
	@PostMapping("/form")
	public String procesar(Model model, 
			@RequestParam(name="username") String username,
			@RequestParam String password,
			@RequestParam String email) {
			
		//se instancia un objeto para poder pasar la informacion ya sea a la vista o a la persistencia
			Usuario usuario = new Usuario();
			usuario.setUsername(username);
			usuario.setPassword(password);
			usuario.setEmail(email);
			
			model.addAttribute("titulo", "Resultado Form");
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			model.addAttribute("email", email);
			
			model.addAttribute("usuario",usuario);
		return "resultado";
	}*/
	
	//Otra forma de poblar los datos 
	//Los parametros se deben de llamar igual
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario,BindingResult result, Model model, 
			SessionStatus status) {
		
		model.addAttribute("titulo", "Resultado Form");
			if (result.hasErrors()) {
				Map<String,String> errores = new HashMap<>();
				result.getFieldErrors().forEach(err ->{
					errores.put(err.getField(),"El campo ".concat( err.getField()).concat(" ").concat(err.getDefaultMessage()));
				});
				model.addAttribute("error",errores);
				return "form";
			}
			model.addAttribute("usuario",usuario);
			status.setComplete();
			
		return "resultado";
	}
}
