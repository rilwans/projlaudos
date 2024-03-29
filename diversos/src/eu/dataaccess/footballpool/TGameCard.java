
package eu.dataaccess.footballpool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tGameCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tGameCard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dGame" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="iMinute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sPlayerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bYellowCard" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="bRedCard" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sTeamName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sTeamFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sTeamFlagLarge" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tGameCard", propOrder = {
    "dGame",
    "iMinute",
    "sPlayerName",
    "bYellowCard",
    "bRedCard",
    "sTeamName",
    "sTeamFlag",
    "sTeamFlagLarge"
})
public class TGameCard {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dGame;
    protected int iMinute;
    @XmlElement(required = true)
    protected String sPlayerName;
    protected boolean bYellowCard;
    protected boolean bRedCard;
    @XmlElement(required = true)
    protected String sTeamName;
    @XmlElement(required = true)
    protected String sTeamFlag;
    @XmlElement(required = true)
    protected String sTeamFlagLarge;

    /**
     * Gets the value of the dGame property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDGame() {
        return dGame;
    }

    /**
     * Sets the value of the dGame property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDGame(XMLGregorianCalendar value) {
        this.dGame = value;
    }

    /**
     * Gets the value of the iMinute property.
     * 
     */
    public int getIMinute() {
        return iMinute;
    }

    /**
     * Sets the value of the iMinute property.
     * 
     */
    public void setIMinute(int value) {
        this.iMinute = value;
    }

    /**
     * Gets the value of the sPlayerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPlayerName() {
        return sPlayerName;
    }

    /**
     * Sets the value of the sPlayerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPlayerName(String value) {
        this.sPlayerName = value;
    }

    /**
     * Gets the value of the bYellowCard property.
     * 
     */
    public boolean isBYellowCard() {
        return bYellowCard;
    }

    /**
     * Sets the value of the bYellowCard property.
     * 
     */
    public void setBYellowCard(boolean value) {
        this.bYellowCard = value;
    }

    /**
     * Gets the value of the bRedCard property.
     * 
     */
    public boolean isBRedCard() {
        return bRedCard;
    }

    /**
     * Sets the value of the bRedCard property.
     * 
     */
    public void setBRedCard(boolean value) {
        this.bRedCard = value;
    }

    /**
     * Gets the value of the sTeamName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTeamName() {
        return sTeamName;
    }

    /**
     * Sets the value of the sTeamName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTeamName(String value) {
        this.sTeamName = value;
    }

    /**
     * Gets the value of the sTeamFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTeamFlag() {
        return sTeamFlag;
    }

    /**
     * Sets the value of the sTeamFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTeamFlag(String value) {
        this.sTeamFlag = value;
    }

    /**
     * Gets the value of the sTeamFlagLarge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTeamFlagLarge() {
        return sTeamFlagLarge;
    }

    /**
     * Sets the value of the sTeamFlagLarge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTeamFlagLarge(String value) {
        this.sTeamFlagLarge = value;
    }

}
