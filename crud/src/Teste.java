import br.crud.domain.Modelo;
import br.hibernate.utils.HibernateUtils;


public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{
		HibernateUtils.beans.add(Modelo.class);
		HibernateUtils.getSessionFactory();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
