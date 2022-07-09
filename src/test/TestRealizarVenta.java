package test;

import ventas.Ventas;
import utilidades.conexion;



public class TestRealizarVenta {

	public static void main(String[] args) {
		
		conexion cnn = new conexion ("super","Super1213","Supermark");
		System.out.println(cnn.conectar());
		
		
		
		Ventas ventas = new Ventas();
		ventas.crearVentas(conexion.getConnection());
		
		
		
	}

}
