package test;


import productos.Productos;
import utilidades.conexion;

public class TestCargarProducto {

	public static void main(String[] args) {
		
		
		conexion cnn = new conexion ("super","Super1213","Supermark");
		System.out.println(cnn.conectar());
		
		
		Productos producto = new Productos () ;
		producto.insertarProducto(conexion.getConnection());
			
		

	}

}
