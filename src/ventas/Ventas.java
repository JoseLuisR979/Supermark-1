package ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class Ventas 
{
	
	//Atributos
	
	private double MontoDeCompra;
	private int Recibo;
	private String Observaciones;
	private String fecha;
	private double total;
	private String observacion;
	private boolean estado;
	//Constructores
	
	

	public Ventas() {	}

	


	public Ventas(double montoDeCompra, int recibo, String observaciones, String fecha, double total,
			String observacion, boolean estado) {
		super();
		MontoDeCompra = montoDeCompra;
		Recibo = recibo;
		Observaciones = observaciones;
		this.fecha = fecha;
		this.total = total;
		this.observacion = observacion;
		this.estado = estado;
			}
	
	
	//GETTERS & SETTERS

	public double getMontoDeCompra() {
		return MontoDeCompra;
	}




	public void setMontoDeCompra(double montoDeCompra) {
		MontoDeCompra = montoDeCompra;
	}




	public int getRecibo() {
		return Recibo;
	}




	public void setRecibo(int recibo) {
		Recibo = recibo;
	}




	public String getObservaciones() {
		return Observaciones;
	}




	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
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




	public String getObservacion() {
		return observacion;
	}




	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}




	public boolean isEstado() {
		return estado;
	}




	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	//----------------------METODOS------------------------------------------
	
	public void crearVentas(Connection conexion) {
		
		
		Scanner sc = new Scanner(System.in);
		PreparedStatement stmt = null;
		Statement statement = null;
		String sql;
		ResultSet rs;
		try {
			System.out.println("############################");
			System.out.println("Cliente");
			System.out.println("############################");
			statement = conexion.createStatement();
			sql = "SELECT idpersona,Nombre,Apellido,Documento "
					+ "FROM persona "
					+ "order by idpersona;";
			rs = statement.executeQuery(sql);
			System.out.println("Seleccione Cliente");
			while(rs.next()) 
			{
				int idpersona = rs.getInt("idpersona");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				System.out.println(idpersona + " - " + apellido + " " + nombre + " " + documento);
			}
			int cliente  = sc.nextInt();
			sc.nextLine();
			
			System.out.println("############################");
			System.out.println("Producto");
			System.out.println("############################");
			ArrayList<DetalleVentas> listaProductos = new ArrayList<DetalleVentas>();
			int option = 0;
			do{
				statement = conexion.createStatement();
				sql = "SELECT idproductos,Nombre,PrecioUnitario FROM productos order by idproductos;";
				rs = statement.executeQuery(sql);
				while(rs.next()) 
				{
					System.out.print(rs.getInt("idproductos")+" - ");
					System.out.print(rs.getString("Nombre"));
					System.out.print(rs.getString("PrecioUnitario"));
					System.out.println();
				}
				System.out.println("#######################");
				System.out.println("Ingrese el producto: ");
				int producto = sc.nextInt();
				sc.nextLine();
				System.out.println("Ingrese la cantidad: ");
				int cantidad = sc.nextInt();
				sc.nextLine();
				sql = "SELECT PrecioUnitario FROM productos WHERE idproductos = "+producto+";";
				ResultSet rsp = statement.executeQuery(sql);
				double precio = 0;
				while(rsp.next()) 
				{
					precio = rsp.getDouble("PrecioUnitario");
				}
				DetalleVentas detalleVenta = new DetalleVentas(producto,cantidad, precio);
				listaProductos.add(detalleVenta);
				System.out.println("#######################");
				System.out.println("Desea seguir Selecionando Producto: ");
				System.out.println("1 - SI");
				System.out.println("0 - NO");
				option = sc.nextInt();
				sc.nextLine();
			}while(option == 1);
			
			try {
				statement = conexion.createStatement();
				sql = "SELECT idventas FROM ventas order by idventas DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idVentas = 0;
				while(rs.next()) 
				{
					idVentas = rs.getInt("idventas");
				}
				
				//LocalDate fecha = LocalDate.now();
				
				SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
		        Calendar calendar = Calendar.getInstance();

		        java.util.Date dateObj =  calendar.getTime();
		        String formattedDate = dtf.format(dateObj);
				
				
				double total = 0;
				for(int i=0;i<listaProductos.size();i++) {
					total = total + (listaProductos.get(i).getPrecioUnitario()*listaProductos.get(i).getCantidad());
				}
				String observacion = "";
				stmt = conexion.prepareStatement("INSERT INTO ventas VALUES (?,?,?,?,?,?,?)");
				stmt.setInt(1,idVentas + 1);
				stmt.setDouble(2,total);
				stmt.setInt(3,1);
				stmt.setString(4,observacion);
	        	stmt.setString(5,formattedDate);
	        	stmt.setInt(6,cliente);
	        	stmt.setInt(7,1);
	        	int response = stmt.executeUpdate();
	        	if (response > 0)
	                System.out.println("Venta Insertada correctamente");
	        	
	        	statement = conexion.createStatement();
				sql = "SELECT idDetalleVentas FROM detalleventas order by idDetalleVentas DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idDetalleVentas = 0;
				while(rs.next()) 
				{
					idDetalleVentas = rs.getInt("idDetalleVentas");
				}
	        	
				for(int i=0;i<listaProductos.size();i++) 
				{
					stmt = conexion.prepareStatement("INSERT INTO detalleventas VALUES (?,?,?,?,?)");
					stmt.setInt(1,idDetalleVentas + 1);
					stmt.setInt(2,listaProductos.get(i).getCantidad());
					stmt.setDouble(3,listaProductos.get(i).getPrecioUnitario());
		        	stmt.setInt(4,listaProductos.get(i).getIdproducto());
		        	stmt.setInt(5,idVentas + 1);
		        	response = stmt.executeUpdate();
		        	if (response > 0)
		                System.out.println("DetalleVenta Insertada correctamente");
		        	idDetalleVentas = idDetalleVentas + 1;
				}
				
				for(int i=0;i<listaProductos.size();i++) 
				{
					int producto = 0;
					int stock = 0;
					int idProductos1=0;
					statement = conexion.createStatement();
					sql = "SELECT idProductos,Cantidad FROM productos WHERE idProductos = "+listaProductos.get(i).getIdproducto();
					rs = statement.executeQuery(sql);
					while(rs.next()) 
					{
						producto= rs.getInt("idproductos");
						stock = rs.getInt("Cantidad");
						idProductos1 = rs.getInt("idProductos");
					}
					
					stmt = conexion.prepareStatement("UPDATE productos SET Cantidad=?, detalleventas_ventas_idventas=? WHERE idProductos=?;");
					int stockActual = stock - listaProductos.get(i).getCantidad();
					stmt.setInt(1,stockActual);
					stmt.setInt(2,producto);
					stmt.setInt(3,idProductos1);
					response = stmt.executeUpdate(); 
		        	if (response > 0)
		                System.out.println("Stock actualizado");
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
	
	
	
	
	
	
	
}