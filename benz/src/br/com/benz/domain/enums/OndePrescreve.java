/**
 * 
 */
package br.com.benz.domain.enums;

/**
 * @author padua
 *
 */
public enum OndePrescreve {
	
	
	SERVICOPUBLICO(1),
	
	SERVICOPRIVADO(2),
	
	AMBOS(3);
	
	private int value;
	
	private OndePrescreve(int value) {
		this.value = value;
	}

	public static OndePrescreve fromInt(int value) {
		switch (value) {
		case 1:
			return SERVICOPUBLICO;
		case 2:
			return SERVICOPRIVADO;
		case 3:
			return AMBOS;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case SERVICOPUBLICO:
			return "Serviço publico";
		case SERVICOPRIVADO:
			return "Serviço Privado";
		case AMBOS:
			return "Ambos";
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
