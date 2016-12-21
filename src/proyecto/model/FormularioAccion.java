package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORMULARIO_ACCION database table.
 * 
 */
@Entity
@Table(name="FORMULARIO_ACCION")
@NamedQuery(name="FormularioAccion.findAll", query="SELECT f FROM FormularioAccion f")
public class FormularioAccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FORMULARIO_ACCION_FORMULARIOACCIONID_GENERATOR", sequenceName="SEQ_FORMULARIO_ACCION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORMULARIO_ACCION_FORMULARIOACCIONID_GENERATOR")
	@Column(name="FORMULARIO_ACCION_ID")
	private long formularioAccionId;

	private boolean estado;

	//uni-directional many-to-one association to Accion
	@ManyToOne
	@JoinColumn(name="ACCION_ID")
	private Accion accion;

	//uni-directional many-to-one association to Formulario
	@ManyToOne
	@JoinColumn(name="FORMULARIO_ID")
	private Formulario formulario;

	public FormularioAccion() {
	}

	public long getFormularioAccionId() {
		return this.formularioAccionId;
	}

	public void setFormularioAccionId(long formularioAccionId) {
		this.formularioAccionId = formularioAccionId;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
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

}