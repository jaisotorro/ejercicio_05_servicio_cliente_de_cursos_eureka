package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.model.Curso;
import com.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {

	@Autowired
	RestTemplate restTemplate;

	String url = "http://servicio-cursos/";
	
	@Override
	public List<Formacion> getAllFormaciones() {
		List<Formacion> formaciones = new ArrayList<>(); 
		Arrays.asList(restTemplate.getForObject(url+"cursos", Curso[].class)).forEach(c -> {
			Formacion formacon = new Formacion(c.getNombre(), c.getDuracion() < 50 ? 5 : 10, c.getPrecio());
			formaciones.add(formacon);
		});
		return formaciones;
	}

	@Override
	public Boolean createFormacion(Formacion formacion) {
		List<Curso> cursos = Arrays.asList(restTemplate.getForObject(url+"cursos", Curso[].class));
		if (cursos.stream().anyMatch(c -> c.getNombre().equals(formacion.getCurso()))) {
			return false;
		}
		restTemplate.postForLocation(url + "curso", new Curso(formacion.getCurso().substring(0, 3).toUpperCase(),
				formacion.getCurso(), formacion.getAsignaturas() * 10, formacion.getPrecio()));
		return true;
	}

}
