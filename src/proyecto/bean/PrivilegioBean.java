package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import proyecto.business.MasterBL;
import proyecto.model.Formulario;
import proyecto.model.Menu;
import proyecto.model.Privilegio;
import proyecto.util.Parametro;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class PrivilegioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Privilegio> lstPrivilegio;
	private Privilegio entidad;
	private boolean nuevo;

	private TreeNode roles;
	private TreeNode[] nodosSeleccionados;

	@Inject
	private MasterBL bl;

	private boolean visibleDialogPrivilegio;

	@PostConstruct
	public void init() {
		cargarListaPrivilegio();
		nuevo();
	}

	@SuppressWarnings("unchecked")
	private void cargarListaPrivilegio() {
		try {
			lstPrivilegio = bl.findAll(Privilegio.class);
		} catch (Exception e) {
			SysMessage.error("Fallo al obtener la lista de privilegios.");
			e.printStackTrace();
		}
	}

	public void nuevo() {
		entidad = new Privilegio();
		entidad.setEstado(true);
		nuevo = true;
		visibleDialogPrivilegio = false;

	}

	public void cargarTree() {
		roles = new DefaultTreeNode("", null);
		roles.setExpanded(true);
		cargarMenus((DefaultTreeNode) roles);
		visibleDialogPrivilegio = true;

	}

	private void cargarMenus(DefaultTreeNode raiz) {
		List<Menu> lstMenu = getMenusPrincipales();
		if (lstMenu != null) {
			for (Menu menu : lstMenu) {
				DefaultTreeNode hijo = new DefaultTreeNode(menu, raiz);
				hijo.setSelected(false);
				hijo.setExpanded(true);
				List<Formulario> lstFormulario = getFormulariosPorMenu(menu);
				if (lstFormulario != null) {
					for (Formulario form : lstFormulario) {
						DefaultTreeNode subhijo = new DefaultTreeNode(form, hijo);
						subhijo.setSelected(true);
						subhijo.setExpanded(true);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<Menu> getMenusPrincipales() {
		try {
			return bl.findAllNamedQuery(Menu.class, "Menu.findAll.Principal", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Formulario> getFormulariosPorMenu(Menu menu) {
		Parametro p = new Parametro();
		p.put("menuId", menu.getMenuId());
		try {
			return bl.findAllNamedQuery(Formulario.class, "Formulario.findAll.menu", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void guardar() {
		if (nuevo) {
			try {
				bl.save(entidad);
				cargarListaPrivilegio();
				nuevo();

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		} else {
			try {

				bl.update(entidad);
				cargarListaPrivilegio();
				nuevo();

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		}
	}

	public void eliminar(long privilegioId) {
		System.out.println("Eliminar: " + privilegioId);
		try {
			Privilegio priv = (Privilegio) bl.find(privilegioId, Privilegio.class);
			if (priv != null) {
				priv.setEstado(false);
				bl.update(priv);
				cargarListaPrivilegio();
			} else {
				SysMessage.warn("Privilegio no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al eliminar el privilegio.");
			e.printStackTrace();
		}
	}

	public void editar(long privilegioId) {
		try {
			entidad = (Privilegio) bl.find(privilegioId, Privilegio.class);
			if (entidad != null) {
				nuevo = false;
			} else {
				SysMessage.warn("Grupo no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al intentar editar el privilegio.");
			e.printStackTrace();
		}
	}

	public List<Privilegio> getLstPrivilegio() {
		return lstPrivilegio;
	}

	public void setLstPrivilegio(List<Privilegio> lstPrivilegio) {
		this.lstPrivilegio = lstPrivilegio;
	}

	public Privilegio getEntidad() {
		return entidad;
	}

	public void setEntidad(Privilegio entidad) {
		this.entidad = entidad;
	}

	public TreeNode getRoles() {
		return roles;
	}

	public void setRoles(TreeNode roles) {
		this.roles = roles;
	}

	public TreeNode[] getNodosSeleccionados() {
		return nodosSeleccionados;
	}

	public void setNodosSeleccionados(TreeNode[] nodosSeleccionados) {
		this.nodosSeleccionados = nodosSeleccionados;
	}

	public boolean isVisibleDialogPrivilegio() {
		return visibleDialogPrivilegio;
	}

	public void setVisibleDialogPrivilegio(boolean visibleDialogPrivilegio) {
		this.visibleDialogPrivilegio = visibleDialogPrivilegio;
	}

}
