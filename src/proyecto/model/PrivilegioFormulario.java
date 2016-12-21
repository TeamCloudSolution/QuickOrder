package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PRIVILEGIO_FORMULARIO database table.
 * 
 */
@Entity
@Table(name = "PRIVILEGIO_FORMULARIO")
@NamedQuery(name = "PrivilegioFormulario.findAll", query = "SELECT p FROM PrivilegioFormulario p where p.estado = true")
public class PrivilegioFormulario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRIVILEGIO_FORMULARIO_PRIVILEGIOFORMULARIOID_GENERATOR", sequenceName = "SEQ_PRIVILEGIO_FORMULARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIVILEGIO_FORMULARIO_PRIVILEGIOFORMULARIOID_GENERATOR")
	@Column(name = "PRIVILEGIO_FORMULARIO_ID")
	private long privilegioFormularioId;

	private Boolean estado;

	// uni-directional many-to-one association to Accion
	@ManyToOne
	@JoinColumn(name = "ACCION_ID")
	private Accion accion;

	// uni-directional many-to-one association to Formulario
	@ManyToOne
	@JoinColumn(name = "FORMULARIO_ID")
	private Formulario formulario;

	// uni-directional many-to-one association to Privilegio
	@ManyToOne
	@JoinColumn(name = "PRIVILEGIO_ID")
	private Privilegio privilegio;

	public PrivilegioFormulario() {
	}

	public long getPrivilegioFormularioId() {
		return this.privilegioFormularioId;
	}

	public void setPrivilegioFormularioId(long privilegioFormularioId) {
		this.privilegioFormularioId = privilegioFormularioId;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
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

	public Privilegio getPrivilegio() {
		return this.privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

}