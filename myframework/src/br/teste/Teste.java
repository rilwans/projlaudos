package br.teste;

import java.util.List;

import br.hibernate.operation.HibernateOperation;
import br.hibernate.utils.HibernateUtils;
import br.hibernate.utils.Modelo;


public class Teste {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		HibernateUtils.getSessionFactory();
		
		HibernateOperation hibernateOperation = new HibernateOperation();
		
		

		List<Modelo> lista = null;
		try {
			lista = (List<Modelo>) hibernateOperation.listAll(Modelo.class);
			for (Modelo modelo : lista) {
				Modelo mod = (Modelo) hibernateOperation.loadById(Modelo.class,"idModelo",modelo.getIdModelo());
				mod.setNmModelo("Alterado");
				hibernateOperation.delete(mod);
			}
			lista = (List<Modelo>) hibernateOperation.listAll(Modelo.class);
			for (Modelo modelo : lista) {
				System.out.println(modelo.getNmModelo());
			}
			System.out.println("Acabou");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
	}

}
