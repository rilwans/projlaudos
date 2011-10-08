package br.com.system;

import br.com.domain.Modelo;
import br.hibernate.utils.HibernateUtils;
import br.jsf.FilterFramework;



public class Configuration extends FilterFramework {

	@Override
	public void init() {
		HibernateUtils.beans.add(Modelo.class);

	}

}
