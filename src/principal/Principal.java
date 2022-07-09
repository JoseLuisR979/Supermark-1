package principal;

import productos.Productos;
import persona.Persona;
import utilidades.conexion;
import ventas.Ventas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Principal {

	public static void main(String[] args) {
		
		
		
		conexion conexion1 = new conexion("super","Super1213","supermark");
		System.out.println(conexion1.conectar());
		//conexion.conectar();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("#################");
		System.out.println("     INGRESAR    ");
		System.out.println("#################");
		System.out.println("\n\n");
		System.out.println("Ingrese su numero de Documento: ");
		String documento1 = sc.nextLine();
		System.out.println("\n");
		System.out.println("Ingrese su Password: ");
		int pass1 = sc.nextInt();
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.getConnection().createStatement();
			
			sql = "SELECT idPersona, Documento, Categoria, Pass FROM persona WHERE Documento = "+documento1+" ;";
			
			rs = statement.executeQuery(sql);
			
			int docu = 0;
			
			String cat = null;
			
			int pass = 0;
			
			while(rs.next())  
			{
				docu = rs.getInt("Documento");
				cat = rs.getString("Categoria");
				pass = rs.getInt("Pass");
			}	
			
			if (pass == pass1) {
						
			//System.out.println(cat);
			//System.out.println(pass);
			
			//String cat2="Empleado";
			
			//if (cat.equals(cat2) ) {
				
				int option = 1;
				
				do {
					System.out.println("###############");
					System.out.println("MENU DE SISTEMA"); // Para Empleados
					System.out.println("###############");
					System.out.println("1 - Productos");
					System.out.println("2 - Vender");
					System.out.println("3 - Clientes");
					System.out.println("0 - SALIR");
					option = sc.nextInt();
					sc.nextLine();
					
					switch (option) {
						
					case 1 :{
						System.out.println("-------------------------");
						System.out.println("      MENU PRODUCTOS     ");
						System.out.println("------------------------ ");
						System.out.println("1 - Crear Producto");
						System.out.println("2 - Actualizar Producto");
						System.out.println("3 - Eliminar Producto");
						System.out.println("4 - Listado de Productos");
						System.out.println("0 - SALIR ");
						int optionProducto = sc.nextInt();
						sc.nextLine();
						
						switch (optionProducto) 
						{
						
						case 1:{
								Productos producto = new Productos();
								producto.insertarProducto(conexion.getConnection() );
								break;
								}
						case 2:{
								Productos producto = new Productos();
								producto.modificarProductos(conexion.getConnection());
								break;
								}
						case 3:{
								Productos producto = new Productos();
								producto.eliminarProducto(conexion.getConnection());
								break;
								}
						case 4:{
								Productos producto = new Productos();
								producto.listarProductos(conexion.getConnection());
								break;
								}
						}
						
						
						
						}break;
					
					case 2:{
						System.out.println("-------------------------");
						System.out.println("       MENU VENTAS       ");
						System.out.println("-------------------------");
						System.out.println("1 - Crear Venta");
						System.out.println("2 - Anular Venta");
						System.out.println("3 - Ver todos los usuarios que realizaron una compra");
						System.out.println("4 - Ver listado de productos seleccionados por el Cliente");
						System.out.println("0 - SALIR ");
						int optionVentas = sc.nextInt();
						sc.nextLine();
						
						switch (optionVentas) { 
						
						case 1:{
								Ventas venta = new Ventas();
								venta.crearVentas(conexion.getConnection());
								break;
								}
						case 2:{
							System.out.println("------------------");	
							System.out.println("Menu en desarrollo");
							System.out.println("------------------");
							break;
							
								}
						case 3:{
							System.out.println("------------------");
							System.out.println("Menu en desarrollo");
							System.out.println("------------------");
							
							break;
							
								}
						case 4:{
							System.out.println("------------------");
							System.out.println("Menu en desarrollo");
							System.out.println("------------------");
							break;
							
								}
						
						
						}
						
						
					} break;
					
					case 3:  {
						System.out.println("-------------------------------");
						System.out.println("         MENU USUARIOS         ");
						System.out.println("-------------------------------");
						System.out.println("1 - Alta Usuario");
						System.out.println("2 - Modificar Datos de Usuario");
						System.out.println("3 - Modificar Passwords");
						System.out.println("4 - Eliminar Usuario");
						System.out.println("0 - SALIR ");
						int optionCliente = sc.nextInt();
						sc.nextLine();
							switch (optionCliente) { 
							
							case 1:{
								Persona persona = new Persona();
								persona.crearPersona(conexion.getConnection());
								break;
									}
							case 2: {
								Persona persona = new Persona();
								persona.modificarPersona(conexion.getConnection());
								break;
									}
							case 3: {
								
								Persona persona = new Persona();
								persona.modificarPass(conexion.getConnection());
								break;
									}
							case 4:{
								Persona persona = new Persona();
								persona.eliminarPersona(conexion.getConnection());
								break;
									}
							
								}
											
							
							
							}break;
			
				}	
					
					System.out.println("##################");
					System.out.println("¿Cierra el Sistema?");
					System.out.println("0 - SI");
					System.out.println("1 - NO");
					System.out.println("##################");
					option = sc.nextInt();
					sc.nextLine();
				
				
				}while(option==1);
				sc.close();
				System.out.println(" -----------------------");				
				System.out.println("     Fin del Programa   ");
				System.out.println(" -----------------------");	
					
				
					
		}else {
			System.out.println("\n");
			System.out.println(" ---------------------------------");
			System.out.println("     Los datos son incorrectos    ");
			System.out.println("         Vuelva a intenar         ");
			System.out.println(" ---------------------------------");
			
			TimeUnit.SECONDS.sleep(1);
			
			
			int opt = 1;
			do {
				
				System.out.println("1 - Volver a Ingresar");
				System.out.println("2 - Salir");
				int opt2 =0;
				opt2 = sc.nextInt();
				sc.nextLine();
				
				
				switch (opt2){
				
					case 1:{
							Principal.main(args);
							break;
							}
										
					}break;
				
				
			} while (opt==1);
			
			sc.close();
			System.out.println(" -----------------------");				
			System.out.println("     Fin del Programa   ");
			System.out.println(" -----------------------");	
		}
		
		
		
		
		
	}catch (SQLException sqle){
	        System.out.println("SQLState: "+ sqle.getSQLState());
	        System.out.println("SQLErrorCode: " + sqle.getErrorCode());
	        sqle.printStackTrace();
	    }catch (Exception e){
	        e.printStackTrace();
	    }
 
		
		
	

} 
	}
