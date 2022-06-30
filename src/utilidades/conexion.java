package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class conexion {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String DB_URL;
	private String USER;
	private String PASS;
	private String baseDato;
	Connection connection;
	
	public conexion(String USER, String PASS, String Supermark) {
		this.USER = "super";
		this.PASS = "Super1213";
		this.baseDato = "Supermark";
		this.DB_URL = "jdbc:mysql://localhost:3306/"+Supermark;
	}
	
	public String conectar() 
	{
		String respuesta="";
		
		try {
            Class.forName(JDBC_DRIVER);
            this.connection = DriverManager.getConnection(this.DB_URL,this.USER,this.PASS);
            if(this.connection != null) 
            {
            	respuesta = "Conectado";
            }
            else 
            {
            	respuesta = "No conectado";
            }
        }catch (ClassNotFoundException e) {
			respuesta="ocurre una ClassNotFoundException : "+e.getMessage();
		}
		catch (SQLSyntaxErrorException e) {
			respuesta="ocurre una SQLSyntaxErrorException: "+e.getMessage()+"\n";
			respuesta+="Verifique que se este usando la base de datos y tablas correctas...";
		}
		catch (CommunicationsException e) {
			respuesta="ocurre una CommunicationsException: "+e.getMessage()+"\n";
			respuesta+="Verifique que la base de datos fue iniciada...";
		}
		catch (SQLException e) {
			respuesta="ocurre una SQLException: "+e.getMessage()+"\n";
			respuesta+="Este es un problema general de SQL, verifique con el administrador";
		}
		
		return respuesta;
	}
	
	public Connection getConnection(){
		return this.connection;
	}
	
	public void desconectar(){
		this.connection = null;
	}
}
