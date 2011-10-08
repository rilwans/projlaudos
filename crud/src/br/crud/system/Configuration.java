package br.crud.system;

import br.crud.domain.Modelo;
import br.hibernate.utils.HibernateUtils;
import br.jsf.FilterFramework;



public class Configuration extends FilterFramework {

	@Override
	public void init() {
		HibernateUtils.beans.add(Modelo.class);

	}

}
