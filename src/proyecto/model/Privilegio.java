package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PRIVILEGIO database table.
 * 
 */
@Entity
@NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p where p.estado = true")
public class Privilegio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRIVILEGIO_PRIVILEGIOID_GENERATOR", sequenceName = "SEQ_PRIVILEGIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVILEGIO_PRIVILEGIOID_GENERATOR")
	@Column(name = "PRIVILEGIO_ID")
	private long privilegioId;

	private String descripcion;

	private boolean estado;

	private String nombre;

	public Privilegio() {
	}

	public long getPrivilegioId() {
		return this.privilegioId;
	}

	public void setPrivilegioId(long privilegioId) {
		this.privilegioId = privilegioId;
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