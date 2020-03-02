package com.inventarioActivos.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.inventarioActivos.model.entity.Activo;

/**
 * The persistent class for the inventario_historico database table.
 * 
 */
@Entity
@Table(name="inventario_historico")
public class InventarioHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_historico")
	private String idHistorico;

	@Column(name="custodio_anterior")
	private String custodioAnterior;

	@Column(name="custodio_nuevo")
	private String custodioNuevo;

	@Column(name="descripcion_anterior")
	private String descripcionAnterior;

	@Column(name="descripcion_nueva")
	private String descripcionNueva;

	@Column(name="empresa_anterior")
	private int empresaAnterior;

	@Column(name="empresa_nueva")
	private int empresaNueva;

	@Column(name="tipo_anterior")
	private int tipoAnterior;

	@Column(name="tipo_nuevo")
	private int tipoNuevo;

	@Column(name="ubicacion_anterior")
	private int ubicacionAnterior;

	@Column(name="ubicacion_nueva")
	private int ubicacionNueva;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="codigo_activo")
	private Activo activo;

	public InventarioHistorico() {
	}

	public String getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(String idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getCustodioAnterior() {
		return this.custodioAnterior;
	}

	public void setCustodioAnterior(String custodioAnterior) {
		this.custodioAnterior = custodioAnterior;
	}

	public String getCustodioNuevo() {
		return this.custodioNuevo;
	}

	public void setCustodioNuevo(String custodioNuevo) {
		this.custodioNuevo = custodioNuevo;
	}

	public String getDescripcionAnterior() {
		return this.descripcionAnterior;
	}

	public void setDescripcionAnterior(String descripcionAnterior) {
		this.descripcionAnterior = descripcionAnterior;
	}

	public String getDescripcionNueva() {
		return this.descripcionNueva;
	}

	public void setDescripcionNueva(String descripcionNueva) {
		this.descripcionNueva = descripcionNueva;
	}

	public int getEmpresaAnterior() {
		return this.empresaAnterior;
	}

	public void setEmpresaAnterior(int empresaAnterior) {
		this.empresaAnterior = empresaAnterior;
	}

	public int getEmpresaNueva() {
		return this.empresaNueva;
	}

	public void setEmpresaNueva(int empresaNueva) {
		this.empresaNueva = empresaNueva;
	}

	public int getTipoAnterior() {
		return this.tipoAnterior;
	}

	public void setTipoAnterior(int tipoAnterior) {
		this.tipoAnterior = tipoAnterior;
	}

	public int getTipoNuevo() {
		return this.tipoNuevo;
	}

	public void setTipoNuevo(int tipoNuevo) {
		this.tipoNuevo = tipoNuevo;
	}

	public int getUbicacionAnterior() {
		return this.ubicacionAnterior;
	}

	public void setUbicacionAnterior(int ubicacionAnterior) {
		this.ubicacionAnterior = ubicacionAnterior;
	}

	public int getUbicacionNueva() {
		return this.ubicacionNueva;
	}

	public void setUbicacionNueva(int ubicacionNueva) {
		this.ubicacionNueva = ubicacionNueva;
	}

	public Activo getActivo() {
		return this.activo;
	}

	public void setActivo(Activo activo) {
		this.activo = activo;
	}

}