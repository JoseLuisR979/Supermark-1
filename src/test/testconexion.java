package test;


import utilidades.conexion;

public class Testconexion {

	public static void main(String[] args) {
		conexion cnn = new conexion("super","Super1213","Supermark");
		
		System.out.println(cnn.conectar());
		cnn.desconectar();

		
	}

	
	
}
