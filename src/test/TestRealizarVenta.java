package test;

import ventas.ventas;
import productos.Productos;
import utilidades.conexion;



public class TestRealizarVenta {

	public static void main(String[] args) {
		
		conexion cnn = new conexion ("super","Super1213","Supermark");
		System.out.println(cnn.conectar());
		
		
		
		ventas ventas = new ventas();
		ventas.crearVentas(conexion.getConnection());
		
		
		
	}

}
