package proyecto.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import proyecto.dao.MasterDao;
import proyecto.dao.MasterDaoInterfaceBl;
import proyecto.model.Categoria;
import proyecto.util.Parametro;

@Named
public class GestionCategoriaBL implements Serializable, MasterDaoInterfaceBl {

	private static final long serialVersionUID = 1L;

	@Inject
	private MasterDao masterDao;

	public String validar(Object entidad, boolean nuevo) {
		if (entidad != null && entidad instanceof Categoria) {
			StringBuilder sb = new StringBuilder();
			return sb.toString();
		} else {
			return "No se pudo validar el objeto.";
		}
	}

	@SuppressWarnings("unchecked")
	public int getCantidadConNombre(String nombre, long promocionId) throws Exception {
		String sql = "select count(0) as total from Categoria where estado = 1 and nombre = :nombre and promocion_id = :promocionId";
		Parametro p = new Parametro();
		p.put("nombre", nombre);
		p.put("promocionId", promocionId);
		List<BigDecimal> l = masterDao.findAllNativeQuery(sql, p);
		return l != null && l.size() > 0 ? l.get(0).intValue() : 0;
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
		String sql = "select l from Categoria l";
		return masterDao.findAllQuery(Categoria.class, sql, null);
	}

}
