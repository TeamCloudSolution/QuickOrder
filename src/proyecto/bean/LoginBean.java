package proyecto.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import proyecto.business.MasterBL;
import proyecto.model.Formulario;
import proyecto.model.Menu;
import proyecto.util.Parametro;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private boolean logeado = false;

	@Inject
	private MasterBL masterBl;

	private MenuModel model;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		// if (userName != null && userName.equals("admin") && password != null
		// && password.equals("admin")) {
		if (validarLogin()) {
			logeado = true;
			cargarMenus();
		} else {
			logeado = false;
		}
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado)
			context.addCallbackParam("view", "index.xhtml");
	}

	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	}

	private void cargarMenus() {
		model = new DefaultMenuModel();

		List<Menu> lstMenu = getMenusPrincipales();
		if (lstMenu != null) {
			for (Menu menu : lstMenu) {
				DefaultSubMenu submenu = new DefaultSubMenu();
				submenu.setLabel(menu.getNombre());
				model.addElement(submenu);
				List<Formulario> lstFormulario = getFormulariosPorMenu(menu);
				if (lstFormulario != null) {
					for (Formulario form : lstFormulario) {
						DefaultMenuItem item = new DefaultMenuItem();
						item.setValue(form.getNombre());
						item.setUrl(form.getUrl());
						// submenu.getChildren().add(item);
						submenu.addElement(item);
					}
				}
				List<Menu> lstSubMenu = getSubmenus(menu);
				if (lstSubMenu != null) {
					for (Menu menu1 : lstSubMenu) {
						DefaultSubMenu submenu1 = new DefaultSubMenu();
						submenu1.setLabel(menu1.getNombre());
						submenu.addElement(submenu1);
						List<Formulario> lstFormulario1 = getFormulariosPorMenu(menu1);
						if (lstFormulario1 != null) {
							for (Formulario form : lstFormulario1) {
								DefaultMenuItem item = new DefaultMenuItem();
								item.setValue(form.getNombre());
								item.setUrl(form.getUrl());
								// submenu1.getChildren().add(item);
								submenu.addElement(item);
							}
						}
					}
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private List<Menu> getMenusPrincipales() {
		try {
			return masterBl.findAllNamedQuery(Menu.class, "Menu.findAll.Principal", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Formulario> getFormulariosPorMenu(Menu menu) {
		// String sql = Messages.getString("LoginBean.getFormulariosPorMenu");
		String sql = "select distinct f.* from formulario f, v_usuario_formulario v where f.estado = true and f.FORMULARIO_ID = v.formulario_id and f.menu_id = :menuId and v.LOGIN = :login order by f.nombre";
		Parametro p = new Parametro();
		p.put("menuId", menu.getMenuId());
		p.put("login", userName); //$NON-NLS-1$
		try {
			return masterBl.findAllNativeQuery(Formulario.class, sql, p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<Menu> getSubmenus(Menu menu) {
		String sql = "select * from menu m where m.estado = true and m.menu_id_padre = :menuPadre order by m.nombre";
		Parametro p = new Parametro();
		p.put("menuPadre", menu.getMenuId());
		try {
			return masterBl.findAllNativeQuery(Menu.class, sql, p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private boolean validarLogin() {
		if (this.userName != null && this.password != null) {
			String sql = "select count(0) from usuario where estado = true and trim(login) = trim( :userName ) and trim(password) = trim( :password )";
			Parametro p = new Parametro();
			p.put("userName", userName);
			p.put("password", password);
			try {
				List<BigInteger> l = masterBl.findAllNativeQuery(sql, p);
				return l != null && l.size() > 0 ? l.get(0).intValue() > 0 : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}