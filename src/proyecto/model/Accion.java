package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ACCION database table.
 * 
 */
@Entity
@NamedQuery(name = "Accion.findAll", query = "SELECT a FROM Accion a where a.estado = true")
public class Accion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ACCION_ACCIONID_GENERATOR", sequenceName = "SEQ_ACCION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_ACCIONID_GENERATOR")
	@Column(name = "ACCION_ID")
	private long accionId;

	private String descripcion;

	private boolean estado;

	private String nombre;

	public Accion() {
	}

	public long getAccionId() {
		return this.accionId;
	}

	public void setAccionId(long accionId) {
		this.accionId = accionId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}