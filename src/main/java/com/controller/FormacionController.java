package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Formacion;
import com.service.FormacionService;

@RestController
public class FormacionController {
	
	@Autowired
	FormacionService formacionService;

	@GetMapping(value="formaciones",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> devuelveAllFormaciones() {
		System.out.println("En el Controler del micro formaciones, devuelveAllFormaciones");
		return formacionService.getAllFormaciones();
	}
	
	@PostMapping(value="formacion",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String creaFormacion(@RequestBody Formacion formacion) {
		return formacionService.createFormacion(formacion) ? "Formacion creada correctamente" : "Ya existe una formación con este nombre";
	}
	
	
}
