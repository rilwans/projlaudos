package br.com.veridistec.izbio.sample.service;

import br.com.veridistec.izbio.sample.model.Cadastro;

public class ServicoCadastro extends CRUDServiceBase<Cadastro, Long> implements CRUDService<Cadastro, Long> {
	public ServicoCadastro() {
		super(Cadastro.class);
	}

	private static final long serialVersionUID = -7398040354900536294L;

	@Override
	public Long getId(Cadastro t) {
		return t.getId();
	}

	@Override
	public void setId(Cadastro t, Long id) {
		t.setId(id);
	}

}
