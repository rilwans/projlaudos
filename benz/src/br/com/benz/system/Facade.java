/**
 *
 */
package br.com.benz.system;

import br.com.framework.hibernate.ControllerAPCC;

/**
 * @author padua
 * 
 */
public class Facade extends ControllerAPCC {

	public static Facade getInstance() {
		if (controller == null) {
			controller = new Facade();
		}
		return (Facade) controller;
	}

}
