package com.inventarioActivos.model.entity;

import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the ubicacion_activo database table.
 * 
 */
@Entity
@Table(name="ubicacion_activo")

public class UbicacionActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ubicacion")
	private int idUbicacion;

	private String descripcion;

	//bi-directional many-to-one association to Activo
//	@OneToMany(mappedBy="ubicacionActivo")
//	private Set<Activo> activos;

	public UbicacionActivo() {
	}

	public int getIdUbicacion() {
		return this.idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
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
//
//	public Activo addActivo(Activo activo) {
//		getActivos().add(activo);
//		activo.setUbicacionActivo(this);
//
//		return activo;
//	}
//
//	public Activo removeActivo(Activo activo) {
//		getActivos().remove(activo);
//		activo.setUbicacionActivo(null);
//
//		return activo;
//	}

}