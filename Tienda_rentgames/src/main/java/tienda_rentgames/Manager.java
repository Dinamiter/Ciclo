package tienda_rentgames;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

//import static tienda_rentgames.Manager.establecerConexion;

/**
 *
 * @author Jonathan Ortega Bravo
 */
class Manager {

    public static String usuarioAlquiler;
    public static float sumaSaldo=0;
    
//METODO ESTABLECE CONEXION
        static Connection establecerConexion()
	{
            String usuario="root", clave="noruega80", url="jdbc:mysql://localhost:3306/rentgames";
            Connection conexion=null;
		try{
                    
		conexion=DriverManager.getConnection(url,usuario,clave);
	
		}catch(SQLException e){System.out.println("Error de conexion:"+ e.getSQLState());}

	return conexion;
	}
        
        static Connection establecerConexionAutoCommitFalse()
	{
            String usuario="root", clave="noruega80", url="jdbc:mysql://localhost:3306/rentgames";
            Connection conexion=null;
		try{
                    
		conexion=DriverManager.getConnection(url,usuario,clave);
                conexion.setAutoCommit(true);
		}catch(SQLException e){System.out.println("Error de conexion:"+ e.getSQLState());}

	return conexion;
	}
    
//METODO QUE REALIZA UN  LOGIN          
        static String login() throws SQLException
        {
	String acceso=null, usuario=null;
        boolean seguir=false;
        

        while(seguir==false)
        {
	Scanner sc=new Scanner(System.in);
	System.out.println("  -->>>>>> Introduce tu numero de usuario: ");
	usuario=String.valueOf(sc.nextInt());
        usuarioAlquiler=usuario;
        int idTrabajador=Integer.parseInt(usuario);
        
        if(valorEnTabla("trabajador",idTrabajador )==1) seguir=true;   
        else System.out.println("--->>Numero de socio erroneo!!!...");
        }

	Scanner sc1=new Scanner(System.in);
	System.out.println(" -->>>>>  Introduce la contraseña de usuario: ");
	String contrasenia=String.valueOf(sc1.nextLine());

	String sentenciaSql=("SELECT * FROM trabajador WHERE IdTrabajador='"+usuario+"' AND contrasenia='"+ contrasenia+"' ");

		try
		{

		Statement st=establecerConexion().createStatement();
		ResultSet rs=st.executeQuery(sentenciaSql);
                

			if(rs.next())
			{
			int resultado=1;
				if(resultado==1)
				{
				acceso="OK";
                                System.out.println("Acceso permitido");				
				}			
			}else{System.out.println("Usuario o contraseña erroneos");}
		
		}catch(SQLException e){System.out.println("Error de conexion:"+ e.getSQLState());}
        return acceso;
        } 
             
        
//METODO AGREGA NUEVO USUARIO/TRABAJADOR DEL SISTEMA
        static void agregarUsuario() 
        {
            System.out.println("Introduce nombre del nuevo trabajador: ");
            Scanner sc=new Scanner(System.in);
            String usuario=sc.nextLine();

            System.out.println("Introduce la contrasenia del nuevo trabajador:");
            Scanner sc1=new Scanner(System.in);
            String contrasenia=String.valueOf(sc.nextLine());

            String sentenciaSql="INSERT INTO trabajador(nombre, contrasenia) VALUES (?,?)";

                try{

                    try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSql)) 
                    {
                        pst.setString(1,usuario);
                        pst.setString(2,contrasenia);
                        pst.executeUpdate();
                        System.out.println("\t------------------------------");
                        System.out.println("\t  Usuario grabado con exito");
                        System.out.println("\t------------------------------");
                    }

                }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());}       
        }
        
//METODO PARA QUE CLIENTE ALQUILE VIDEOJUEGO
        static void alquilarVideojuego(int idCliente) throws SQLException
        {
           
            boolean seguir=false;
            int numVideojuego=0;
        
            if(obtenerSaldoRetornado(idCliente)>=3.5)
            {
                while (!seguir)
                {
                System.out.println("Introduce el numero del videojuego:");
                Scanner sc1=new Scanner(System.in);
                numVideojuego=sc1.nextInt();

                if(valorEnTabla("videojuegos", numVideojuego)==1) seguir=true;   
                else System.out.println("Numero de videojuego erroneo!!!...");
                }

                String sentenciaSql="INSERT INTO alquileres(idCliente, idVideojuego,atendidoPor,create_at) VALUES (?,?,?,now())";

                    try{

                        try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSql)) 
                        {

                            pst.setInt(1,idCliente);
                            pst.setInt(2,numVideojuego);
                            pst.setString(3, usuarioAlquiler);
                            //PARA ENVIAR LA FECHA DATE TAMBIEN SE PODRIA HACER CON LAS SENTENCIAS DE ABAJO PERO DECIDI USAR EL NOW()
                            //java.util.Date utilDate = new java.util.Date();
                            //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                            //pst.setDate(4,sqlDate );
                        
                            int filas=pst.executeUpdate();
                            System.out.println("\t------------------------------");
                            System.out.println("\t  Alquiler grabado con exito");
                            System.out.println("\t------------------------------");
                            System.out.println("----Se han modificado " +filas+ " en registro de alquileres----");                    
                        }
                         if(establecerConexion()!=null) establecerConexion().close();
                    }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());} 

                    String sentenciaSqlalquilado="UPDATE videojuegos SET alquilado = 'S' WHERE idVideojuego= '"+numVideojuego+"' ";

                        try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSqlalquilado)) 
                        {

                            int filas = pst.executeUpdate();
                            System.out.println(filas+ "----Se han modificado " +filas+ " en registro de videojuegos----");

                             if(establecerConexion()!=null) establecerConexion().close();
                        }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());} 

                    
                    String sentenciaSqlRestarSaldo="UPDATE cliente SET saldo = ? WHERE idCliente= '"+idCliente+"' ";

                        try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSqlRestarSaldo)) 
                        {
                            float saldo=obtenerSaldoRetornado(idCliente);
                            saldo-=precioAlquiler(numVideojuego);
                            pst.setFloat(1, saldo);   
                            int filas = pst.executeUpdate();
                            System.out.println(filas+ "----Se han modificado " +filas+ " en registro de cliente----");

                             if(establecerConexion()!=null) establecerConexion().close();
                        }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());} 

                } else System.out.println("-->>>>No hay saldo suficiente, debe recargar el saldo");
            }
        
//DEVUELVE RETORNADO EL PRECIO DEL ALQUILER DE UN VIDEOJUEGO
        static float precioAlquiler(int idVideojuego) throws SQLException
         {
            float precio=0;
            String sentenciaObtenerPrecio="SELECT precioAlquiler FROM videojuegos WHERE idVideojuego='"+idVideojuego+"'";
            
            try (Statement st = establecerConexionAutoCommitFalse().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs = st.executeQuery(sentenciaObtenerPrecio)) 
            {
                while(rs.next())
                {
                precio =rs.getFloat(1);
                }
                System.out.println("Precio del alquiler a descontar: "+precio);
                
            }catch (SQLException cn) {cn.printStackTrace();System.out.println("Error al acceder a los datos");}  
                if(establecerConexionAutoCommitFalse()!=null) establecerConexionAutoCommitFalse().close();
                
         return precio;
         }      
        
//METODO DEVUELVE VIDEOJUEGO MARCA VIDEOJUEGO COMO NO ALQUILADO        
        static void devolverVideojuego() throws SQLException
        {
            boolean seguir=false;
            int idVideojuego=0;
            while (!seguir)
            {    
            System.out.println("Introduzca numero de videojuego: ");
            Scanner sc2=new Scanner(System.in);
            idVideojuego=sc2.nextInt();
            
            if(valorEnTabla("videojuegos", idVideojuego)==1) seguir=true;   
                else System.out.println("Numero de videojuego erroneo!!!...");
            }    
            
        String sentenciaSqlalquilado="UPDATE videojuegos SET alquilado = 'N' WHERE idVideojuego= '"+idVideojuego+"' ";

                        try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSqlalquilado)) 
                        {

                            int filas = pst.executeUpdate();
                            System.out.println(filas+ "----Se han modificado " +filas+ " en registro de videojuegos----");

                             if(establecerConexion()!=null) establecerConexion().close();
                        }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());}    
        }
     
//DEVUELVE RETORNADO EL SALDO DE LA CUENTA DE UN CLIENTE       
        static float obtenerSaldoRetornado(int idCliente) throws SQLException
         {
      
            String sentenciaObtenerSaldo="SELECT saldo FROM CLIENTE WHERE idCliente='"+idCliente+"'";
            float saldo=0;
            try (Statement st = establecerConexionAutoCommitFalse().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs = st.executeQuery(sentenciaObtenerSaldo)) 
            {
                while(rs.next())
                {
                saldo =rs.getFloat(1);
                }
                //System.out.println("Saldo actual: "+saldo);
                
            }catch (SQLException cn) {cn.printStackTrace();System.out.println("Error al obtener saldo del cliente");}  
                if(establecerConexionAutoCommitFalse()!=null) establecerConexionAutoCommitFalse().close();
                
         return saldo;
         }    
           
         
//AQUI OBTENGO EL SALDO QUE EL CLIENTE TIENE EN CUENTA PARA SUMARLE POSTERIORMETNE EL NUEVO SALDO ASIGNANDOLO A UNA VARIABLE  
         static void obtenerSaldo(int idCliente) throws SQLException
         {
      
            String sentenciaObtenerSaldo="SELECT saldo FROM CLIENTE WHERE idCliente='"+idCliente+"'";
            
            try (Statement st = establecerConexionAutoCommitFalse().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs = st.executeQuery(sentenciaObtenerSaldo)) 
            {
                while(rs.next())
                {
                sumaSaldo =rs.getFloat(1);
                }
                System.out.println("Saldo actual: "+sumaSaldo);
                
            }catch (SQLException cn) {cn.printStackTrace();System.out.println("Error al obtener saldo del cliente");}  
                if(establecerConexionAutoCommitFalse()!=null) establecerConexionAutoCommitFalse().close();
         }      
         
//INGRESO EL SALDO EN LA CUENTA DEL CLIENTE
         static void ingresarSaldo(int idCliente, float pagoRealizado) throws SQLException
         {    
        
                String sentenciaSqlAniadirSaldo="UPDATE cliente SET saldo=? WHERE idCliente='"+idCliente+"'";
                try (PreparedStatement pst1 = establecerConexionAutoCommitFalse().prepareStatement(sentenciaSqlAniadirSaldo))
                {
                     System.out.println("Suma saldo es: "+sumaSaldo);
                     System.out.println("pago realizado es: "+pagoRealizado);
                    pagoRealizado+=sumaSaldo;
                    pst1.setFloat(1, pagoRealizado);
                    int filas = pst1.executeUpdate();
                    
                    System.out.println(filas+ "----Se han modificado " +filas+ " en registro del cliente----");
                    
                }catch (SQLException cn) {cn.printStackTrace();}  
                     if(establecerConexionAutoCommitFalse()!=null) establecerConexionAutoCommitFalse().close();
            
            }
         
         static void insertMovimientoCaja(int idCliente, float pagoRealizado) throws SQLException
         {
                String sentenciSqlAniadirCaja="INSERT INTO movimientoCaja(idCliente,cantidad,create_at,update_at)VALUES (?,?,now(),null)";
                
                try(PreparedStatement pst1 = establecerConexion().prepareStatement(sentenciSqlAniadirCaja))
                {

                    pst1.setInt(1,idCliente);
                    pst1.setFloat(2, pagoRealizado);
                       
                    int filas = pst1.executeUpdate();
                                
                    System.out.println(filas+ "----Se han modificado " +filas+ " en registros de movimientos de caja----");

                }catch(SQLException cn) {cn.printStackTrace();} 
                    if(establecerConexion()!=null) establecerConexion().close();
         }
         
 //AÑADE SALDO A LA CUENTA SALDO DEL CLIENTE Y AÑADIR A REGISTRO DE CAJA DEL DIA (TRANSACCION)        
         static void transaccionCargaSaldo(int idCliente, float pagoRealizado) throws SQLException
         { 
            try{
                
                
               obtenerSaldo(idCliente);
               
               //no funciona el commit. poner en la conexion setautocommit false 
                
               ingresarSaldo(idCliente, pagoRealizado);
              // establecerConexionAutoCommitFalse().commit();       
              
            
           }catch(SQLException e){
                   //establecerConexionAutoCommitFalse().rollback(); //si alguna sentencia sql falla la exception viene aqui y hace el rollback
                   e.printStackTrace();
           }finally {
                      if(establecerConexionAutoCommitFalse()!=null) 
                      establecerConexionAutoCommitFalse().close();
                    } 
         }
         

//CREA UN NUEVO CLIENTE EN LA BD
        static void nuevoCliente() throws SQLException
        {
        int filas=0;    
            
            System.out.println("Introduce nombre del nuevo cliente: ");
            Scanner sc=new Scanner(System.in);
            String nombrecliente=sc.nextLine();

	    System.out.println("Introduce direccion: ");
            Scanner sc1=new Scanner(System.in);
            String direccion=sc1.nextLine();
            
            java.util.Date fechaTipoUtil = new java.util.Date();
            java.sql.Date fechaAlta = new java.sql.Date(fechaTipoUtil.getTime());
            

	    System.out.println("Introduce telefono: ");
            Scanner sc2=new Scanner(System.in);
            int telefono=sc2.nextInt();

	    System.out.println("Introduce saldo de inicio");
	    Scanner sc3=new Scanner(System.in);
            float saldo=sc3.nextFloat();
            
            String sentenciaSql="INSERT INTO cliente( nombre, direccion, telefono, saldo, create_at, update_at) VALUES (?,?,?,?, now(), null)";
		
                try{

                    try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSql)) 
                    {
                        pst.setString(1,nombrecliente);
                        pst.setString(2,direccion);            
                        pst.setInt(3,telefono);
                        pst.setFloat(4,saldo);
                        filas=pst.executeUpdate();
                        System.out.println("\t------------------------------");
                        System.out.println("\t  Usuario grabado con exito \n");
			System.out.println("\t"+filas+" registros moficados");
                        System.out.println("\t------------------------------");

                    }

                }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());}
                  
            if(establecerConexion()!=null) establecerConexion().close();
        }

//IMPRIME EN PANTALLA UN CLIENTE INDRODUCIDO POR NUMERO DE CLIENTE
        static void imprimirCliente(int id) throws SQLException
        {
         
            String sentenciaSql="SELECT * FROM rentgames.cliente WHERE idCliente='"+id+"' ";

            Statement st = establecerConexion().createStatement();

                try (ResultSet resultado = st.executeQuery(sentenciaSql)) {

                    while(resultado.next())
                    {
                        System.out.println(" Cliente Nº:"+resultado.getInt(1)+" \n Nombre: "+resultado.getString(2)+" \n Direccion:"+ resultado.getString(3)+" \n Telefono: "
                        +resultado.getInt(4)+" \n Saldo: "+ resultado.getFloat(5)+" \n Fecha de alta: "+resultado.getDate(6));          
                    }
                if(establecerConexion()!=null) establecerConexion().close();
                }
                if(st!=null)st.close();
        }

//REALIZA UN INFORME DE LOS INGRESOS Y LA CAJA DEL DIA 
        static void informeCaja() throws SQLException
        {
	float cantidadTotal = 0;
        System.out.println("Introduce el dia del que quieres el informe:(AAAA-MM-DD)");
 	Scanner sc2=new Scanner(System.in);
        Date fechaIntroducida=Date.valueOf(sc2.nextLine());
 
        String sentenciaSql="SELECT * FROM rentgames.movimientoCaja WHERE create_at='"+fechaIntroducida+"' ";

        Statement st = establecerConexion().createStatement();
        
        try (ResultSet resultado = st.executeQuery(sentenciaSql)) {

	    System.out.println("Los movimienos realizados el dia "+fechaIntroducida+" son:");

            while(resultado.next())
            {
                System.out.println("Orden: "+resultado.getInt(1)+" \t Cliente: "+ resultado.getInt(2)+" \t Ingresado: "+
			 resultado.getFloat(3)+" \t Fecha: " +resultado.getDate(4));
	       
		cantidadTotal+=resultado.getFloat(3);
            }

        if(establecerConexion()!=null) establecerConexion().close();
        }
        if(st!=null)st.close();

	System.out.println("Total del dia: "+ fechaIntroducida+"-->"+cantidadTotal);
        }

//IMPRIME EN PANTALLA EL STOCK DE VIDEOJUEGOS
        static void imprimirStock() throws SQLException
        {
        
        String sentenciaSql="SELECT * FROM rentgames.videojuegos";

        Statement st = establecerConexion().createStatement();
        
        try (ResultSet resultado = st.executeQuery(sentenciaSql)) {

            while(resultado.next())
            {
                System.out.println(resultado.getInt(1)+" /Nombre: "+ resultado.getString(2)+" /Plataforma:"+ 
                        resultado.getString(3)+" /Genero: " +resultado.getString(4)+" /Distribuidor: "+
                        resultado.getInt(5)+" /Precio: " +resultado.getFloat(6)+"/Stock "+ resultado.getInt(7));
            }
        if(establecerConexion()!=null) establecerConexion().close();
        }
        if(st!=null)st.close();
        }

//BORRA UN VIDEOJUEGO DE LA BD(AL FINAL NO USE ESTE METODO Y AÑADI UNO CON BATCH)
        static void borrarVideojuego() throws SQLException
        {
            
         int videojuego = 0;   
            
        String sentenciaSql="DELETE FROM videojuegos WHERE idVideojuego='"+videojuego+"'";
        
            try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSql))
 	    {
   
                System.out.println("¿Numero de videojuego que deseas dar de baja del stock?");
                Scanner sc=new Scanner(System.in);
                videojuego=sc.nextInt();

                int filas = pst.executeUpdate();
                System.out.println(filas+ "----Se han modificado " +filas+ " registros----");
            
            }catch (SQLException cn) {
                                    cn.printStackTrace();
                                    System.out.print("Error al acceder al numero de videojuego");
                                    }  
                if(establecerConexion()!=null) establecerConexion().close();
        }
        
//INTRODUCE UN NUEVO VIDEOJUEGO EN LA BD   
        static void nuevoVideojuego() throws SQLException
        {
        int filas=0;    
            
            System.out.println("Introduce nombre del videojuego: ");
            Scanner sc=new Scanner(System.in);
            String titulo=sc.nextLine();

	    System.out.println("Introduce plataforma ");
            Scanner sc1=new Scanner(System.in);
            String plataforma=sc1.nextLine();
            
	    System.out.println("Introduce genero: ");
            Scanner sc2=new Scanner(System.in);
            String genero=sc2.nextLine();
            
            System.out.println("Introduce distribuidor: ");
            Scanner sc3=new Scanner(System.in);
            int numDistribuidor=sc3.nextInt();

	    System.out.println("Introduce precio por el alquiler");
	    Scanner sc4=new Scanner(System.in);
            float precio=sc4.nextFloat();
            
            System.out.println("Introduce distribuidor: ");
            Scanner sc5=new Scanner(System.in);
            int stock=sc5.nextInt();
            
            String sentenciaSql="INSERT INTO videojuegos( titulo, plataforma, genero, idDistribuidor, precioAlquiler, stock,create_at)VALUES (?,?,?,?,?,?, now())";
	
                try{

                    try (PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSql)) 
                    {
                        pst.setString(1,titulo);
                        pst.setString(2,plataforma);
                        pst.setString(3, genero);
                        pst.setInt(4,numDistribuidor);
                        pst.setFloat(5,precio);
                        pst.setInt(6,stock);
                        filas=pst.executeUpdate();
                        System.out.println("\t------------------------------");
                        System.out.println("\t  videojuego grabado con exito \n");
			System.out.println("\t"+filas+" registros moficados");
                        System.out.println("\t------------------------------");

                    }

                }catch (SQLException e){System.out.println("Error de acceso"+ e.getMessage());}
                  
            if(establecerConexion()!=null) establecerConexion().close();
        }
        
//ESTE METODO CARGA UN VECTOR CON LOS ID DE LOS JUEGOS QUE QUIERO DAR DE BAJA CON EL SIGUIENTE METODO
        static int[] cargaVectorVideojuegos()
        {

            boolean seguir=true;
            int[]numVideojuegos=new int[5];

            while(seguir)
            {	

                    for(int i=0;i<numVideojuegos.length; ++i)
                    {
                        String respuestaCadena;
                        Scanner sc=new Scanner(System.in);
                        System.out.println("Introduce el num de videojuego que quieres eliminar del stock("+i+" de 5): ");
                        int respuesta=sc.nextInt();

                        numVideojuegos[i]=respuesta;
                            String continuar="s";
                            
                                while(!continuar.equals("S") && !continuar.equals("N"))
                                {
                                Scanner sc1=new Scanner(System.in);
                                System.out.println("Quieres seguir introduciento identificador de videojuegos??(S/N): ");
                                continuar=sc1.nextLine();
                                }
                                if(continuar.equals("N")){ seguir=false;                                                      
                                                                break;}		
                    }
            }
            return numVideojuegos;

        }
        
//METODO QUE ELIMINA LOS VIDEOJUEGOS DEL STOCK INTRODUCIENDOLOS EN UN VECTOR PARA HACER BATCH DE TODOS A LA VEZ    
        static void eliminaVideojuego(int []vector1) throws SQLException
        {

            String sentenciaSql="DELETE FROM videojuegos WHERE idVideojuego=?";

            try
            {
                try(PreparedStatement pst = establecerConexion().prepareStatement(sentenciaSql))
                {
                    for(int i = 0; i<vector1.length;++i)
                    {
                    pst.setInt(1, vector1[i]);
                    pst.addBatch();
                    }

                    pst.clearParameters();
                    int[] resultado = pst.executeBatch();
                    System.out.println("Se han modificado estos registros: " + Arrays.toString(resultado));

                }
            }catch(SQLException e){e.getSQLState();}
            if(establecerConexion()!=null) establecerConexion().close();
        }
   
//CUENTA LAS FILAS DE LA TABLA DADA COMO PARAMETRO        
        static int cuentaFilas(String tabla) throws SQLException
        {
            int numeroFilas=0;

            final String sentenciaSql = ("SELECT * FROM  "+tabla+" ");
            try(Statement st=establecerConexion().createStatement(); 
                    ResultSet resultadoConsulta = st.executeQuery(sentenciaSql))
            {               
                while(resultadoConsulta.next()){ ++numeroFilas; }

            }catch(SQLException e){e.getSQLState();}
             
        return numeroFilas;
        }
 
//DEVUELVE EL ULTIMO VALOR ID DE LA TABLA
        static int ultimoIdTabla(String tabla) throws SQLException
{
	int i=0,j=0, ultimoValor=0;

	String sentenciaSql=("SELECT * FROM "+tabla+" ");

		try(Statement st= establecerConexion().createStatement();
			ResultSet rs1=st.executeQuery(sentenciaSql))
		{
			while(rs1.next()){++i;}
                        System.out.println("numero de filas son "+i);
       	
		}catch(SQLException e){System.out.println("Error de conexion 1: "+ e.getMessage());}
		if(establecerConexion()!=null) establecerConexion().close();
                
                try(Statement st= establecerConexion().createStatement();
			ResultSet rs2=st.executeQuery(sentenciaSql))
		{
                        while(rs2.next())
			{
			++j;
			if(j==i) ultimoValor=rs2.getInt(1);
			}
                
                }catch(SQLException e){System.out.println("Error de conexion 2: "+ e.getMessage());}
		if(establecerConexion()!=null) establecerConexion().close();
                
return ultimoValor;
}       
  
  
//DEVUELVE UN 1 SI EL VALOR INTRODUCIDO ESTA EN LA TABLA  
  
        static int valorEnTabla(String tabla, int id) throws SQLException
{
int i = 0;
int respuesta=0;

String sentenciaSql=("SELECT * FROM "+ tabla+" ");

		try(Statement st= establecerConexion().createStatement();
			ResultSet rs=st.executeQuery(sentenciaSql))
		{
			while(rs.next()){++i;}
                        
                }catch(SQLException e){System.out.println("Error de conexion: "+ e.getMessage());}
		if(establecerConexion()!=null) establecerConexion().close();
                
                int[]ids=new int[i];
                
                i=0;
		try(Statement st= establecerConexion().createStatement();
			ResultSet rs=st.executeQuery(sentenciaSql))
		{	
			while(rs.next())
			{
				ids[i]=rs.getInt(1);
				++i;
			}
                }catch(SQLException e){System.out.println("Error de conexion: "+ e.getMessage());}
		if(establecerConexion()!=null) establecerConexion().close();
		i=0;
                
			for(i=0; i<ids.length;++i)
			{
                            if(ids[i]==id) 
                            {
                                System.out.println("el valor esta en el sistema");
                                respuesta=1; 
                            }
                            
			}
 
return respuesta;
}

    
  
  
}
       
