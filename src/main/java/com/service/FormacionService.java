package com.service;

import java.util.List;

import com.model.Formacion;

public interface FormacionService {
	List<Formacion> getAllFormaciones();
	Boolean createFormacion(Formacion formacion);
}
