package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FacturaPK id;

	@Column(name = "total_pagar")
	private double totalPagar;

	// uni-directional many-to-one association to Empleado
	// @ManyToOne
	// @JoinColumn(name="ci_empleado")
	// private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	// uni-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	// uni-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	public Factura() {
	}

	public FacturaPK getId() {
		return this.id;
	}

	public void setId(FacturaPK id) {
		this.id = id;
	}

	public double getTotalPagar() {
		return this.totalPagar;
	}

	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}