package com.inventarioActivos.model.entity;

import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the tipo_activo database table.
 * 
 */
@Entity
@Table(name="tipo_activo")

public class TipoActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo")
	private int idTipo;

	private String descripcion;

	//bi-directional many-to-one association to Activo
//	@OneToMany(mappedBy="tipoActivo")
//	private Set<Activo> activos;

	public TipoActivo() {
	}

	public int getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
//
//	public Set<Activo> getActivos() {
//		return this.activos;
//	}
//
//	public void setActivos(Set<Activo> activos) {
//		this.activos = activos;
//	}

//	public Activo addActivo(Activo activo) {
//		getActivos().add(activo);
//		activo.setTipoActivo(this);
//
//		return activo;
//	}
//
//	public Activo removeActivo(Activo activo) {
//		getActivos().remove(activo);
//		activo.setTipoActivo(null);
//
//		return activo;
//	}

}