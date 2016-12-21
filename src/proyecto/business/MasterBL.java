package proyecto.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import proyecto.dao.MasterDao;
import proyecto.util.Parametro;

@Named
public class MasterBL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MasterDao dao;

	public void save(Object entidad) throws Exception {
		dao.save(entidad);
	}

	public void remove(Object entidad) throws Exception {
		dao.remove(entidad);
	}

	public void update(Object entidad) throws Exception {
		dao.update(entidad);
	}

	@SuppressWarnings("rawtypes")
	public Object find(Object entityKey, Class clase) throws Exception {
		return dao.find(entityKey, clase);
	}

	@SuppressWarnings("rawtypes")
	public List findAll(Class clase) throws Exception {
		return dao.findAll(clase);
	}

	@SuppressWarnings("rawtypes")
	public List findAllNamedQuery(Class clase, String namedQuery, Parametro p) throws Exception {
		return dao.findAllNamedQuery(clase, namedQuery, p);
	}

	@SuppressWarnings("rawtypes")
	// public List findAllNativeQuery(String nativeQuery, Map<String, Object>
	// parametros) throws Exception {
	public List findAllNativeQuery(String nativeQuery, Parametro p) throws Exception {
		return dao.findAllNativeQuery(nativeQuery, p);
	}

	@SuppressWarnings("rawtypes")
	// public List findAllNativeQuery(Class clase, String nativeQuery,
	// Map<String, Object> parametros) throws Exception {
	public List findAllNativeQuery(Class clase, String nativeQuery, Parametro p) throws Exception {
		return dao.findAllNativeQuery(clase, nativeQuery, p);
	}

	// public void executeUpdateNativeQuery(String sql, Map<String, Object>
	// parametros) throws Exception {
	public void executeUpdateNativeQuery(String sql, Parametro p) throws Exception {
		dao.executeUpdateNativeQuery(sql, p);
	}

}
