package br.com.benz.domain.enums;

public enum CompraSemReceita {
	
	SIMSEMPRE(1),
	
	SIMNAOCONSEGUI(2),
	
	NAOTENTEI(3);
	
	private int value;
	
	CompraSemReceita(int value){
		this.value = value;
	}
	
	
	public static CompraSemReceita fromInt(int value) {
		switch (value) {
		case 1:
			return SIMSEMPRE;
		case 2:
			return SIMNAOCONSEGUI;
		case 3:
			return NAOTENTEI;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case SIMSEMPRE:
			return "Sim, Sempre Consigo";
		case SIMNAOCONSEGUI:
			return "Sim, não consegui";
		case NAOTENTEI:
			return "Não Tentei";
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
