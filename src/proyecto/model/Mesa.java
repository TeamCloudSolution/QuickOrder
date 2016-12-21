package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mesa database table.
 * 
 */
@Entity
@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
public class Mesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private long id;

	private String descripcion;

	private String numero;

	public Mesa() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}