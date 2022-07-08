package principal;

import productos.Productos;
import persona.Persona;
import utilidades.conexion;
import ventas.ventas;
import ventas.DetalleVentas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
		String documetno1 = sc.nextLine();
		System.out.println("\n");
		System.out.println("Ingrese su Password: ");
		String pass1 = sc.nextLine();
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.createStatement();
			
			sql = "SELECT idPersona, Documento, Categoria FROM persona order by idPersona ;";
			
			rs = statement.executeQuery(sql);
			
			String Categoria1 = rs.getString("Categoria");
			
			System.out.println(Categoria1);
			
			//stmt = conexion.prepareStatement();
			
		}catch (SQLException sqle){
	        System.out.println("SQLState: "+ sqle.getSQLState());
	        System.out.println("SQLErrorCode: " + sqle.getErrorCode());
	        sqle.printStackTrace();
	    }catch (Exception e){
	        e.printStackTrace();
	    }
 
	} //cierra bloque main

} //cierra  clase principal
