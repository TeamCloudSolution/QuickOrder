package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.MasterBL;
import proyecto.model.Accion;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class AccionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Accion> lstAccion;
	private Accion entidad;
	private boolean nuevo;

	@Inject
	private MasterBL bl;

	@PostConstruct
	public void init() {
		cargarListaAccion();
		nuevo();
	}

	@SuppressWarnings("unchecked")
	private void cargarListaAccion() {
		try {
			lstAccion = bl.findAll(Accion.class);
		} catch (Exception e) {
			SysMessage.error("Fallo al obtener la lista de acciones.");
			e.printStackTrace();
		}
	}

	public void nuevo() {
		// if (Control.validar()) {
		entidad = new Accion();
		entidad.setEstado(true);
		nuevo = true;
		// }

	}

	public void guardar() {
		if (nuevo) {
			try {
				bl.save(entidad);
				cargarListaAccion();
				nuevo();

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		} else {
			try {

				bl.update(entidad);
				cargarListaAccion();
				nuevo();

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		}
	}

	public void eliminar(long accionId) {
		System.out.println("Eliminar: " + accionId);
		try {
			Accion acc = (Accion) bl.find(accionId, Accion.class);
			if (acc != null) {
				acc.setEstado(false);
				bl.update(acc);
				cargarListaAccion();
			} else {
				SysMessage.warn("Accion no encontrada.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al eliminar la accion.");
			e.printStackTrace();
		}
	}

	public void editar(long accionId) {
		try {
			entidad = (Accion) bl.find(accionId, Accion.class);
			if (entidad != null) {
				nuevo = false;
			} else {
				SysMessage.warn("Accion no encontrada.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al intentar editar la accion.");
			e.printStackTrace();
		}
	}

	public List<Accion> getLstAccion() {
		return lstAccion;
	}

	public void setLstAccion(List<Accion> lstAccion) {
		this.lstAccion = lstAccion;
	}

	public Accion getEntidad() {
		return entidad;
	}

	public void setEntidad(Accion entidad) {
		this.entidad = entidad;
	}

}
