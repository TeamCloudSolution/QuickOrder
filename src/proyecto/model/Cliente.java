package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long ci;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	public Cliente() {
	}

	public long getCi() {
		return this.ci;
	}

	public void setCi(long ci) {
		this.ci = ci;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}