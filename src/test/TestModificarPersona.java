package test;

import utilidades.conexion;
import persona.Persona;

public class TestModificarPersona {
	

	public static void main(String[] args) {
		conexion cnn = new conexion ("super","Super1213","Supermark");
		System.out.println(cnn.conectar());
		
		
	Persona persona= new Persona();
	
	persona.modificarPersona(conexion.getConnection());
	
	}

}
