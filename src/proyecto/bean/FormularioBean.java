package proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.MasterBL;
import proyecto.model.Accion;
import proyecto.model.Formulario;
import proyecto.model.FormularioAccion;
import proyecto.model.Menu;
import proyecto.util.Parametro;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class FormularioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Formulario> lstFormulario;
	private Formulario entidad;
	private boolean nuevo;

	private List<Accion> lstAccion;
	private List<String> lstAccionSelected;
	private List<Menu> lstMenu;
	private long menuId;

	@Inject
	private MasterBL bl;

	@PostConstruct
	public void init() {
		cargarListaFormulario();
		cargarLstAccion();
		cargarLstMenu();
		nuevo();
		lstAccionSelected = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	private void cargarListaFormulario() {
		try {
			lstFormulario = bl.findAll(Formulario.class);
		} catch (Exception e) {
			SysMessage.error("Fallo al obtener la lista de formularios.");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarLstMenu() {
		try {
			lstMenu = bl.findAll(Menu.class);
		} catch (Exception e) {
			System.out.println("Fallo al obtener la lista de menus.");
			e.printStackTrace();
			lstMenu = new ArrayList<Menu>();
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarLstAccion() {
		try {
			lstAccion = bl.findAll(Accion.class);
		} catch (Exception e) {
			System.out.println("Fallo al obtener la lista de acciones.");
			e.printStackTrace();
			lstAccion = new ArrayList<Accion>();
		}
	}

	public void nuevo() {
		entidad = new Formulario();
		entidad.setEstado(true);
		nuevo = true;

	}

	public void guardar() {

		if (nuevo) {
			try {

				Menu menu = (Menu) bl.find(menuId, Menu.class);
				if (menu != null) {
					entidad.setMenu(menu);

					bl.save(entidad);

					for (String l : lstAccionSelected) {
						FormularioAccion fa = new FormularioAccion();
						fa.setEstado(true);
						fa.setFormulario(entidad);
						Accion accion = (Accion) bl.find(Long.valueOf(l), Accion.class);
						if (accion != null) {
							fa.setAccion(accion);
							bl.save(fa);
						} else {
							System.out.println("[guardar] Fallo al obtener la accion con id: " + l);
						}
					}

					cargarListaFormulario();
					nuevo();
				} else {
					SysMessage.error("Fallo al buscar menu.");
				}

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		} else {
			try {
				Menu menu = (Menu) bl.find(menuId, Menu.class);
				if (menu != null) {
					entidad.setMenu(menu);

					String sql = "update FORMULARIO_ACCION set estado = 0 where FORMULARIO_ID = :formularioId and estado = 1";
					Parametro p = new Parametro();
					p.put("formularioId", entidad.getFormularioId());

					bl.update(entidad);
					bl.executeUpdateNativeQuery(sql, p);
					for (String l : lstAccionSelected) {
						FormularioAccion fa = new FormularioAccion();
						fa.setEstado(true);
						fa.setFormulario(entidad);
						Accion accion = (Accion) bl.find(Long.valueOf(l), Accion.class);
						if (accion != null) {
							fa.setAccion(accion);
							bl.save(fa);
						} else {
							System.out.println("[guardar] Fallo al obtener la accion con id: " + l);
						}
					}

					cargarListaFormulario();
					nuevo();
				} else {
					SysMessage.error("Fallo al buscar menu.");
				}

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		}
	}

	public void eliminar(long formularioId) {
		System.out.println("Eliminar: " + formularioId);
		try {
			Formulario form = (Formulario) bl.find(formularioId, Formulario.class);
			if (form != null) {
				form.setEstado(false);
				bl.update(form);
				cargarListaFormulario();
			} else {
				SysMessage.warn("Formulario no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al eliminar el formulario.");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void editar(long formularioId) {
		try {
			entidad = (Formulario) bl.find(formularioId, Formulario.class);
			if (entidad != null) {
				nuevo = false;

				menuId = entidad.getMenu().getMenuId();

				String sql = "select a.* from accion a inner join FORMULARIO_ACCION fa on a.ACCION_ID = fa.ACCION_ID where a.ESTADO = 1 and FA.ESTADO = 1 and fa.FORMULARIO_ID = :formularioId";
				Parametro p = new Parametro();
				p.put("formularioId", entidad.getFormularioId());
				List<Accion> lAccion = bl.findAllNativeQuery(Accion.class, sql, p);
				lstAccionSelected.clear();
				for (Accion a : lAccion) {
					lstAccionSelected.add(String.valueOf(a.getAccionId()));
				}

			} else {
				SysMessage.warn("Formulario no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al intentar editar el formulario.");
			e.printStackTrace();
		}
	}

	public List<Formulario> getLstFormulario() {
		return lstFormulario;
	}

	public void setLstFormulario(List<Formulario> lstFormulario) {
		this.lstFormulario = lstFormulario;
	}

	public Formulario getEntidad() {
		return entidad;
	}

	public void setEntidad(Formulario entidad) {
		this.entidad = entidad;
	}

	public List<Accion> getLstAccion() {
		return lstAccion;
	}

	public void setLstAccion(List<Accion> lstAccion) {
		this.lstAccion = lstAccion;
	}

	public List<String> getLstAccionSelected() {
		return lstAccionSelected;
	}

	public void setLstAccionSelected(List<String> lstAccionSelected) {
		this.lstAccionSelected = lstAccionSelected;
	}

	public List<Menu> getLstMenu() {
		return lstMenu;
	}

	public void setLstMenu(List<Menu> lstMenu) {
		this.lstMenu = lstMenu;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

}
