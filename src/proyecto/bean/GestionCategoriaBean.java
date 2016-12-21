package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.GestionCategoriaBL;
import proyecto.model.Categoria;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class GestionCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestionCategoriaBL bl;

	private List<Categoria> lst;
	private Categoria selected;
	private Categoria entidad;
	private boolean nuevo;
	private boolean visibleNuevoEditar;

	@PostConstruct
	public void init() {
		cargarLst();
		nuevo();
	}

	public void nuevo() {
		entidad = new Categoria();
		nuevo = true;
		visibleNuevoEditar = false;
	}

	public void nuevaEntidad() {
		nuevo();
		visibleNuevoEditar = true;
	}

	@SuppressWarnings("unchecked")
	private void cargarLst() {
		try {
			lst = bl.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar() {
		if (entidad != null) {

			String validacion = bl.validar(entidad, nuevo);
			if (validacion.trim().isEmpty()) {
				if (nuevo) {
					try {
						bl.save(entidad);
						SysMessage.info("Guardado correctamente.");
						nuevo();
						cargarLst();
						visibleNuevoEditar = true;
					} catch (Exception e) {
						SysMessage.error("Fallo al guardar.");
					}
				} else {
					try {
						bl.update(entidad);
						SysMessage.info("Guardado correctamente.");
						nuevo();
						cargarLst();
						visibleNuevoEditar = false;
					} catch (Exception e) {
						SysMessage.error("Fallo al guardar.");
					}
				}
			} else {
				SysMessage.warn("Se encontro observaciones al intentar guardar: " + validacion);
			}
		}
	}

	public void editar() {
		if (selected != null) {
			entidad = selected;
			nuevo = false;
			visibleNuevoEditar = true;
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void editar(Categoria obj) {
		selected = obj;
		editar();
	}

	public void eliminar() {
		if (selected != null) {
			try {
				bl.remove(selected);
				SysMessage.info("Eliminado correctamente.");
				cargarLst();
			} catch (Exception e) {
				SysMessage.info("Fallo al eliminar.");
			}
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void eliminar(Categoria obj) {
		selected = obj;
		eliminar();
	}

	public List<Categoria> getLst() {
		return lst;
	}

	public void setLst(List<Categoria> lst) {
		this.lst = lst;
	}

	public Categoria getSelected() {
		return selected;
	}

	public void setSelected(Categoria selected) {
		this.selected = selected;
	}

	public Categoria getEntidad() {
		return entidad;
	}

	public void setEntidad(Categoria entidad) {
		this.entidad = entidad;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isVisibleNuevoEditar() {
		return visibleNuevoEditar;
	}

	public void setVisibleNuevoEditar(boolean visibleNuevoEditar) {
		this.visibleNuevoEditar = visibleNuevoEditar;
	}

}
