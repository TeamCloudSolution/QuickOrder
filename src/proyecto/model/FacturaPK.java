package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the factura database table.
 * 
 */
@Embeddable
public class FacturaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="nro_factura")
	private String nroFactura;

	@Column(name="cod_control")
	private String codControl;

	public FacturaPK() {
	}
	public String getNroFactura() {
		return this.nroFactura;
	}
	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
	public String getCodControl() {
		return this.codControl;
	}
	public void setCodControl(String codControl) {
		this.codControl = codControl;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FacturaPK)) {
			return false;
		}
		FacturaPK castOther = (FacturaPK)other;
		return 
			this.nroFactura.equals(castOther.nroFactura)
			&& this.codControl.equals(castOther.codControl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nroFactura.hashCode();
		hash = hash * prime + this.codControl.hashCode();
		
		return hash;
	}
}