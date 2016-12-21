package proyecto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.MasterBL;
import proyecto.model.Grupo;
import proyecto.model.Usuario;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> lstUsuario;
	private List<Grupo> lstGrupo;
	private long grupoId;
	private Usuario entidad;
	private boolean nuevo;

	@Inject
	private MasterBL bl;

	@PostConstruct
	public void init() {
		cargarListaUsuario();
		cargarListaGrupo();
		nuevo();
	}

	@SuppressWarnings("unchecked")
	private void cargarListaUsuario() {
		try {
			lstUsuario = bl.findAll(Usuario.class);
		} catch (Exception e) {
			SysMessage.error("Fallo al obtener la lista de usuarios.");
			e.printStackTrace();
		}
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

	public void nuevo() {
		entidad = new Usuario();
		entidad.setEstado(true);
		nuevo = true;
		grupoId = 0;
	}

	public void guardar() {
		if (nuevo) {
			try {
				Grupo grupo = (Grupo) bl.find(grupoId, Grupo.class);
				if (grupo != null) {
					entidad.setGrupo(grupo);

					bl.save(entidad);
					cargarListaUsuario();
					nuevo();
				} else {
					SysMessage.error("No se encontro el grupo seleccionado.");
				}

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		} else {
			try {
				Grupo grupo = (Grupo) bl.find(grupoId, Grupo.class);
				if (grupo != null) {
					entidad.setGrupo(grupo);

					bl.update(entidad);
					cargarListaUsuario();
					nuevo();
				} else {
					SysMessage.error("No se encontro el grupo seleccionado.");
				}

			} catch (Exception e) {
				SysMessage.error("Fallo al guardar.");
				e.printStackTrace();
			}
		}
	}

	public void eliminar(long usuarioId) {
		try {
			Usuario usu = (Usuario) bl.find(usuarioId, Usuario.class);
			if (usu != null) {
				usu.setEstado(false);
				bl.update(usu);
				cargarListaUsuario();
			} else {
				SysMessage.warn("Usuario no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al eliminar el usuario.");
			e.printStackTrace();
		}
	}

	public void editar(long usuarioId) {
		try {
			entidad = (Usuario) bl.find(usuarioId, Usuario.class);
			if (entidad != null) {
				grupoId = entidad.getGrupo().getGrupoId();
				nuevo = false;
			} else {
				SysMessage.warn("Usuario no encontrado.");
			}
		} catch (Exception e) {
			SysMessage.error("Fallo al intentar editar el usuario.");
			e.printStackTrace();
		}
	}

	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public List<Grupo> getLstGrupo() {
		return lstGrupo;
	}

	public void setLstGrupo(List<Grupo> lstGrupo) {
		this.lstGrupo = lstGrupo;
	}

	public long getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(long grupoId) {
		this.grupoId = grupoId;
	}

	public Usuario getEntidad() {
		return entidad;
	}

	public void setEntidad(Usuario entidad) {
		this.entidad = entidad;
	}

}
