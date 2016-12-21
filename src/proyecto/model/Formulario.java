package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the FORMULARIO database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Formulario.findAll", query = "SELECT f FROM Formulario f where f.estado = true"), @NamedQuery(name = "Formulario.findAll.menu", query = "SELECT f FROM Formulario f where f.estado = true and f.menu.menuId = :menuId") })
public class Formulario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FORMULARIO_FORMULARIOID_GENERATOR", sequenceName = "SEQ_FORMULARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMULARIO_FORMULARIOID_GENERATOR")
	@Column(name = "FORMULARIO_ID")
	private long formularioId;

	private String descripcion;

	private boolean estado;

	private String nombre;

	private String url;

	// uni-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name = "MENU_ID")
	private Menu menu;

	public Formulario() {
	}

	public long getFormularioId() {
		return this.formularioId;
	}

	public void setFormularioId(long formularioId) {
		this.formularioId = formularioId;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return nombre;
	}

}