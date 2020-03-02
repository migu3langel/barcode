package com.inventarioActivos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventarioActivos.model.entity.Activo;
import com.inventarioActivos.models.services.IActivoService;

@RestController
@RequestMapping("/api")
public class ActivoRestController {

	@Autowired
	private IActivoService activoService;
	
	@GetMapping("/activos")
	public List<Activo> index(){
		
		return activoService.findAll();
		
	}
	
	@GetMapping("/activo/{id}")
	public Activo activo(@PathVariable Long id) {
		
		// control cuando venga null
		return activoService.findById(id).get();
	
	}
	
}
