package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.MasterBL;
import proyecto.model.Grupo;
import proyecto.model.Privilegio;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class GrupoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Grupo> lstGrupo;
	private List<Privilegio> lstPrivilegio;
	private long privilegioId;
	private Grupo entidad;
	private boolean nuevo;

	@Inject
	private MasterBL bl;

	@PostConstruct
	public void init() {
		cargarListaGrupo();
		cargarListaPrivilegio();
		nuevo();
	}

	@SuppressWarnings("unchecked")
	private void cargarListaGrupo() {
		try {
			lstGrupo = bl.findAll(Grupo.class);
		} catch (Exception e) {
			SysMessage.error("Fallo al obtener la lista de grupos.");
			e.printStackTrace();
		}
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
		entidad = new Grupo();
		entidad.setEstado(true);
		nuevo = true;
		privilegioId = 0;
	}

	public void guardar() {
		if (nuevo) {
			try {
				Privilegio privil = (Privilegio) bl.find(privilegioId, Privilegio.class);
				if (privil != null) {
					entidad.setPrivilegio(privil);

					bl.save(entidad);
					cargarListaGrupo();
					nuevo();
				} else {
					SysMessage.error("No se encontro el privilegio seleccionado.");
				}

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		} else {
			try {
				Privilegio privil = (Privilegio) bl.find(privilegioId, Privilegio.class);
				if (privil != null) {
					entidad.setPrivilegio(privil);

					bl.update(entidad);
					cargarListaGrupo();
					nuevo();
				} else {
					SysMessage.error("No se encontro el privilegio seleccionado.");
				}

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		}
	}

	public void eliminar(long grupoId) {
		System.out.println("Eliminar: " + grupoId);
		try {
			Grupo gru = (Grupo) bl.find(grupoId, Grupo.class);
			if (gru != null) {
				gru.setEstado(false);
				bl.update(gru);
				cargarListaGrupo();
			} else {
				SysMessage.warn("Grupo no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al eliminar el grupo.");
			e.printStackTrace();
		}
	}

	public void editar(long grupoId) {
		try {
			entidad = (Grupo) bl.find(grupoId, Grupo.class);
			if (entidad != null) {
				privilegioId = entidad.getPrivilegio().getPrivilegioId();
				nuevo = false;
			} else {
				SysMessage.warn("Grupo no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al intentar editar el grupo.");
			e.printStackTrace();
		}
	}

	public List<Grupo> getLstGrupo() {
		return lstGrupo;
	}

	public void setLstGrupo(List<Grupo> lstGrupo) {
		this.lstGrupo = lstGrupo;
	}

	public List<Privilegio> getLstPrivilegio() {
		return lstPrivilegio;
	}

	public void setLstPrivilegio(List<Privilegio> lstPrivilegio) {
		this.lstPrivilegio = lstPrivilegio;
	}

	public long getPrivilegioId() {
		return privilegioId;
	}

	public void setPrivilegioId(long privilegioId) {
		this.privilegioId = privilegioId;
	}

	public Grupo getEntidad() {
		return entidad;
	}

	public void setEntidad(Grupo entidad) {
		this.entidad = entidad;
	}

}
