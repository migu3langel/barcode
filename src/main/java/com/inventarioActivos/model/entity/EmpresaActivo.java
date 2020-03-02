package com.inventarioActivos.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empresa_activo database table.
 * 
 */
@Entity
@Table(name="empresa_activo")

public class EmpresaActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_empresa")
	private int idEmpresa;

	private String descripcion;

	//bi-directional many-to-one association to Activo
//	@OneToMany(mappedBy="empresaActivo")
//	private Set<Activo> activos;

	public EmpresaActivo() {
	}

	public int getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public Set<Activo> getActivos() {
//		return this.activos;
//	}
//
//	public void setActivos(Set<Activo> activos) {
//		this.activos = activos;
//	}

//	public Activo addActivo(Activo activo) {
//		getActivos().add(activo);
//		activo.setEmpresaActivo(this);
//
//		return activo;
//	}
//
//	public Activo removeActivo(Activo activo) {
//		getActivos().remove(activo);
//		activo.setEmpresaActivo(null);
//
//		return activo;
//	}

}