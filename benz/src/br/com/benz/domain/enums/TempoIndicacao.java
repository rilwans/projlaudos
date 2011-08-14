package br.com.benz.domain.enums;

public enum TempoIndicacao {

	MENORQUE2SEM(1),

	ENTRE3A4SEM(2),

	ENTRE5A6SEM(3),

	ATEPROXCONSULTA(4),

	ATEREMISSAOSINT(5),

	NAOMARCOUTEMPO(6);

	private int value;

	TempoIndicacao(int value) {
		this.value = value;
	}

	public static TempoIndicacao fromInt(int value) {
		switch (value) {
		case 1:
			return MENORQUE2SEM;
		case 2:
			return ENTRE3A4SEM;
		case 3:
			return ENTRE5A6SEM;
		case 4:
			return ATEPROXCONSULTA;
		case 5:
			return ATEREMISSAOSINT;
		case 6:
			return NAOMARCOUTEMPO;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case MENORQUE2SEM:
			return "Menor que 2 semanas";
		case ENTRE3A4SEM:
			return "Entre 3 a 4 semanas";
		case ENTRE5A6SEM:
			return "Entre 5 a 6 semanas";
		case ATEPROXCONSULTA:
			return "Ate proxima consulta";
		case ATEREMISSAOSINT:
			return "Até remissao da doença";
		case NAOMARCOUTEMPO:
			return "Não Marcou";
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
