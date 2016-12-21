package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the detalle_pedido database table.
 * 
 */
@Entity
@Table(name = "detalle_pedido")
@NamedQuery(name = "DetallePedido.findAll", query = "SELECT d FROM DetallePedido d")
public class DetallePedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetallePedidoPK id;

	private int cantidad;

	// //uni-directional many-to-one association to Pedido
	// @ManyToOne
	// @JoinColumn(name="id_pedido")
	// private Pedido pedido;
	//
	// //uni-directional many-to-one association to Producto
	// @ManyToOne
	// @JoinColumn(name="id_producto")
	// private Producto producto;

	public DetallePedido() {
	}

	public DetallePedidoPK getId() {
		return this.id;
	}

	public void setId(DetallePedidoPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// public Pedido getPedido() {
	// return this.pedido;
	// }
	//
	// public void setPedido(Pedido pedido) {
	// this.pedido = pedido;
	// }
	//
	// public Producto getProducto() {
	// return this.producto;
	// }
	//
	// public void setProducto(Producto producto) {
	// this.producto = producto;
	// }

}