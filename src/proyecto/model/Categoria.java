package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CATEGORIA_ID_GENERATOR", sequenceName = "CATEGORIA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_ID_GENERATOR")
	private long id;

	private String descripcion;

	private String nombre;

	public Categoria() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}