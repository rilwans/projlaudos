
package teste;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the teste package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DolarToEuroResponse_QNAME = new QName("http://teste/", "dolarToEuroResponse");
    private final static QName _RealToDolar_QNAME = new QName("http://teste/", "realToDolar");
    private final static QName _EuroToReal_QNAME = new QName("http://teste/", "euroToReal");
    private final static QName _DolarToEuro_QNAME = new QName("http://teste/", "dolarToEuro");
    private final static QName _EuroToRealResponse_QNAME = new QName("http://teste/", "euroToRealResponse");
    private final static QName _RealToDolarResponse_QNAME = new QName("http://teste/", "realToDolarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: teste
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RealToDolar }
     * 
     */
    public RealToDolar createRealToDolar() {
        return new RealToDolar();
    }

    /**
     * Create an instance of {@link DolarToEuroResponse }
     * 
     */
    public DolarToEuroResponse createDolarToEuroResponse() {
        return new DolarToEuroResponse();
    }

    /**
     * Create an instance of {@link DolarToEuro }
     * 
     */
    public DolarToEuro createDolarToEuro() {
        return new DolarToEuro();
    }

    /**
     * Create an instance of {@link RealToDolarResponse }
     * 
     */
    public RealToDolarResponse createRealToDolarResponse() {
        return new RealToDolarResponse();
    }

    /**
     * Create an instance of {@link EuroToRealResponse }
     * 
     */
    public EuroToRealResponse createEuroToRealResponse() {
        return new EuroToRealResponse();
    }

    /**
     * Create an instance of {@link EuroToReal }
     * 
     */
    public EuroToReal createEuroToReal() {
        return new EuroToReal();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DolarToEuroResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teste/", name = "dolarToEuroResponse")
    public JAXBElement<DolarToEuroResponse> createDolarToEuroResponse(DolarToEuroResponse value) {
        return new JAXBElement<DolarToEuroResponse>(_DolarToEuroResponse_QNAME, DolarToEuroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealToDolar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teste/", name = "realToDolar")
    public JAXBElement<RealToDolar> createRealToDolar(RealToDolar value) {
        return new JAXBElement<RealToDolar>(_RealToDolar_QNAME, RealToDolar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EuroToReal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teste/", name = "euroToReal")
    public JAXBElement<EuroToReal> createEuroToReal(EuroToReal value) {
        return new JAXBElement<EuroToReal>(_EuroToReal_QNAME, EuroToReal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DolarToEuro }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teste/", name = "dolarToEuro")
    public JAXBElement<DolarToEuro> createDolarToEuro(DolarToEuro value) {
        return new JAXBElement<DolarToEuro>(_DolarToEuro_QNAME, DolarToEuro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EuroToRealResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teste/", name = "euroToRealResponse")
    public JAXBElement<EuroToRealResponse> createEuroToRealResponse(EuroToRealResponse value) {
        return new JAXBElement<EuroToRealResponse>(_EuroToRealResponse_QNAME, EuroToRealResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealToDolarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://teste/", name = "realToDolarResponse")
    public JAXBElement<RealToDolarResponse> createRealToDolarResponse(RealToDolarResponse value) {
        return new JAXBElement<RealToDolarResponse>(_RealToDolarResponse_QNAME, RealToDolarResponse.class, null, value);
    }

}
