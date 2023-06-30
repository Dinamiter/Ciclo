
package cat.paucasesnovescifp.spaad.definitions;
import java.util.ArrayList;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="Shop")
@XmlType(propOrder={"name","clientes"})
public class Shop {
    
    private String Name;
    
        
    private ArrayList<Customer>clientes;
   
    @XmlElement(name="Name")
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    //@XmlElementWrapper(name="clientes")
    @XmlElement(name="customer")
    public ArrayList<Customer> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Customer>clientes) {
        this.clientes = clientes;
    }

   @Override
    public String toString() {
        String datos = """
                       Tienda
                       nombre=""" + Name + "\n Cliente:\n";
        for (Customer c : clientes) {
            datos += c;
            datos += "\n";
        }
        
        return datos;
    }
    
    }
    
    
    
    
    
    
    
    
    
    

   
    
    
    

    

  
    
    
    
    
    
    

   

    
    
    
    

    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

