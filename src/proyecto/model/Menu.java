package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the MENU database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m where m.estado = true"), @NamedQuery(name = "Menu.findAll.Principal", query = "SELECT m FROM Menu m where m.estado = true and m.menu is null ") })
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "MENU_MENUID_GENERATOR", sequenceName = "SEQ_MENU", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_MENUID_GENERATOR")
	@Column(name = "MENU_ID")
	private long menuId;

	private String descripcion;

	private boolean estado;

	private String nombre;

	// uni-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name = "MENU_ID_PADRE")
	private Menu menu;

	public Menu() {
	}

	public long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
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