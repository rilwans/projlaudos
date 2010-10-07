package br.com.laudos.facade;


import br.com.framework.hibernate.ControllerAPCC;

/**
 * @author vinicius
 *
 */
public class Facade extends ControllerAPCC {

	/**
	 * @return Retorna o controle do sistema.
	 */
	public static Facade getInstance() {
		if (controller == null) {
			controller = new Facade();
		}
		return (Facade) controller;
	}

}
