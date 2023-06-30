/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnovescifp.spaad;

import cat.paucasesnovescifp.spaad.jaxbmanager.*;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;

public class ClasePrincipal {
    
    
    public static void main(String[]args) throws JAXBException ,IOException
    {
               
                       
    String rutaFicheroCustomer="customer.xml";
    String rutaFicheroShop="shop.xml";
    String rutaEscrituraCustomer="Cliente_prueba.xml";
    String rutaEscrituraShop="Shop_prueba.xml";
    
    Manager manager=new Manager();
    boolean seguir=true;
    
        while(seguir)
        {
    
            System.out.println("--------------------------------------------------------------");
            System.out.println("¿Que accion desea realizar?"); 
            System.out.println("1.Imprimir en pantalla archivo costumer.xml o shop.xml");
            System.out.println("2.Imprimir en pantalla archivo Cliente_prueba.xml o shop_prueba.xml");
            System.out.println("3.Rellenar fichero con nuevo cliente o nueva tienda(genera cliente_prueba.xml o shop_prueba.xml)");
            System.out.println("---------------------------------------------------------------");

        
            Scanner sc=new Scanner(System.in);
            int respuesta=sc.nextInt();

            switch (respuesta){
                case 1 -> {

                    System.out.println("¿Que fichero quiere abrir? \n 1. customer.xml \n 2. shop.xml");
                    Scanner sc2=new Scanner(System.in);
                    String respuestaFicheroAbrir=sc2.nextLine();

                    if(respuestaFicheroAbrir.contentEquals("1"))
                            {
                            System.out.println("Imprimiendo en pantalla archivo customer.xml...");
                            manager.leerCliente(rutaFicheroCustomer);                    
                            }else if(respuestaFicheroAbrir.contentEquals("2"))
                                    {
                                     System.out.println("Imprimiendo en pantalla archivo Shop.xml...");
                                     manager.leerCliente(rutaFicheroShop);                                 
                                    }
                }                 

                case 2 -> {

                    System.out.println("¿Que fichero quiere abrir? \n 1. Cliente_prueba.xml \n 2. shop_prueba.xml");
                    Scanner sc2=new Scanner(System.in);
                    String respuestaFicheroAbrir=sc2.nextLine();

                    if(respuestaFicheroAbrir.contentEquals("1"))
                            {

                            try{                   
                                manager.leerCliente(rutaEscrituraCustomer);
                                 System.out.println("Imprimiendo en pantalla archivo customer.xml...");
                                }catch(JAXBException ioe){
                                                        System.out.println("ERROR DE LECTURA.Tal vez el fichero no exista aun"
                                                        + " o este defectuoso");
                                                        }


                            }else if(respuestaFicheroAbrir.contentEquals("2"))
                                    {
                                     
                                     try{          
                                         manager.leerCliente(rutaEscrituraShop); 
                                         System.out.println("Imprimiendo en pantalla archivo Shop_prueba.xml...");
                                        }catch(JAXBException ioe){
                                                                System.out.println("ERROR DE LECTURA.Tal vez el fichero no exista aun"
                                                                 + " o este defectuoso");                                               
                                                               }           
                                    }
                }

                case 3 -> {
                    System.out.println("Rellenar fichero cliente nuevo...");

                    System.out.println("¿Que fichero quiere rellenar? \n 1. Cliente_prueba.xml \n 2. Shop_prueba.xml");
                    Scanner sc2=new Scanner(System.in);
                    String respuestaFicheroRellenar=sc2.nextLine();

                    if(respuestaFicheroRellenar.contentEquals("1"))
                            {
                            System.out.println("Rellenar fichero Cliente_prueba.xml...");

                             manager.escribirCliente(rutaEscrituraCustomer); 

                            }else if(respuestaFicheroRellenar.contentEquals("2"))
                                    {
                                     System.out.println("Rellenar fichero Shop_prueba.xml...");
                                     manager.escribirCliente(rutaEscrituraShop);                                 
                                    }

                    }

                    default -> System.out.println("Ha introducido numero no valido");
                }

        System.out.println("¿Continuar con el programa? (Pulse cualquier tecla para continuar/ pulse N para salir)  :");
        Scanner cont=new Scanner(System.in);
        String continuar=cont.nextLine();
        if (continuar.equals("N")) seguir=false;
        
        }
    }
}
