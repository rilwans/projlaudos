/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author 11102004
 */
@WebService(serviceName = "convertServer")
public class ConvertServer {

    /**
     * This is a sample web service operation
     */

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "realToDolar")
    public double realToDolar(@WebParam(name = "valor") double valor) {
        //TODO write your implementation code here:
        return (valor*2.21);
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "dolarToEuro")
    public double dolarToEuro(@WebParam(name = "valor") double valor) {
        //TODO write your implementation code here:
        return (valor*1.21);
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "euroToReal")
    public double euroToReal(@WebParam(name = "valor") double valor) {
        //TODO write your implementation code here:
        return (valor*3.21);
    }
}
