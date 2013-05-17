package teste;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Calculos implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -66185313211531390L;
	private double valor = 0;
	private double result = 0;
	private double result1 = 0;
	private double result2 = 0;
	private double toreal = 0;
	private double todolar = 0;
	private double toeuro = 0;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public double getResult1() {
		return result1;
	}

	public void setResult1(double result1) {
		this.result1 = result1;
	}

	public double getResult2() {
		return result2;
	}

	public void setResult2(double result2) {
		this.result2 = result2;
	}

	public double getToreal() {

		return toreal;
	}

	public void setToreal(double toreal) {
		this.toreal = toreal;

	}

	public double getTodolar() {

		return todolar;
	}

	public void setTodolar(double todolar) {
		this.todolar = todolar;
	}

	public double getToeuro() {

		return toeuro;
	}

	public void setToeuro(double toeuro) {
		this.toeuro = toeuro;
	}

	public void calcular() {
		ConvertServer_Service server = new ConvertServer_Service();
		ConvertServer soap = server.getConvertServerPort();

		result = soap.dolarToEuro(getToeuro());
		System.out.println(result);

		result1 = soap.realToDolar(getTodolar());
		result2 = soap.euroToReal(getToreal());
	}
}
