
package cat.paucasesnovescifp.spaad.definitions;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="FullAddress")
@XmlType(propOrder={"address","city","region","postalCode","country"})
public class FullAddress {
    
        private String Address;
        private String City;
        private String Region;
        private String PostalCode;
        private String Country;

    @XmlElement(name="Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @XmlElement(name="City")
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    @XmlElement(name="Region")
    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    @XmlElement(name="PostalCode")
    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    @XmlElement(name="Country")
    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    @Override
    public String toString() {
        return "FullAddress{" + "," + Address + "," + City + "," + Region + "," + PostalCode + "," + Country + '}';
    }
   
}
