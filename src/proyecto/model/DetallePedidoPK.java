package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalle_pedido database table.
 * 
 */
@Embeddable
public class DetallePedidoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pedido", insertable=false, updatable=false)
	private Long idPedido;

	@Column(name="id_producto", insertable=false, updatable=false)
	private Long idProducto;

	public DetallePedidoPK() {
	}
	public Long getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public Long getIdProducto() {
		return this.idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallePedidoPK)) {
			return false;
		}
		DetallePedidoPK castOther = (DetallePedidoPK)other;
		return 
			this.idPedido.equals(castOther.idPedido)
			&& this.idProducto.equals(castOther.idProducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPedido.hashCode();
		hash = hash * prime + this.idProducto.hashCode();
		
		return hash;
	}
}