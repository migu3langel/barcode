package com.inventarioActivos.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventarioActivos.model.entity.Activo;
import com.inventarioActivos.models.dao.IActivoDao;

@Service
public class ActivoServiceImpl implements IActivoService {

	@Autowired
	private IActivoDao activoDao;
	
	@Override
	public List<Activo> findAll() {

		return (List<Activo>) activoDao.findAll();
	}

	@Override
	public Optional<Activo> findById(Long id) {
		
		return activoDao.findById(id);
	}

}
