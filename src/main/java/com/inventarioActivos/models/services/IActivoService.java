package com.inventarioActivos.models.services;

import java.util.List;
import java.util.Optional;

import com.inventarioActivos.model.entity.Activo;

public interface IActivoService {

	
	public List<Activo> findAll();
	
	public Optional<Activo> findById(Long id);
}
