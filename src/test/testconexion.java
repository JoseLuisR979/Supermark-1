package test;


import utilidades.conexion;

public class testconexion {

	public static void main(String[] args) {
		conexion cnn = new conexion("root","Navidad$25","supermark");
		
		System.out.println(cnn.conectar());
		cnn.desconectar();

	}

}
