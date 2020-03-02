package com.inventarioActivos.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.inventarioActivos.model.entity.Activo;

import java.sql.Timestamp;


/**
 * The persistent class for the scan_activo database table.
 * 
 */
@Entity
@Table(name="scan_activo")
public class ScanActivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_scan")
	private int idScan;

	@Column(name="fecha_scan")
	private Timestamp fechaScan;

	//bi-directional many-to-one association to Activo
	@ManyToOne
	@JoinColumn(name="codigo_activo")
	private Activo activo;

	//bi-directional many-to-one association to Lote
	@ManyToOne
	@JoinColumn(name="id_lote")
	private Lote lote;

	public ScanActivo() {
	}

	public int getIdScan() {
		return this.idScan;
	}

	public void setIdScan(int idScan) {
		this.idScan = idScan;
	}

	public Timestamp getFechaScan() {
		return this.fechaScan;
	}

	public void setFechaScan(Timestamp fechaScan) {
		this.fechaScan = fechaScan;
	}

	public Activo getActivo() {
		return this.activo;
	}

	public void setActivo(Activo activo) {
		this.activo = activo;
	}

	public Lote getLote() {
		return this.lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

}