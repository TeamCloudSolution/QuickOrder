package proyecto.util;

import java.util.HashMap;
import java.util.Map;

public class Parametro {

	private Map<String, Object> parametros;

	public Parametro() {
		this.parametros = new HashMap<String, Object>();
	}

	public void put(String nombre, Object valor) {
		parametros.put(nombre, valor);
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

}
