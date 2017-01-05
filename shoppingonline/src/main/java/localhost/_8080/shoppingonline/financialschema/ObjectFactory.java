//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.01.03 à 09:47:58 AM EST 
//


package localhost._8080.shoppingonline.financialschema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the localhost._8080.shoppingonline.financialschema package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: localhost._8080.shoppingonline.financialschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PurchaseItemResponse }
     * 
     */
    public PurchaseItemResponse createPurchaseItemResponse() {
        return new PurchaseItemResponse();
    }

    /**
     * Create an instance of {@link RechargeResponse }
     * 
     */
    public RechargeResponse createRechargeResponse() {
        return new RechargeResponse();
    }

    /**
     * Create an instance of {@link PurchaseItemRequest }
     * 
     */
    public PurchaseItemRequest createPurchaseItemRequest() {
        return new PurchaseItemRequest();
    }

    /**
     * Create an instance of {@link RechargeRequest }
     * 
     */
    public RechargeRequest createRechargeRequest() {
        return new RechargeRequest();
    }

}
