package br.com.benz.domain.enums;
/**
 * @author padua
 *
 */
public enum Receitador {
	/**
	 *
	 */
	
	MESMOMEDICO(1),
	
	MEDICOSDIFERENTES(2),
	
	OUTROSMEDICOSEMCONSULTAS(3),
	
	MEDICOPARENTEAMIGO(4);
	
	
	private int value;
	
	
	Receitador(int value){
		this.value = value;
	}
	
	public static Receitador fromInt(int value) {
		switch (value) {
		case 1:
			return MESMOMEDICO;
		case 2:
			return MEDICOSDIFERENTES;
		case 3:
			return OUTROSMEDICOSEMCONSULTAS;
		case 4:
			return MEDICOPARENTEAMIGO;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case MESMOMEDICO:
			return "Mesmo médico de sempre";
		case MEDICOSDIFERENTES:
			return "Medicos diferentes";
		case OUTROSMEDICOSEMCONSULTAS:
			return "Outros medicos em outras consultas";
		case MEDICOPARENTEAMIGO:
			return "Médico Amigo/Parente";
		}
		return null;
	}
	
	public String getToString() {
		return toString();
	}
	
	/**
	 * @return retorna o inteiro correspondete.
	 */
	public int getToInt() {
		return toInt();
	}

	public void setToInt(int tempo) {
		fromInt(tempo);
	}
	
	public int toInt() {
		return value;
	}
}
