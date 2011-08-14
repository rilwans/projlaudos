/**
 *
 */
package br.com.benz.exception;

/**
 * Exception de conversao de data invalido
 *
 * @author padua
 *
 */
public class InvalidConvertDateException extends Exception {


	/**
	 *
	 */
	private static final long serialVersionUID = -2412995696759433615L;

	/**
	 * @param err
	 */
	public InvalidConvertDateException(String err) {
		super(err);

	}
}
