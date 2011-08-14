package br.com.benz.domain.enums;

public enum TratamentoQueda {
	
	INTERNACAO(1),
	
	CIRURGIA(2),
	
	FISIOTERAPIA(3),
	
	ANALGESICOS(4),
	
	AINDATRATA(5),
	
	SEQUELA(6);
	
	private int value;
	
	TratamentoQueda(int value){
		this.value = value;
	}
	
	public static TratamentoQueda fromInt(int value) {
		switch (value) {
		case 1:
			return INTERNACAO;
		case 2:
			return CIRURGIA;
		case 3:
			return FISIOTERAPIA;
		case 4:
			return ANALGESICOS;
		case 5:
			return AINDATRATA;
		case 6:
			return SEQUELA;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case INTERNACAO:
			return "Internação";
		case CIRURGIA:
			return "Cirurgia";
		case FISIOTERAPIA:
			return "Fisioterapia";
		case ANALGESICOS:
			return "Analgesicos";
		case AINDATRATA:
			return "Ainda está em tratamento";
		case SEQUELA:
			return "Sequela";
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
