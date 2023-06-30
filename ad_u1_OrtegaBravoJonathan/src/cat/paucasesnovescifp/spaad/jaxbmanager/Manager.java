
package cat.paucasesnovescifp.spaad.jaxbmanager;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import cat.paucasesnovescifp.spaad.definitions.*;
import java.util.ArrayList;
import cat.paucasesnovescifp.spaad.definitions.Order;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.Marshaller;

public class Manager {
    
    public void leerCliente(String ruta) throws JAXBException
    {
        int opcionRuta=0;
        if ((ruta.equals("customer.xml")) ||  (ruta.equals("Cliente_prueba.xml"))) opcionRuta=1;  
        else opcionRuta=2;
        
        System.out.println(ruta);
        
        switch (opcionRuta){
            case 1 -> {
                
                JAXBContext context = JAXBContext.newInstance(Customer.class);//asigno la clase java al xml con jaxb
                Unmarshaller unmarshaller = context.createUnmarshaller();//creo el objeto para leerlounmarshaller
                Customer cliente = (Customer) unmarshaller.unmarshal(new File(ruta)); //creo objeto donde descarga el unmarsahler
                           
                System.out.println("----------------------------------------");
                System.out.println("Cliente: "+ cliente.getCustomerID());
                System.out.println("Nombre: "+ cliente.getContactName());
                System.out.println("Telefono: "+ cliente.getPhone());
                System.out.println("Fax: "+ cliente.getFax()+"\n");
                                              
                    ArrayList<FullAddress>direcciones=cliente.getDirecciones();
                
                    for(FullAddress i: direcciones)
                    {
                    System.out.println("Direccion: "+i.getAddress());
                    System.out.println("Region: "+ i.getRegion());   
                    System.out.println("Ciudad: "+i.getCity());
                    System.out.println("Codigo postal: "+i.getPostalCode());
                    System.out.println("Pais: "+ i.getCountry()+"\n");                       
                    }
               
                        ArrayList<Order>ordenes=cliente.getOrders();
                
                        for(Order i: ordenes)
                        {
                        System.out.println("Pedido: "+ i.getOrder());
                        System.out.println("Fecha orden: "+i.getOrderDate());
                        System.out.println("Fecha envio: "+ i.getShippedDate());
                        System.out.println("Estado envio: "+ i.getShipStatus());
                        System.out.println("Coste: "+ i.getCost()+"\n");                   
                        }
                        
                System.out.println("----------------------------------------");
            }
                     
                    
            case 2 -> {
               
                JAXBContext context = JAXBContext.newInstance(Shop.class);//asigno la clase java al xml con jaxb
                Unmarshaller unmarshaller = context.createUnmarshaller();//creo el objeto para leerlounmarshaller
                Shop shop = (Shop) unmarshaller.unmarshal(new File(ruta)); //creo objeto donde descarga el unmarsahler
 
                if (ruta.equals("shop.xml")) 
                {
                    System.out.println("----------------------------------------");
                    System.out.println("Nombre: " + shop.getName()+" "+ shop.getClientes());
                    System.out.println("----------------------------------------");
                }else
                    { 
                    System.out.println("----------------------------------------");
                    System.out.println("Nombre: " + shop.getName());
                    ArrayList<Customer>clientes=shop.getClientes();
                    
                    for(Customer m:clientes)
                    {
                     System.out.println("  " + m.getCustomerID());
                     System.out.println("  " + m.getContactName());
                     System.out.println("  " + m.getPhone());
                     System.out.println("  " + m.getFax()+"\n"); 

                     ArrayList<FullAddress>direcciones=m.getDirecciones(); 
                     

                        for(FullAddress f:direcciones)
                        {
                        System.out.println("  " + f.getAddress());
                        System.out.println("  " + f.getCity());
                        System.out.println("  " + f.getRegion());
                        System.out.println("  " + f.getPostalCode());
                        System.out.println("  " + f.getCountry()+"\n");   
                        }
                            ArrayList<Order>pedidos=m.getOrders();
                            for(Order o:pedidos)
                            {
                            System.out.println("  " + o.getOrder());
                            System.out.println("  " + o.getOrderDate());
                            System.out.println("  " + o.getShippedDate());
                            System.out.println("  " + o.getShipStatus());
                            System.out.println("  " + o.getCost());
                            System.out.println("  " + o.getOrderTags());                          
                            }
                    }                           
            }
       
                System.out.println("----------------------------------------");
   
            }
            
            default ->{
                
                System.out.println("Dato introducido no valido");
                
                
            }

    }
                
}          
     //AQUI EMPIEZA EL METODO ESCRIBIR  
       
    public void escribirCliente(String rutaEscritura)throws JAXBException, IOException
    {
           
         int opcionRuta=0;
         
            if (rutaEscritura.equals("Cliente_prueba.xml")) opcionRuta=1;
   
            else opcionRuta=2;
        
                switch (opcionRuta){
                    case 1 -> {

                JAXBContext context = JAXBContext.newInstance(Customer.class);
                Marshaller marshaller = context.createMarshaller();
                Customer customer=new Customer();

                //cargamos datos del ciente

                customer.setCustomerID("5555");
                customer.setContactName("Francisco Sanchez");
                customer.setPhone("971525252");
                customer.setFax("971636363");

                //cargamos arraylist datos de direccion del cliente
                ArrayList<FullAddress>direccioNueva=new ArrayList<>();

                FullAddress nueva=new FullAddress();

                    nueva.setAddress("Calle del Cerro 89");
                    nueva.setRegion("CCMM");
                    nueva.setCity("Toledo");    
                    nueva.setPostalCode("45215");
                    nueva.setCountry("Spain");
                    direccioNueva.add(nueva);

                customer.setDirecciones(direccioNueva);

                //cargamos el arraylist de ordenes          

                ArrayList<Order> ordenesNuevas = new ArrayList();

                Order nuevaOrden=new Order();

                   nuevaOrden.setOrder("1023");
                   nuevaOrden.setOrderDate("10-05-99");
                   nuevaOrden.setShippedDate("25-05-99");
                   nuevaOrden.setShipStatus("OK");
                   nuevaOrden.setCost("786.50");
                   nuevaOrden.setOrderTags("None");
                   ordenesNuevas.add(0,nuevaOrden);

               
                        Order nuevaOrden1=new Order();

                           nuevaOrden1.setOrder("1256");
                           nuevaOrden1.setOrderDate("10-05-02");
                           nuevaOrden1.setShippedDate("23-05-02");
                           nuevaOrden1.setShipStatus("none");
                           nuevaOrden1.setCost("5632.2");
                           nuevaOrden1.setOrderTags("15% free2");
                           ordenesNuevas.add(1,nuevaOrden1);

                        customer.setOrders(ordenesNuevas);

                //marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "\n");
                //marshaller.marshal(Customer,System.out);
                //marshaller.marshal(cliente, new FileWriter(rutaEscritura,true));
                //JAXBContext contexto=JAXBContext.newInstance(Customer.class);

                //Marshaller marshaler=contexto.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(customer, new FileWriter("Cliente_prueba.xml",false));

                /*otra forma de hacerlo
                
                Writer escribe = null;
                try {
                    escribe = new FileWriter(rutaEscritura,false);
                    marshaller.marshal(customer, escribe);
                } finally {
                        try {
                            escribe.close();
                            } catch (IOException e) { System.out.print("ERROR LECTURA");}

                }
                */
                }
                case 2 ->{

                    JAXBContext context = JAXBContext.newInstance(Shop.class);
                    Marshaller marshaller = context.createMarshaller();
                    Shop shop=new Shop();

                    shop.setName("Nueva tienda");

                    ArrayList<Customer>clienteNuevo=new ArrayList<>();
                    Customer cliente=new Customer();

                    cliente.setCustomerID("2563");
                    cliente.setContactName("Perico el de los palotes");
                    cliente.setPhone("971526362");
                    cliente.setFax("971968596");
                    clienteNuevo.add(cliente);

                    shop.setClientes(clienteNuevo);

                        ArrayList<FullAddress>direccionNueva=new ArrayList();

                        FullAddress nueva=new FullAddress();

                        nueva.setAddress("Calle del pino 56, 3 A");
                        nueva.setRegion("Baleares");
                        nueva.setCity("Marratxi");
                        nueva.setPostalCode("07141");
                        nueva.setCountry("Spain");
                        direccionNueva.add(nueva);

                        cliente.setDirecciones(direccionNueva);

                            ArrayList<Order> ordenesNuevas = new ArrayList();

                            Order nuevaOrden=new Order();

                           nuevaOrden.setOrder("1205");
                           nuevaOrden.setOrderDate("05-12-07");
                           nuevaOrden.setShippedDate("02-12-07");
                           nuevaOrden.setShipStatus("NO");
                           nuevaOrden.setCost("566.50");
                           nuevaOrden.setOrderTags("10% off");    
                           ordenesNuevas.add(0,nuevaOrden);

                           cliente.setOrders(ordenesNuevas);
                
                        
                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    marshaller.marshal(shop, new FileWriter("Shop_prueba.xml",false));

                }
                default ->  System.out.println("Dato introducido no valido");

            }  
            
    }

}   
         
        
         
       
           
           
          
         
          
    
    
    
    
    
