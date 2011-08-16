package br.com.benz.system;

import br.com.benz.domain.Medico;
import br.com.benz.domain.Paciente;
import br.com.framework.hibernate.HibernateUtil;
import br.com.framework.jsf.FilterJSF;

public class Configuration extends FilterJSF {

	@Override
	public void init() {
		HibernateUtil.beans.add(Paciente.class);
		HibernateUtil.beans.add(Medico.class);
	}
}
