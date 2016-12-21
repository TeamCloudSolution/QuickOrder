package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.GestionCategoriaBL;
import proyecto.business.GestionProductoBL;
import proyecto.model.Categoria;
import proyecto.model.Producto;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class GestionProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestionProductoBL bl;

	@Inject
	private GestionCategoriaBL blCategoria;

	private List<Producto> lst;
	private Producto selected;
	private Producto entidad;
	private boolean nuevo;
	private boolean visibleNuevoEditar;

	private List<Categoria> lstCategoria;
	private long idCategoria;

	@PostConstruct
	public void init() {
		cargarLst();
		nuevo();
		cargarLstCategoria();
	}

	public void nuevo() {
		entidad = new Producto();
		nuevo = true;
		visibleNuevoEditar = false;
		idCategoria = 0;
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

	@SuppressWarnings("unchecked")
	private void cargarLstCategoria() {
		try {
			lstCategoria = blCategoria.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar() {
		Categoria categoria;
		try {
			categoria = (Categoria) blCategoria.find(idCategoria, Categoria.class);
			entidad.setCategoria(categoria);
		} catch (Exception e1) {
			e1.printStackTrace();
			categoria = null;
		}

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
			idCategoria = entidad.getCategoria().getId();
			nuevo = false;
			visibleNuevoEditar = true;
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void editar(Producto obj) {
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

	public void eliminar(Producto obj) {
		selected = obj;
		eliminar();
	}

	public List<Producto> getLst() {
		return lst;
	}

	public void setLst(List<Producto> lst) {
		this.lst = lst;
	}

	public Producto getSelected() {
		return selected;
	}

	public void setSelected(Producto selected) {
		this.selected = selected;
	}

	public Producto getEntidad() {
		return entidad;
	}

	public void setEntidad(Producto entidad) {
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

	public List<Categoria> getLstCategoria() {
		return lstCategoria;
	}

	public void setLstCategoria(List<Categoria> lstCategoria) {
		this.lstCategoria = lstCategoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

}
