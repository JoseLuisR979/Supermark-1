package persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Persona {

		//Atributos
	private String idPersona;
	private String Documento;
	private String TipoDocumento;
	private String Nombre;
	private String Apellido;
	private String Direccion;
	private String Telefono;
	private String Categoria;
	private String Pass;
	
	/////Constructores
	
	public Persona() {}
	
	public Persona(String idPersona, String documento, String tipoDocumento, String nombre, String apellido,
			String direccion, String telefono, String categoria, String pass) 
	{
		
		this.idPersona = idPersona;
		this.Documento = documento;
		this.TipoDocumento = tipoDocumento;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Direccion = direccion;
		this.Telefono = telefono;
		this.Categoria = categoria;
		this.Pass = pass;
	}

	
	/////Getters & Setters
	
	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getDocumento() {
		return Documento;
	}

	public void setDocumento(String documento) {
		Documento = documento;
	}

	public String getTipoDocumento() {
		return TipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}
	
	//METODOS----------------------------------------------
	
	
	//---------CREAR PERSONA -----------------------------------
	public void crearPersona (Connection conexion)
	{
			Scanner sc = new Scanner(System.in);
			System.out.println("Datos de Persona");
			System.out.println("Nº de Documento: ");
			String Documento = sc.nextLine();
			System.out.println("Tipo de Documento (DNI, LE, etc): ");
			String TipoDocumento = sc.nextLine();
			System.out.println("Nombre: ");
			String Nombre = sc.nextLine();
			System.out.println("Apellido: ");
			String Apellido = sc.nextLine();
			System.out.println("Direccion: ");
			String Direccion = sc.nextLine();
			System.out.println("Numero de Telefono: ");
			String Telefono = sc.nextLine();
			System.out.println("Ingrese un Password de 8 caracteres:");
			String Pass = sc.nextLine();
			
			String Categoria="Empleado";
			
			Statement statement = null;
			String sql;
			ResultSet rs;
			PreparedStatement stmt;
			
			try 
				{
				//busca el ultimo registro en la tabla
				statement = conexion.createStatement();
				sql = "SELECT idPersona FROM persona order by idPersona DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idPersona = 0;
				while (rs.next()) 
				{
					idPersona = rs.getInt("idPersona");
					
				}
				
				stmt = conexion.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?,?,?,?,?)");
					stmt.setInt(1,idPersona + 1);
					stmt.setString(2,Documento);
					stmt.setString(3,TipoDocumento);
					stmt.setString(4,Nombre);
					stmt.setString(5,Apellido);
					stmt.setString(6,Direccion);
					stmt.setString(7,Telefono);
					stmt.setString(8,Categoria);
					stmt.setString(9,Pass);
					
				int response = stmt.executeUpdate();
					if (response > 0) 
					{
						System.out.println("Se dio de alta correctamente");
						
					}
				}catch (SQLException sqle){
		            System.out.println("SQLState: "+ sqle.getSQLState());
		            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
		            sqle.printStackTrace();
								 
		        }catch (Exception e){
		            e.printStackTrace();
			
		        	}
	
}
	
	//---------MODIFICAR PERSONA -------------------------------
	
	public void modificarPersona(Connection conexion) 
	{
		
		Scanner sc = new Scanner(System.in);
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.createStatement();
			
			sql = "SELECT idPersona, Nombre, Apellido, Documento FROM persona order by idPersona ;";
			
			rs = statement.executeQuery(sql);
			
			System.out.println("Seleccione Cliente");
			
			while(rs.next()) 
				{
				
				int idPersona = rs.getInt("idPersona");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				//0String tipodocumento = rs.getString("TipoDocumento");
				System.out.println(idPersona + " - " + apellido + " " + nombre + " " + documento );
				}
			System.out.println("Cancelar seleccione presione 0");
			int cliente  = sc.nextInt();
			sc.nextLine();
			
			if(cliente!=0) {
				System.out.println("#######################");
				System.out.println("Datos del Cliente");
				System.out.println("#######################");
				System.out.println("Apellido :");
				String apellido = sc.nextLine(); 
				System.out.println("Nombre :");
				String nombre = sc.nextLine();
				System.out.println("Documento :");
				String documento = sc.nextLine();
				System.out.println("Tipo de Documento");
				String tipodocumento = sc.nextLine();
				System.out.println("Direccion :");
				String direccion = sc.nextLine();
				System.out.println("Telefono :");
				String telefono = sc.nextLine();
				
				statement = conexion.createStatement();
				sql = "SELECT idPersona FROM Persona WHERE idPersona = "+cliente+";";
				rs = statement.executeQuery(sql);
				int idpersona = 0;
				while(rs.next())  
				{
					idpersona = rs.getInt("idPersona");
				}	
				
				System.out.println("Datos ingresados, modificando...");
				
				stmt = conexion.prepareStatement("UPDATE persona SET"
						+ " Documento = ?,"
						+ " TipoDocumento = ?,"
						+ " Nombre = ?,"
						+ " Apellido = ?,"
						+ " Direccion = ?,"
						+ " Telefono = ?" 
						+ " WHERE idPersona = ?; " );
	        	stmt.setString(1,documento);
	        	stmt.setString(2,tipodocumento);
	        	stmt.setString(3,nombre);
	        	stmt.setString(4,apellido);
	        	stmt.setString(5,direccion);
	        	stmt.setString(6,telefono);
	        	stmt.setInt(7,cliente);
	        	
	        	
	        	int response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("se actualizo persona " + idpersona + " correctamente");
	        		
	        	}	
			}
		
		
		
	}catch (SQLException sqle){
        System.out.println("SQLState: "+ sqle.getSQLState());
        System.out.println("SQLErrorCode: " + sqle.getErrorCode());
        sqle.printStackTrace();
    }catch (Exception e){
        e.printStackTrace();
    }






}

	//-----------MODIFICAR PASSWORD----------------------------
	
	public void modificarPass(Connection conexion) 
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Modificar Password");
		System.out.println("------------------");
		System.out.println("Ingrese su Nº de Documento:");
		
		String documento = sc.nextLine();
		System.out.println("Ingrese su Password Actual: ");
		
		String pass1 = sc.nextLine();
		
	
		
		
		Statement statement = null;
		
		String sql;
		
		ResultSet rs;
		
		PreparedStatement stmt;
		
		try 
		{
		//busca el registro en la tabla que coincida con el documento
		statement = conexion.createStatement();
		
		
		
		System.out.println(documento);
		
		sql = "SELECT idPersona, Pass FROM Persona WHERE Documento = "+documento+";";
		
		rs = statement.executeQuery(sql);

		int idpersona = 0;
		String pass3 = null;
		while(rs.next())  
		{
			idpersona = rs.getInt("idPersona");
			pass3 = rs.getString("Pass");
		}	
		
			
		
		if (pass1.equalsIgnoreCase(pass3)  ) 
		{
			System.out.println("#######################");
			System.out.println("INGRESE NUEVO PASSWORD");
			System.out.println("#######################");
			
			String pass2 = sc.nextLine(); 
			
			//System.out.println("IdPersona: " + idpersona);
			//System.out.println("Pass2: " + pass2);
			
			
			
			stmt = conexion.prepareStatement("UPDATE persona SET PASS = "+pass2+" where idPersona = "+idpersona+"; ");
			System.out.println("Pass2: " + pass2);
			int response = stmt.executeUpdate();
			
        	if(response>0) 
        	{
        		System.out.println("\n\n\n\n\n\n");
        		System.out.println("Se actualizo su password correctamente");
        		System.out.println("--------------------------------------");
        		System.out.println("\n\n");
        		
        		
        	}	
		
	
	
	
			}else {
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Su pasword no coincide consulte con el administrador");
				System.out.println("----------------------------------------------------");
				System.out.println("\n\n");
				
				
			}
		
			}catch (SQLException sqle){
				System.out.println("SQLState: "+ sqle.getSQLState());
				System.out.println("SQLErrorCode: " + sqle.getErrorCode());
				sqle.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
}

			
			
			
			
			
	}

	
	//------------ELIMINAR PERSONA -----------------------------
	
	public void eliminarPersona(Connection conexion)
	{
		Scanner sc = new Scanner(System.in);
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.createStatement();
			
			sql = "SELECT idPersona, Nombre, Apellido, Documento FROM persona order by idPersona ;";
			
			rs = statement.executeQuery(sql);
			
			System.out.println("Seleccione Cliente");
			
			while(rs.next()) 
				{
				
				int idPersona = rs.getInt("idPersona");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				
				System.out.println(idPersona + " - " + apellido + " " + nombre + " " + documento );
				}
			System.out.println("Cancelar seleccione presione 0");
			int cliente  = sc.nextInt();
			sc.nextLine();
			
			if(cliente!=0) {
				
				statement = conexion.createStatement();
				sql = "SELECT idPersona FROM Persona WHERE idPersona = "+cliente+";";
				rs = statement.executeQuery(sql);
				int idpersona = 0;
				while(rs.next())  
				{
					idpersona = rs.getInt("idPersona");
				}	
				
				System.out.println("Datos ingresados, modificando...");
				
				stmt = conexion.prepareStatement("DELETE FROM persona WHERE idPersona = "+cliente+";");
	        	
	        	
	        	
	        	int response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("Se elimino el registro " + idpersona+ " correctamente");
	        		
	        	}	
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





		
		
	







