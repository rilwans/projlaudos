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

	private Modelo modeloalterado = new Modelo();

	@SuppressWarnings("unchecked")
	public List<Modelo> getModelos() throws Exception {

		modelos = (List<Modelo>) Controler.getInstance().listAll(Modelo.class);
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo getModelo() throws Exception {

		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void insert() {
		Modelo mod = new Modelo();

		mod.setNmModelo(modelo.getNmModelo());
		mod.setTxtModelo(modelo.getNmModelo());

		try {
			Controler.getInstance().insert(mod);
			modelo = new Modelo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update() {

		Modelo mod = new Modelo();
		try {
			mod = (Modelo) Controler.getInstance().loadById(Modelo.class, "idModelo", modeloalterado.getIdModelo());
			mod.setNmModelo(modeloalterado.getNmModelo());

			Controler.getInstance().update(mod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloalterado = new Modelo();




	}

	@SuppressWarnings("unchecked")
	public void delete() {

		try {
			Modelo mod = new Modelo();
			mod = (Modelo) Controler.getInstance().loadById(Modelo.class,
					"idModelo", modelo.getIdModelo());
			Controler.getInstance().delete(mod);
			modelos = (List<Modelo>) Controler.getInstance().listAll(
					Modelo.class);
			modelo = new Modelo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the modeloalterado
	 */
	public Modelo getModeloalterado() {
		return modeloalterado;
	}

	/**
	 * @param modeloalterado the modeloalterado to set
	 */
	public void setModeloalterado(Modelo modeloalterado) {
		this.modeloalterado = modeloalterado;
	}

}
