package tienda_rentgames;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static tienda_rentgames.Manager.*;

public class Tienda_rentgames
{

    public static void main(String[] args) throws SQLException
    {
        boolean seguirPrograma = true;
        String acceso = null;
        //Manager.agregarUsuario();
        System.out.println("************************---------PROPICIOS DIAS!!!----------************************** \n-->> DESEA CARGAR LA BASE DE DATOS E INICIAR EL PROGRAMA RENTGAMES?? (pulse S para acceder)");
        Scanner sc = new Scanner(System.in);
        String resp = sc.next();

        if (resp.equals("S"))
        {

            establecerConexion();
            acceso = Manager.login();

        } else
        {
            System.out.println("--->>Opcion introducida erronea");
            seguirPrograma = false;
        }

        while (seguirPrograma)
        {
            int respuestaMenuPpal = 0;
            if (acceso.equals("OK"))
            {
                System.out.println("\t---------------------------------------------------------------");
                System.out.println("\t|------------------ MENU PRINCIPAL ---------------------------|\n");
                System.out.println("\t|                   1.  CLIENTES                              |");
                System.out.println("\t|                   2.  CONSULTAS                             |");
                System.out.println("\t|                   3.  STOCK                                 |");
                System.out.println("\t|                   4.  SALIR DE LA APLICACION                |");
                System.out.println("\t|-------------------------------------------------------------|");

                Scanner sc1 = new Scanner(System.in);
                try
                {
                    System.out.println("Elija una opcion: ");
                    respuestaMenuPpal = sc1.nextInt();
                } catch (InputMismatchException e)
                {
                    System.out.println("--->>Debes introducir un número!!...");
                    sc1.next();
                }

            } else
            {
                seguirPrograma = false;
            }

            switch (respuestaMenuPpal)
            {
                case 1 ->
                {

                    boolean seguirMenuCliente = true;
                    int respuestaMenuCliente = 0;
                    int idCliente = 0;
                    boolean seguir = false;

                    while (seguirMenuCliente)
                    {
                        while (respuestaMenuCliente != 1 && respuestaMenuCliente != 2 && respuestaMenuCliente != 3 && respuestaMenuCliente != 4 && respuestaMenuCliente != 5)
                        {
                            seguir = false;
                            System.out.println("\t-----------------------------------------");
                            System.out.println("\t|-------------- MENU CLIENTES ----------|\n");
                            System.out.println("\t|       1.  ALQUILAR VIDEOJUEGO         |");
                            System.out.println("\t|       2.  CARGAR SALDO                |");
                            System.out.println("\t|       3.  NUEVO CLIENTE               |");
                            System.out.println("\t|       4.  DEVOLVER VIDEOJUEGO         |");
                            System.out.println("\t|       5.  VOLVER AL MENU PPAL         |");
                            System.out.println("\t|---------------------------------------|");

                            Scanner sc1 = new Scanner(System.in);
                            try
                            {
                                System.out.println("Elija una opcion(Use mayusculas): ");
                                respuestaMenuCliente = sc1.nextInt();
                            } catch (InputMismatchException e)
                            {
                                System.out.println("--->>Debes introducir un número!!...");
                                sc1.next();
                            }
                            if (respuestaMenuCliente == 1)
                            {

                                while (!seguir)
                                {
                                    System.out.println("Introduzca numero de cliente: ");
                                    Scanner sc2 = new Scanner(System.in);
                                    idCliente = sc2.nextInt();

                                    if (valorEnTabla("cliente", idCliente) == 1)
                                    {
                                        seguir = true;
                                    } else
                                    {
                                        System.out.println("--->>Numero de socio erroneo!!!...");
                                    }
                                }
                                alquilarVideojuego(idCliente);
                                System.out.println("Saldo actual: " + obtenerSaldoRetornado(idCliente));
                                System.out.println("--------------------------------------");

                            } else if (respuestaMenuCliente == 2)
                            {

                                while (!seguir)
                                {
                                    System.out.println("Introduzca numero de cliente para cargar saldo: ");
                                    Scanner sc3 = new Scanner(System.in);
                                    idCliente = sc3.nextInt();

                                    if (valorEnTabla("cliente", idCliente) == 1)
                                    {
                                        seguir = true;
                                    } else
                                    {
                                        System.out.println("--->>Numero de socio erroneo!!!...");
                                    }
                                }

                                System.out.println("Introduzca cantidad a ingresar en saldo: ");
                                Scanner sc4 = new Scanner(System.in);
                                float cantidadPagada = sc4.nextInt();

                                transaccionCargaSaldo(idCliente, cantidadPagada);

                                insertMovimientoCaja(idCliente, cantidadPagada);

                                System.out.println("--------------------------------------");

                            } else if (respuestaMenuCliente == 3)
                            {
                                nuevoCliente();
                                System.out.println("--------------------------------------");

                            } else if (respuestaMenuCliente == 4)
                            {
                                devolverVideojuego();
                                System.out.println("--------------------------------------");
                            } else if (respuestaMenuCliente == 5)
                            {
                                seguirMenuCliente = false;
                                System.out.println("Saliendo del menu cliente");
                                System.out.println("------------------------------------------------------------------");
                                break;
                            }
                            respuestaMenuCliente = 0;

                        }
                    }
                }
                case 2 ->
                {

                    boolean seguirMenuConsultas = true;
                    int respuestaMenuConsultas = 0, idCliente = 0;
                    boolean seguir = false;

                    while (seguirMenuConsultas)
                    {
                        respuestaMenuConsultas = 0;
                        seguir = false;
                        while (respuestaMenuConsultas != 1 && respuestaMenuConsultas != 2 && respuestaMenuConsultas != 3 && respuestaMenuConsultas != 4)
                        {

                            System.out.println("\t-----------------------------------------");
                            System.out.println("\t|-------------- MENU CONSULTAS ---------|\n");
                            System.out.println("\t|       1.  DATOS DE CLIENTES           |");
                            System.out.println("\t|       2.  NUEVO TRABAJADOR            |");
                            System.out.println("\t|       3.  INFORME DE CAJA             |");
                            System.out.println("\t|       4.  VOLVER A MENU PPAL          |");
                            System.out.println("\t|---------------------------------------|");

                            Scanner sc1 = new Scanner(System.in);
                            try
                            {
                                System.out.println("Elija una opcion: ");
                                respuestaMenuConsultas = sc1.nextInt();
                            } catch (InputMismatchException e)
                            {
                                System.out.println("Debes introducir un número!!...");
                                sc1.next();
                            }
                            if (respuestaMenuConsultas == 1)
                            {
                                while (!seguir)
                                {
                                    System.out.println("Introduzca numero de cliente: ");
                                    Scanner sc2 = new Scanner(System.in);
                                    idCliente = sc2.nextInt();

                                    if (valorEnTabla("cliente", idCliente) == 1)
                                    {
                                        seguir = true;
                                    } else
                                    {
                                        System.out.println("Numero de socio erroneo!!!...");
                                    }
                                }

                                imprimirCliente(idCliente);
                                System.out.println("------------------------------------------------------------------");

                            } else if (respuestaMenuConsultas == 2)
                            {
                                agregarUsuario();
                                System.out.println("------------------------------------------------------------------");
                            } else if (respuestaMenuConsultas == 3)
                            {
                                informeCaja();
                                System.out.println("------------------------------------------------------------------");
                            } else if (respuestaMenuConsultas == 4)
                            {
                                seguirMenuConsultas = false;
                                System.out.println("sale del menu consultas");
                                System.out.println("------------------------------------------------------------------");
                                break;
                            }
                        }
                    }
                }

                case 3 ->
                {

                    boolean seguirMenuStock = true;
                    int respuestaMenuStock = 0, idCliente;

                    while (seguirMenuStock)
                    {

                        while (respuestaMenuStock != 1 && respuestaMenuStock != 2 && respuestaMenuStock != 3 && respuestaMenuStock != 4)
                        {

                            System.out.println("\t------------------------------------");
                            System.out.println("\t|-------------- MENU STOCK ---------|\n");
                            System.out.println("\t|       1.  CONSULAR STOCK          |");
                            System.out.println("\t|       2.  AÑADIR STOCK            |");
                            System.out.println("\t|       3.  DAR BAJA DE STOCK       |");
                            System.out.println("\t|       4.  VOLVER A MENU PPAL      |");
                            System.out.println("\t|-----------------------------------|");

                            System.out.println("Elija una opcion: ");
                            Scanner sc1 = new Scanner(System.in);
                            respuestaMenuStock = sc1.nextInt();
                        }
                        if (respuestaMenuStock == 1)
                        {
                            imprimirStock();
                            System.out.println("------------------------------------------------------------------");
                        } else if (respuestaMenuStock == 2)
                        {
                            nuevoVideojuego();
                            System.out.println("------------------------------------------------------------------");
                            System.out.println("nuevoVideojuego())");

                        } else if (respuestaMenuStock == 3)
                        {
                            System.out.println("Numero de filas: " + cuentaFilas("cliente"));

                            eliminaVideojuego(cargaVectorVideojuegos());
                            System.out.println("------------------------------------------------------------------");

                        } else if (respuestaMenuStock == 4)
                        {
                            seguirMenuStock = false;
                            System.out.println("Salir del menu consultas");
                            break;
                        }
                        respuestaMenuStock = 0;
                    }
                }
                case 4 ->
                {
                    seguirPrograma = false;
                    break;
                }
                default ->
                    System.out.println("Ha introducido numero no valido");
            }
        }
    }

}//fin de main

