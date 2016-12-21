package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the GRUPO database table.
 * 
 */
@Entity
@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g where g.estado = true")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GRUPO_GRUPOID_GENERATOR", sequenceName = "SEQ_GRUPO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPO_GRUPOID_GENERATOR")
	@Column(name = "GRUPO_ID")
	private long grupoId;

	private String descripcion;

	private boolean estado;

	private String nombre;

	// uni-directional many-to-one association to Privilegio
	@ManyToOne
	@JoinColumn(name = "PRIVILEGIO_ID")
	private Privilegio privilegio;

	public Grupo() {
	}

	public long getGrupoId() {
		return this.grupoId;
	}

	public void setGrupoId(long grupoId) {
		this.grupoId = grupoId;
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

	public Privilegio getPrivilegio() {
		return this.privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

}