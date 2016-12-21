package proyecto.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.GestionPedidoBL;
import proyecto.model.Pedido;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class GestionPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestionPedidoBL bl;

	private List<Pedido> lst;
	private Pedido selected;
	private Pedido entidad;
	private boolean nuevo;
	private boolean visibleNuevoEditar;

	private List<Object[]> lstProducto;

	@PostConstruct
	public void init() {
		cargarLst();
		nuevo();
	}

	public void nuevo() {
		entidad = new Pedido();
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

	private void cargarProductos(long pedidoId) {
		lstProducto = new ArrayList<Object[]>();
		try {
			lstProducto = bl.findAllProductos(pedidoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar() {
		// Categoria categoria;
		// try {
		// categoria = (Categoria) blCategoria.find(idCategoria,
		// Categoria.class);
		// entidad.setCategoria(categoria);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// categoria = null;
		// }
		//
		// if (entidad != null) {
		//
		// String validacion = bl.validar(entidad, nuevo);
		// if (validacion.trim().isEmpty()) {
		// if (nuevo) {
		// try {
		// bl.save(entidad);
		// SysMessage.info("Guardado correctamente.");
		// nuevo();
		// cargarLst();
		// visibleNuevoEditar = true;
		// } catch (Exception e) {
		// SysMessage.error("Fallo al guardar.");
		// }
		// } else {
		// try {
		// bl.update(entidad);
		// SysMessage.info("Guardado correctamente.");
		// nuevo();
		// cargarLst();
		// visibleNuevoEditar = false;
		// } catch (Exception e) {
		// SysMessage.error("Fallo al guardar.");
		// }
		// }
		// } else {
		// SysMessage.warn("Se encontro observaciones al intentar guardar: " +
		// validacion);
		// }
		// }
	}

	public void atendido() {
		try {
			entidad.setEstado("ATENDIDO");
			bl.update(entidad);
			setVisibleNuevoEditar(false);
			SysMessage.info("Pedido atendido correctamente");
			cargarLst();
		} catch (Exception e) {
			e.printStackTrace();
			SysMessage.error("Fallo al actualizar el pedido");
		}
	}

	public void editar() {
		if (selected != null) {
			entidad = selected;
			cargarProductos(entidad.getId());
			nuevo = false;
			visibleNuevoEditar = true;
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void editar(Pedido obj) {
		selected = obj;
		editar();
	}

	public List<Pedido> getLst() {
		return lst;
	}

	public void setLst(List<Pedido> lst) {
		this.lst = lst;
	}

	public Pedido getSelected() {
		return selected;
	}

	public void setSelected(Pedido selected) {
		this.selected = selected;
	}

	public Pedido getEntidad() {
		return entidad;
	}

	public void setEntidad(Pedido entidad) {
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

	public List<Object[]> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Object[]> lstProducto) {
		this.lstProducto = lstProducto;
	}

}
