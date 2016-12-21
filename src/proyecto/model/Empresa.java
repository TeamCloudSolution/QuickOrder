package proyecto.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="comentario_factura")
	private String comentarioFactura;

	private String direccion;

	private String nit;

	private String nombre;

	@Column(name="nro_autorizacion")
	private String nroAutorizacion;

	private int sucursal;

	public Empresa() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComentarioFactura() {
		return this.comentarioFactura;
	}

	public void setComentarioFactura(String comentarioFactura) {
		this.comentarioFactura = comentarioFactura;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNroAutorizacion() {
		return this.nroAutorizacion;
	}

	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}

	public int getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

}