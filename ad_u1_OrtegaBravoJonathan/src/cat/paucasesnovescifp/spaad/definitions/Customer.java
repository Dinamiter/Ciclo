

package cat.paucasesnovescifp.spaad.definitions;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="Customer")
//@XmlAccessorType (XmlAccessType.NONE)
@XmlType(propOrder={"customerID","contactName","phone","fax","direcciones","orders"})
public class Customer {
    
    
    private String CustomerID;
    private String ContactName;
    private String Phone;
    private String Fax;
        
    private ArrayList<FullAddress>direcciones;
    
    private ArrayList<Order>Orders;
    

    public Customer() {
    }
    
    @XmlAttribute(name="CustomerID")
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    @XmlElement(name="ContactName")
    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    @XmlElement(name="Phone")
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @XmlElement(name="Fax")
    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

  
   @XmlElement(name="FullAddress")
    public ArrayList<FullAddress> getDirecciones() {
        return direcciones;
    }
    
    public void setDirecciones(ArrayList<FullAddress> direcciones) {
        this.direcciones = direcciones;
    }

    @XmlElementWrapper(name="Orders")
    @XmlElement(name="Order")
    public ArrayList<Order> getOrders() {
        return Orders;
    }

     public void setOrders(ArrayList<Order> Orders) {
        this.Orders = Orders;
    }
     
/*
    @Override
    public String toString() {
      
        String datos = "Customer{" + "CustomerID=" + CustomerID + ", ContactName=" + ContactName + ", Phone=" + Phone + ", Fax=" + Fax + ", direcciones=" + direcciones; 
        for (Order c : Orders) {
            datos += c;
            datos += "\n";
        }
               
        
        return datos;
    }
 */      

    @Override
    public String toString() {
        return "Customer{" + "CustomerID=" + CustomerID + ", ContactName=" + ContactName + ", Phone=" + Phone + ", Fax=" + Fax + ", direcciones=" + direcciones + ", Orders=" + Orders + '}';
    }
    }

    

    
    

    

    

    

   

    

    
    
    
    
    
    
    
   
    
    

    
    
    

