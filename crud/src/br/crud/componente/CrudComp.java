package br.crud.componente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.crud.domain.Modelo;
import br.crud.system.Controler;

@ManagedBean
@ViewScoped
public class CrudComp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1982179516716470436L;

	private List<Modelo> modelos = new ArrayList<Modelo>();

	private Modelo modelo = new Modelo();

	@SuppressWarnings("unchecked")
	public List<Modelo> getModelos() throws Exception {
		Modelo mod = new Modelo();
		mod.setNmModelo("sdsada");
		mod.setTxtModelo("sdasdsa");
		
		Controler.getInstance().insert(mod);
		
		modelos = (List<Modelo>) Controler.getInstance().listAll(Modelo.class);
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo getModelo() throws Exception {
		for (Modelo mod : getModelos()) {
			modelo = mod;
		}
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}
