package Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Venta {
	private String fecha;
	private double total;
	private String observacion;
	private boolean estado;
	
	public Venta() {
	}
	public Venta(String fecha, double total, boolean estado) {
		super();
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void crearVenta(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		PreparedStatement stmt = null;
		Statement statement = null;
		String sql;
		ResultSet rs;
		try {
		
			System.out.println("Cliente");
			statement = conexion.createStatement();
			sql = "SELECT idcliente,Nombre,Apellido,Documento "
					+ "FROM cliente AS c INNER JOIN persona AS per ON c.idpersona = per.idpersona "
					+ "order by idcliente;";
			rs = statement.executeQuery(sql);
			System.out.println("Seleccione Cliente");
			while(rs.next()) 
			{
				int idcliente = rs.getInt("idcliente");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				System.out.println(idcliente + " - " + apellido + " " + nombre + " " + documento);
			}
			int cliente  = sc.nextInt();
			sc.nextLine();
			

			System.out.println("Producto");
			ArrayList<DetalleVenta> listaProductos = new ArrayList<DetalleVenta>();
			int option = 0;
			do{
				statement = conexion.createStatement();
				sql = "SELECT idproducto,Nombre,Precio FROM producto order by idproducto;";
				rs = statement.executeQuery(sql);
				while(rs.next()) 
				{
					System.out.print(rs.getString("Nombre"));
					System.out.print(rs.getString("Precio"));
					
				{
					precio = rsp.getDouble("Precio");
				}
				DetalleVenta detalleVenta = new DetalleVenta(cantidad, precio);
				listaProductos.add(detalleVenta);
			
			
			try {
				statement = conexion.createStatement();
				sql = "SELECT idventa FROM venta order by idventa DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idVenta = 0;
				while(rs.next()) 
				{
					idVenta = rs.getInt("idventa");
				}
				
				String fecha = "30-06-2022";
				double total = 0;
				for(int i=0;i<listaProductos.size();i++) {
					total = total + (getPrecioUnitario(i)*.getCantidad());
				}
				String observacion = "";
				stmt = conexion.prepareStatement("INSERT INTO venta PRICE (?,?,?,?,?,?)");
				stmt.setInt(1,idVenta+1);
				stmt.setInt(2,cliente);
				stmt.setString(3,fecha);
	        	stmt.setDouble(4,total);
	        	int response = stmt.executeUpdate();
	        	if (response > 0)
	                System.out.println("Venta Insertada correctamente");
	        	
	        	statement = conexion.createStatement();
				sql = "SELECT iddetalle_venta FROM detalle_venta order by iddetalle_venta DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idDetalleVenta = 0;
				while(rs.next()) 
				{
					idDetalleVenta = rs.getInt("iddetalle_venta");
				}
	        	
				for(int i=0;i<listaProductos.size();i++) 
				{
					stmt = conexion.prepareStatement("INSERT INTO detalle_venta PRICE (?,?,?,?,?)");
					stmt.setInt(1,idDetalleVenta+1);
					stmt.setInt(2,idVenta+1);
		        	stmt.setInt(3,listaProductos.get(i).getCantidad());
		        	response = stmt.executeUpdate();
		        	if (response > 0)
		                System.out.println("DetalleVenta Insertada correctamente");
		        	idDetalleVenta = idDetalleVenta + 1;
				}
				
				for(int i=0;i<listaProductos.size();i++) 
				{
					int producto = 0;
					int stock = 0;
					statement = conexion.createStatement();
					{
						producto= rs.getInt("idproducto");
						stock = rs.getInt("Stock");
					}
					
					stmt = conexion.prepareStatement("UPDATE producto SET Stock=? WHERE idproducto=?");
					stmt.setInt(1,stockActual);
					stmt.setInt(2,producto);
					response = stmt.executeUpdate(); 
		        	if (response > 0)
		                System.out.println("Stock correctament");
				}
			 }catch (SQLException sqle){
		            System.out.println("SQLState: "+ sqle.getSQLState());
		            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
		            sqle.printStackTrace();
		     }catch (Exception e){
		            e.printStackTrace();
		     }
		}catch (SQLException sqle){
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();
	     }catch (Exception e){
	            e.printStackTrace();
	     }
	}
	
	public void anularVenta(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		PreparedStatement stmt = null;
		Statement statement = null;
		String sql;
		ResultSet rs;
		try {
			System.out.println("Ventas");
			statement = conexion.createStatement();
			sql = "SELECT idventa,Fecha,per.Nombre,per.Documento,Total"
					+ "FROM venta AS v INNER JOIN cliente AS c ON v.idcliente = c.idcliente "
					+ "INNER JOIN persona AS per ON per.id = c.idpersona "
					+ "order by idventa;";
			rs = statement.executeQuery(sql);
			System.out.println("Seleccione Venta que quiera ANULAR");
			while(rs.next()) 
			{
				int idventa = rs.getInt("idventa");
				String fecha = rs.getString("Fecha");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				String total = rs.getString("Total");
				System.out.println(idventa + " - " + fecha + " " + nombre + " " + documento + " " + total);
			}
			int venta  = sc.nextInt();
			sc.nextLine();
			
			int iddetalle_venta = 0;
			int idproducto = 0;
			int cantidad = 0;
			statement = conexion.createStatement();
			sql = "SELECT idproducto,cantidad FROM detalle_venta WHERE idventa = "+venta;
			rs = statement.executeQuery(sql);
			while(rs.next()) 
			{
				idproducto = rs.getInt("idproducto");
				cantidad = rs.getInt("cantidad");
			}
			
			int producto = 0;
			int stock = 0;
			statement = conexion.createStatement();
			sql = "SELECT idproducto,Stock FROM producto WHERE idproducto = "+idproducto;
			rs = statement.executeQuery(sql);
			while(rs.next()) 
			{
				producto= rs.getInt("idproducto");
				stock = rs.getInt("Stock");
			}
			stmt = conexion.prepareStatement("UPDATE producto SET Stock=? WHERE idproducto=?");
			int stockActual = stock + cantidad;
			stmt.setInt(1,stockActual);
			stmt.setInt(2,producto);
			int response = stmt.executeUpdate(); 
        	if (response > 0)
                System.out.println("Stock correctamente");
        	
		}catch (SQLException sqle){
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();
	     }catch (Exception e){
	            e.printStackTrace();
	     }
	}
	
	public void verClienteVenta(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		PreparedStatement stmt = null;
		Statement statement = null;
		String sql;
		ResultSet rs;
		System.out.println("Listado de Cliente que Compraron");
		try {
			statement = conexion.createStatement();
			sql = "SELECT idventa,Fecha,per.Nombre,per.Documento,Total "
					+ "FROM venta AS v INNER JOIN cliente AS c ON v.idcliente = c.idcliente "
					+ "INNER JOIN persona AS per ON per.idpersona = c.idpersona "
					+ "order by idventa;";
			rs = statement.executeQuery(sql);
			while(rs.next()) 
			{
				int idventa = rs.getInt("idventa");
				String fecha = rs.getString("Fecha");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				String total = rs.getString("Total");
				System.out.println(idventa + " - " + fecha + " " + nombre + " " + documento + " " + total);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verProductoUsuario(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		PreparedStatement stmt = null;
		Statement statement = null;
		String sql;
		ResultSet rs;
		try {
			System.out.println("Seleccione Cliente");
			statement = conexion.createStatement();
			sql = "SELECT idcliente,Nombre,Apellido,Documento "
					+ "FROM cliente AS c INNER JOIN persona AS per ON c.idpersona = per.idpersona "
					+ "order by idcliente;";
			rs = statement.executeQuery(sql);
			while(rs.next()) 
			{
				int idcliente = rs.getInt("idcliente");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				System.out.println(idcliente + " - " + apellido + " " + nombre + " " + documento);
			}
			int cliente  = sc.nextInt();
			sc.nextLine();
		
			System.out.println("Seleccione Venta");
			statement = conexion.createStatement();
			sql = "SELECT idventa,Fecha,per.Nombre,per.Documento,Total "
					+ "FROM venta AS v INNER JOIN cliente AS c ON v.idcliente = c.idcliente "
					+ "INNER JOIN persona AS per ON per.idpersona = c.idpersona "
					+ "WHERE v.idcliente = "+cliente
					+ " order by idventa;";
			rs = statement.executeQuery(sql);
			System.out.println("Seleccione Venta que desea ver Productos");
			while(rs.next()) 
			{
				int idventa = rs.getInt("idventa");
				String fecha = rs.getString("Fecha");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				String total = rs.getString("Total");
				System.out.println(idventa + " - " + fecha + " " + nombre + " " + documento + " " + total);
			}
			int venta  = sc.nextInt();
			sc.nextLine();
			
			int iddetalle_venta = 0;
			statement = conexion.createStatement();
			sql = "SELECT dv.idproducto,p.nombre,cantidad,precio FROM detalle_venta AS dv "
				+ "INNER JOIN producto AS p ON p.idproducto = dv.idproducto "
				+ "WHERE idventa = "+venta;
			rs = statement.executeQuery(sql);
			while(rs.next()) 
			{
				int idproducto = rs.getInt("idproducto");
				String nombre = rs.getString("nombre");
				int cantidad = rs.getInt("cantidad");
				double precio = rs.getDouble("precio");
				System.out.println(idproducto + " - " + nombre + " " + cantidad + " " + precio);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


