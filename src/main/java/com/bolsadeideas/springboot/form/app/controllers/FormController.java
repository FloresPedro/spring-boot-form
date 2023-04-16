package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("Titulo", "Formulario usuarios");
		return "form";
	}
	
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
			/*
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			model.addAttribute("email", email);
			*/
			model.addAttribute("usuario",usuario);
		return "resultado";
	}
}
