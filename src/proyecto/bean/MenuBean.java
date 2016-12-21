package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.MasterBL;
import proyecto.model.Menu;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Menu> lstMenu;
	private Menu entidad;
	private boolean nuevo;

	@Inject
	private MasterBL bl;

	@PostConstruct
	public void init() {
		cargarListaMenu();
		nuevo();
	}

	@SuppressWarnings("unchecked")
	private void cargarListaMenu() {
		try {
			lstMenu = bl.findAll(Menu.class);
		} catch (Exception e) {
			SysMessage.error("Fallo al obtener la lista de menus.");
			e.printStackTrace();
		}
	}

	public void nuevo() {
		entidad = new Menu();
		entidad.setEstado(true);
		nuevo = true;

	}

	public void guardar() {
		if (nuevo) {
			try {
				bl.save(entidad);
				cargarListaMenu();
				nuevo();

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		} else {
			try {

				bl.update(entidad);
				cargarListaMenu();
				nuevo();

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		}
	}

	public void eliminar(long menuId) {
		System.out.println("Eliminar: " + menuId);
		try {
			Menu men = (Menu) bl.find(menuId, Menu.class);
			if (men != null) {
				men.setEstado(false);
				bl.update(men);
				cargarListaMenu();
			} else {
				SysMessage.warn("Menu no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al eliminar el menu.");
			e.printStackTrace();
		}
	}

	public void editar(long menuId) {
		try {
			entidad = (Menu) bl.find(menuId, Menu.class);
			if (entidad != null) {
				nuevo = false;
			} else {
				SysMessage.warn("Menu no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al intentar editar el menu.");
			e.printStackTrace();
		}
	}

	public List<Menu> getLstMenu() {
		return lstMenu;
	}

	public void setLstMenu(List<Menu> lstMenu) {
		this.lstMenu = lstMenu;
	}

	public Menu getEntidad() {
		return entidad;
	}

	public void setEntidad(Menu entidad) {
		this.entidad = entidad;
	}

}
