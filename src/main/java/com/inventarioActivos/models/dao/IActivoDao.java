package com.inventarioActivos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.inventarioActivos.model.entity.Activo;

public interface IActivoDao extends CrudRepository<Activo, Long>{

}
