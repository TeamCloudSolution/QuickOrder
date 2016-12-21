package proyecto.business;

import proyecto.dao.MasterDao;
import proyecto.dao.MasterDaoInterfaceBl;
import proyecto.model.Empresa;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class GestionEmpresaBL implements Serializable, MasterDaoInterfaceBl {

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
		String sql = "select o from Empresa o";
		return masterDao.findAllQuery(Empresa.class, sql, null);
	}

}
