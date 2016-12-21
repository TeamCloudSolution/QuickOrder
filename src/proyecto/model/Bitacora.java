package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the BITACORA database table.
 * 
 */
@Entity
@NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b")
public class Bitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BITACORA_BITACORAID_GENERATOR", sequenceName = "SEQ_BITACORA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BITACORA_BITACORAID_GENERATOR")
	@Column(name = "BITACORA_ID")
	private long bitacoraId;

	private String descripcion;

	@Column(name = "FECHA_HORA")
	private Timestamp fechaHora;

	private String ip;

	// uni-directional many-to-one association to Accion
	@ManyToOne
	@JoinColumn(name = "ACCION_ID")
	private Accion accion;

	// uni-directional many-to-one association to Formulario
	@ManyToOne
	@JoinColumn(name = "FORMULARIO_ID")
	private Formulario formulario;

	// uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	public Bitacora() {
	}

	public long getBitacoraId() {
		return this.bitacoraId;
	}

	public void setBitacoraId(long bitacoraId) {
		this.bitacoraId = bitacoraId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Accion getAccion() {
		return this.accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public Formulario getFormulario() {
		return this.formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}