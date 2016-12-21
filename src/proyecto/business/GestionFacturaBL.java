package proyecto.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import proyecto.dao.MasterDao;
import proyecto.dao.MasterDaoInterfaceBl;
import proyecto.model.Pedido;
import proyecto.util.Parametro;

@Named
public class GestionFacturaBL implements Serializable, MasterDaoInterfaceBl {

	private static final long serialVersionUID = 1L;

	@Inject
	private MasterDao masterDao;

	public String validar(Object entidad, boolean nuevo) {
		return "";
	}

	public void save(Object entidad) throws Exception {
		masterDao.save(entidad);
	}

	public void remove(Object entidad) throws Exception {
		masterDao.remove(entidad);
	}

	public void update(Object entidad) throws Exception {
		masterDao.update(entidad);
	}

	@SuppressWarnings("rawtypes")
	public Object find(Object entityKey, Class clase) throws Exception {
		return masterDao.find(entityKey, clase);
	}

	@SuppressWarnings({ "rawtypes" })
	public List findAll() throws Exception {
		String sql = "select o from Pedido o where o.estado = 'ATENDIDO' order by o.fechaHora desc";
		return masterDao.findAllQuery(Pedido.class, sql, null);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findAllProductos(long pedidoId) throws Exception {
		String sql = "select c.nombre as categoria, p.nombre as producto, p.descripcion, d.cantidad from detalle_pedido d inner join producto p on ( p.id = d.id_producto ) inner join categoria c on ( c.id = p.id_categoria ) WHERE d.id_pedido = :pedidoId";
		Parametro p = new Parametro();
		p.put("pedidoId", pedidoId);
		return masterDao.findAllNativeQuery(sql, p);
	}

}
