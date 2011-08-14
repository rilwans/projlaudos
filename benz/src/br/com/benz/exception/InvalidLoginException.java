/**
 *
 */
package br.com.benz.exception;

/**
 * Exception de login inválido
 *
 * @author vinicius
 *
 */
public class InvalidLoginException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 4436200670075308665L;

	/**
	 * @param err
	 */
	public InvalidLoginException(String err) {
		super(err);
	}
}
