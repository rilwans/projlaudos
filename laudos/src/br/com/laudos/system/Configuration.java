package br.com.laudos.system;

import br.com.framework.hibernate.HibernateUtil;
import br.com.framework.jsf.FilterJSF;
import br.com.laudos.domain.Convenio;
import br.com.laudos.domain.Laudo;
import br.com.laudos.domain.Paciente;
import br.com.laudos.domain.Teste;
import br.com.laudos.domain.Usuario;

public class Configuration extends FilterJSF {

	@Override
	public void init() {

		HibernateUtil.beans.add(Paciente.class);
		HibernateUtil.beans.add(Laudo.class);
		HibernateUtil.beans.add(Teste.class);
		HibernateUtil.beans.add(Usuario.class);
		HibernateUtil.beans.add(Convenio.class);
	}
}
