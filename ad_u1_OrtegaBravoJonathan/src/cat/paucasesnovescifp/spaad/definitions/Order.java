package cat.paucasesnovescifp.spaad.definitions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="Orders")
@XmlType(propOrder={"order", "orderDate","shippedDate","shipStatus","cost","orderTags"})
//@XmlAccessorType (XmlAccessType.NONE)
public class Order {
    
    private String OrderID;
    private String OrderDate;
    private String ShippedDate;
    private String ShipStatus;
    private String Cost;
    private String OrderTags;
    

    public Order() {
    }
    
   
    @XmlAttribute(name="OrderID")
    public String getOrder() {
        return OrderID;
    }

    public void setOrder(String Order) {
        this.OrderID = Order;
    }

    @XmlElement(name="OrderDate")
    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    @XmlElement(name="ShippedDate")
    public String getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(String ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    @XmlElement(name="ShipStatus")
    public String getShipStatus() {
        return ShipStatus;
    }

    public void setShipStatus(String ShipStatus) {
        this.ShipStatus = ShipStatus;
    }

    @XmlElement(name="Cost")
    public String getCost() {
        return Cost;
    }

    public void setCost(String Cost) {
        this.Cost = Cost;
    }

    @XmlElement(name="OrderTags")
    public String getOrderTags() {
        return OrderTags;
    }

    public void setOrderTags(String OrderTags) {
        this.OrderTags = OrderTags;
    }

    @Override
    public String toString() {
        return "Order{" + "Order=" + OrderID  + ", OrderDate=" + OrderDate + ", ShippedDate=" + ShippedDate + ", ShipStatus=" + ShipStatus + ", Cost=" + Cost + ", OrderTags=" + OrderTags + '}';
    }
    
}
