package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u where u.estado = true")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USUARIO_USUARIOID_GENERATOR", sequenceName = "SEQ_USUARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_USUARIOID_GENERATOR")
	@Column(name = "USUARIO_ID")
	private long usuarioId;

	@Column(name = "APELLIDO_MATERNO")
	private String apellidoMaterno;

	@Column(name = "APELLIDO_PATERNO")
	private String apellidoPaterno;

	private boolean estado;

	private String login;

	private String nombre;

	private String password;

	// uni-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name = "GRUPO_ID")
	private Grupo grupo;

	public Usuario() {
	}

	public long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}