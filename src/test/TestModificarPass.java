package test;

import persona.Persona;
import utilidades.conexion;

public class TestModificarPass {

	public static void main(String[] args) {
		conexion cnn = new conexion ("super","Super1213","Supermark");
		System.out.println(cnn.conectar());
		
		
	Persona persona= new Persona();
	
	persona.modificarPass(conexion.getConnection());
	
	


}
	}
