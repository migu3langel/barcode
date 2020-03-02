package com.inventarioActivos.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the lote database table.
 * 
 */
@Entity

public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_lote")
	private int idLote;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to ScanActivo
	@OneToMany(mappedBy="lote")
	private Set<ScanActivo> scanActivos;

	public Lote() {
	}

	public int getIdLote() {
		return this.idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Set<ScanActivo> getScanActivos() {
		return this.scanActivos;
	}

	public void setScanActivos(Set<ScanActivo> scanActivos) {
		this.scanActivos = scanActivos;
	}

	public ScanActivo addScanActivo(ScanActivo scanActivo) {
		getScanActivos().add(scanActivo);
		scanActivo.setLote(this);

		return scanActivo;
	}

	public ScanActivo removeScanActivo(ScanActivo scanActivo) {
		getScanActivos().remove(scanActivo);
		scanActivo.setLote(null);

		return scanActivo;
	}

}