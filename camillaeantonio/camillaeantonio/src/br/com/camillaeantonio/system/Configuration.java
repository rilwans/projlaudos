package br.com.camillaeantonio.system;

import br.com.camillaeantonio.domain.Confirma;
import br.com.framework.hibernate.HibernateUtil;
import br.com.framework.jsf.FilterJSF;

public class Configuration extends FilterJSF {

	@Override
	public void init() {
		HibernateUtil.beans.add(Confirma.class);

	}
}
