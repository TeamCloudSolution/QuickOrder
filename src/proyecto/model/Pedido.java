package proyecto.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String estado;

	@Column(name = "fecha_hora")
	private Timestamp fechaHora;

	// uni-directional many-to-one association to Mesa
	@ManyToOne
	@JoinColumn(name = "ci_cliente")
	private Cliente cliente;

	// uni-directional many-to-one association to Mesa
	@ManyToOne
	@JoinColumn(name = "id_mesa")
	private Mesa mesa;

	public Pedido() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Mesa getMesa() {
		return this.mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}