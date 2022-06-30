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
	
	//Constructores
	
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

	
	//Getters & Setters
	
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
	
	//CRUDD
	
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
			
			String Categoria="Cliente";
			
			Statement statement = null;
			String sql;
			ResultSet rs;
			PreparedStatement stmt;
			
			try 
				{
				
				statement = conexion.createStatement();
				sql = "SELECT idPersona FROM persona order by idPersona DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idPersona = 0;
				while (rs.next()) 
				{
					idPersona = rs.getInt("idPersona");
					
				}
				
				stmt = conexion.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?,?,?,?)");
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
	
}}
