package proyecto.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import proyecto.business.GestionFacturaBL;
import proyecto.model.Empresa;
import proyecto.model.Pedido;
import proyecto.util.SysMessage;

@ManagedBean
@ViewScoped
public class GestionFacturaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestionFacturaBL bl;

	private List<Pedido> lst;
	private Pedido selected;
	private Pedido entidad;
	private boolean nuevo;
	private boolean visibleNuevoEditar;

	private List<Object[]> lstProducto;

	@PostConstruct
	public void init() {
		cargarLst();
		nuevo();
	}

	public void nuevo() {
		entidad = null;
		nuevo = true;
		visibleNuevoEditar = false;
	}

	public void nuevaEntidad() {
		nuevo();
		visibleNuevoEditar = true;
	}

	@SuppressWarnings("unchecked")
	private void cargarLst() {
		try {
			lst = bl.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarProductos(long pedidoId) {
		lstProducto = new ArrayList<Object[]>();
		try {
			lstProducto = bl.findAllProductos(pedidoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guardar() {
		// Categoria categoria;
		// try {
		// categoria = (Categoria) blCategoria.find(idCategoria,
		// Categoria.class);
		// entidad.setCategoria(categoria);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// categoria = null;
		// }
		//
		// if (entidad != null) {
		//
		// String validacion = bl.validar(entidad, nuevo);
		// if (validacion.trim().isEmpty()) {
		// if (nuevo) {
		// try {
		// bl.save(entidad);
		// SysMessage.info("Guardado correctamente.");
		// nuevo();
		// cargarLst();
		// visibleNuevoEditar = true;
		// } catch (Exception e) {
		// SysMessage.error("Fallo al guardar.");
		// }
		// } else {
		// try {
		// bl.update(entidad);
		// SysMessage.info("Guardado correctamente.");
		// nuevo();
		// cargarLst();
		// visibleNuevoEditar = false;
		// } catch (Exception e) {
		// SysMessage.error("Fallo al guardar.");
		// }
		// }
		// } else {
		// SysMessage.warn("Se encontro observaciones al intentar guardar: " +
		// validacion);
		// }
		// }
	}

	public String getFormatoFactura() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Empresa empresa = (Empresa) bl.find(1l, Empresa.class);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 4);

			return "<div style='text-align: center;'>" + "<span style='font-size: large; font-weight: bold;'>"
					+ empresa.getNombre()
					+ "</span></div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>"
					+ empresa.getDireccion()
					+ "</div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>Santa Cruz - Bolivia</div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'><br></div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>FACTURA</div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>ORIGINAL</div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'><span style='font-size: 13.3333px;'>-----------------------------------------------------------------------------------------------------------------------------------------------</span></div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>NIT :"
					+ empresa.getNit()
					+ "</div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>FACTURA N° : 4321</div>"
					+ "<div style='text-align: center; font-size: 10pt; font-weight: normal;'>AUTORIZACION N° : "
					+ empresa.getNroAutorizacion()
					+ "</div><div style='font-size: 10pt; font-weight: normal;'><span style='font-size: 13.3333px;'>-----------------------------------------------------------------------------------------------------------------------------------------------</span></div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'><br></div><div style='font-size: 10pt; font-weight: normal;'>FECHA: "
					+ sdf.format(new Date())
					+ "</div><div style='font-size: 10pt; font-weight: normal;'>NOMBRE: "
					+ entidad.getCliente() != null ? entidad.getCliente().getNombreCompleto():""
					+ "</div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'>NIT/CI: "
					+  (entidad.getCliente() != null ? entidad.getCliente().getCi() : "")
					+ "</div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'><span style='font-size: 13.3333px;'>-----------------------------------------------------------------------------------------------------------------------------------------------</span></div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'>CANT &nbsp; &nbsp; CONCEPTO &nbsp; &nbsp; &nbsp; &nbsp; PRECIO &nbsp; &nbsp; TOTAL</div><div style='font-size: 10pt; font-weight: normal;'><span style='font-size: 13.3333px;'>-----------------------------------------------------------------------------------------------------------------------------------------------</span></div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'>1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;HELADO &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 12.50 &nbsp; &nbsp; &nbsp; &nbsp; 12.50</div><div style='font-size: 10pt; font-weight: normal;'><span style='font-size: 13.3333px;'>-----------------------------------------------------------------------------------------------------------------------------------------------</span></div>"
					+ "<div style='text-align: left; font-size: 10pt; font-weight: normal;'>Total a Pagar Bs: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 12.50</div>"
					+ "<div style='text-align: left; font-size: 10pt; font-weight: normal;'>Total Entregado Bs: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 20.00</div><div style='text-align: left; font-size: 10pt; font-weight: normal;'>Cambio Bs: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;7.50</div><div style='font-size: 10pt; font-weight: normal;'><br></div><div style='font-size: 10pt; font-weight: normal;'>Son doce con 50/100 Bs.</div><div style='font-size: 10pt; font-weight: normal;'><br></div><div style='font-size: 10pt; font-weight: normal;'>CODIGO DE CONTROL: A1-B2-C3-D4-E5</div>"
					+ "<div style='font-size: 10pt; font-weight: normal;'>FECHA LIMITE DE EMISION: " + sdf.format(cal.getTime()) + "</div>" + "<div style='font-size: 10pt; font-weight: normal;'>CAJERO: Andrea Carranza</div><div style='font-size: 10pt; font-weight: normal;'><br></div>";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getQr() {
		if (entidad != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Empresa empresa = (Empresa) bl.find(1l, Empresa.class);
				String separador = "|";
				String nitEmpresa = empresa.getNit();
				String nombreEmpresa = empresa.getNombre();
				String numeroFactura = "4321";
				String autorizacion = empresa.getNroAutorizacion();
				String fecha = entidad.getFechaHora() != null ? sdf.format(entidad.getFechaHora()) : "";
				String monto = "12.50";
				String control = "A1-B2-C3-D4-E5";
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 4);
				String fechaFin = sdf.format(cal.getTime());
				String nit = entidad.getCliente() != null ? String.valueOf(entidad.getCliente().getCi()) : "";
				String razonSocial = entidad.getCliente() != null ? entidad.getCliente().getNombreCompleto() : "";
				return nitEmpresa + separador + nombreEmpresa + separador + numeroFactura + separador + autorizacion + separador + fecha + separador + monto + separador + control + separador + fechaFin + separador + nit + separador + razonSocial;
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		return "";
	}

	public void atendido() {
		try {
			entidad.setEstado("ATENDIDO");
			bl.update(entidad);
			setVisibleNuevoEditar(false);
			SysMessage.info("Pedido atendido correctamente");
			cargarLst();
		} catch (Exception e) {
			e.printStackTrace();
			SysMessage.error("Fallo al actualizar el pedido");
		}
	}

	public void editar() {
		if (selected != null) {
			entidad = selected;
			cargarProductos(entidad.getId());
			nuevo = false;
			visibleNuevoEditar = true;
		} else {
			SysMessage.warn("No se encontro ningun registro seleccionado.");
		}
	}

	public void editar(Pedido obj) {
		selected = obj;
		editar();
	}

	public List<Pedido> getLst() {
		return lst;
	}

	public void setLst(List<Pedido> lst) {
		this.lst = lst;
	}

	public Pedido getSelected() {
		return selected;
	}

	public void setSelected(Pedido selected) {
		this.selected = selected;
	}

	public Pedido getEntidad() {
		return entidad;
	}

	public void setEntidad(Pedido entidad) {
		this.entidad = entidad;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isVisibleNuevoEditar() {
		return visibleNuevoEditar;
	}

	public void setVisibleNuevoEditar(boolean visibleNuevoEditar) {
		this.visibleNuevoEditar = visibleNuevoEditar;
	}

	public List<Object[]> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Object[]> lstProducto) {
		this.lstProducto = lstProducto;
	}

}
