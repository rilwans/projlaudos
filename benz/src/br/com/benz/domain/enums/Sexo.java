/**
 *
 */
package br.com.benz.domain.enums;

/**
 * @author vinicius
 *
 */
public enum Sexo {
	/**
	 *
	 */
	MASCULINO(1), /**
	 *
	 */
	FEMININO(2);

	private int value;

	Sexo(int value) {
		this.value = value;
	}

	/**
	 * @param value
	 * @return Retorna o enum do sexo.
	 */
	public static Sexo fromInt(int value) {
		switch (value) {
		case 1:
			return MASCULINO;
		case 2:
			return FEMININO;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case MASCULINO:
			return "Masculino";
		case FEMININO:
			return "Feminino";
		}
		return null;
	}

	/**
	 * @return Retorna o nome formatado.
	 */
	public String getToString() {
		return toString();
	}

	/**
	 * @return retorna o inteiro correspondete.
	 */
	public int getToInt() {
		return toInt();
	}

	public void setToInt(int sexo) {
		fromInt(sexo);
	}

	/**
	 * @return o interiro representando o valor!
	 */
	public int toInt() {
		return value;
	}
}