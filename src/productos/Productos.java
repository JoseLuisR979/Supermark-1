package productos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;





public class Productos {
	
	//Atributos
	
	//private int idProducto;
	private String Nombre; 
	private String Descripcion;
	private int Cantidad;
	private double PrecioUnitario;
	private int idCategoria;
	private int idVentas;
	private int idDetalleVentas;
	
	
	//----------------Constructores----------------------------------
	public Productos() { }


	public Productos(int idProducto, String nombre, String descripcion, int cantidad, double precioUnitario,
			int idCategoria, int idVentas, int idDetalleVentas) {
		super();
		//this.idProducto = idProducto;
		Nombre = nombre;
		Descripcion = descripcion;
		Cantidad = cantidad;
		PrecioUnitario = precioUnitario;
		this.idCategoria = idCategoria;
		this.idVentas = idVentas;
		this.idDetalleVentas = idDetalleVentas;
	}



	
	
	//--------------------Getter & Setters--------------------------
	
	//public int getIdProducto() {
	//	return idProducto;
	//}


	//public void setIdProducto(int idProducto) {
	//	this.idProducto = idProducto;
	//}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


	public int getCantidad() {
		return Cantidad;
	}


	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}


	public double getPrecioUnitario() {
		return PrecioUnitario;
	}


	public void setPrecioUnitario(double precioUnitario) {
		PrecioUnitario = precioUnitario;
	}


	public int getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	public int getIdVentas() {
		return idVentas;
	}


	public void setIdVentas(int idVentas) {
		this.idVentas = idVentas;
	}


	public int getIdDetalleVentas() {
		return idDetalleVentas;
	}


	public void setIdDetalleVentas(int idDetalleVentas) {
		this.idDetalleVentas = idDetalleVentas;
	}
	

	//----------------------------Metodos--------------------------------------
	
		//------------------Insertar Producto-----------------------------------
	
	public void insertarProducto(Connection conexion) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Datos de Producto");
		System.out.println("Nombre: ");
		String Nombre = sc.nextLine();
		System.out.println("Descripcion : ");
		String Descripcion = sc.nextLine();
		System.out.println("Cantidad: ");
		String Cantidad = sc.nextLine();
		System.out.println("Precio Unitario: ");
		String PrecioUnitario = sc.nextLine();
		

		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try 
			{
			//busca el ultimo registro en la tabla
			statement = conexion.createStatement();
			sql = "SELECT idproductos, nombre FROM productos;";// order by idproductos DESC LIMIT 1;";
			rs = statement.executeQuery(sql);
			int idproductos = 0;
			
			while (rs.next()) 
			{
				idproductos = rs.getInt("idproductos");
				//System.out.println("esto es "+idproductos);
			}
		
		
		
			
			//System.out.println("ahora"+idproductos);
			
			stmt = conexion.prepareStatement("INSERT INTO productos VALUES (?,?,?,?,?,?,?,?)");
				stmt.setInt(1,idproductos + 1);
				stmt.setString(2,Nombre);
				stmt.setString(3,Descripcion);
				stmt.setString(4,Cantidad);
				stmt.setString(5,PrecioUnitario);
				stmt.setString(6,"1");
				stmt.setString(7,"1");
				stmt.setString(8,"1");
				
			int response = stmt.executeUpdate();
				if (response > 0) 
				{
					System.out.println("Se dio de alta el producto correctamente!!");
					sc.close();
				}
			}catch (SQLException sqle){
	            System.out.println("SQLState: "+ sqle.getSQLState());
	            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
	            sqle.printStackTrace();
							 
	        }catch (Exception e){
	            e.printStackTrace();
		
	        	}

}
		
		
		//-------------------Eliminar Producto--------------------------------------
	
	public void eliminarProducto(Connection conexion) {
	
		Scanner sc = new Scanner(System.in);
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		
		PreparedStatement stmt;
	
	try {
		statement = conexion.createStatement();
		
		sql = "SELECT idproductos, nombre, descripcion, cantidad FROM productos order by idproductos ;";
		
		rs = statement.executeQuery(sql);
		
		
		
		System.out.println("Seleccione Producto a Eliminar");
		
		while(rs.next()) 
			{
			
			int idproductos = rs.getInt("idproductos");
			String nombre = rs.getString("nombre");
			String descripcion = rs.getString("descripcion");
			String cantidad = rs.getString("cantidad");
			//0String tipodocumento = rs.getString("TipoDocumento");
			System.out.println(idproductos + " - " + nombre + " " + descripcion + " " + cantidad );
			}
		System.out.println("Cancelar seleccione presione 0");
		int producto  = sc.nextInt();
		sc.nextLine();
		
		if(producto!=0) {
						
			statement = conexion.createStatement();
			sql = "SELECT idproductos FROM productos WHERE idproductos = "+producto+";";
			rs = statement.executeQuery(sql);
			
			int idproductos = 0;
			while(rs.next())  
			{
				idproductos = rs.getInt("idproductos");
			}	
			
			System.out.println("Datos ingresados, modificando...");
			
			stmt = conexion.prepareStatement("DELETE FROM productos WHERE idproductos = "+producto+";");
			
        	int response = stmt.executeUpdate();
        	
        	if(response>0) 
        	{
        		System.out.println("Se elimino producto correctamente");
        		sc.close();
        	}	
		}
	
	
	
}catch (SQLException sqle){
    System.out.println("SQLState: "+ sqle.getSQLState());
    System.out.println("SQLErrorCode: " + sqle.getErrorCode());
    sqle.printStackTrace();
}catch (Exception e){
    e.printStackTrace();
}




	

}
	
	
	//----------------------Listar Productos ---------------------------
	public void listarProductos(Connection conexion) {
	
	Statement statement = null;
	String sql;
	ResultSet rs;
	
	

try {
	statement = conexion.createStatement();
	
	sql = "SELECT idproductos, nombre, descripcion, cantidad, preciounitario FROM productos order by idproductos ;";
	
	rs = statement.executeQuery(sql);
	
	
	
	System.out.println("Listado de productos");
	
	while(rs.next()) 
		{
		
		int idproductos = rs.getInt("idproductos");
		String nombre = rs.getString("nombre");
		String descripcion = rs.getString("descripcion");
		String cantidad = rs.getString("cantidad");
		String PrecioUnitario = rs.getString("preciounitario");
		System.out.println(idproductos + " - " + nombre + " " + descripcion + " " + cantidad + " " + PrecioUnitario );
		}
			}catch (SQLException sqle){
				System.out.println("SQLState: "+ sqle.getSQLState());
				System.out.println("SQLErrorCode: " + sqle.getErrorCode());
				sqle.printStackTrace();
				}catch (Exception e){
					e.printStackTrace();
}
}

	//-----------------------Modificar Producto--------------------------------
	
	
	public void modificarProductos(Connection conexion) {
		
		
		
		Scanner sc = new Scanner(System.in);
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.createStatement();
			
			sql = "SELECT idProductos, Nombre, Descripcion, Cantidad, PrecioUnitario FROM productos order by idProductos ;";
			
			rs = statement.executeQuery(sql);
			
			System.out.println("Seleccione Producto");
			
			while(rs.next()) 
				{
				int idproducto = rs.getInt("idProductos");
				String nombre = rs.getString("Nombre");
				String descripcion = rs.getString("Descripcion");
				int cantidad = rs.getInt("Cantidad");
				double preciounitario = rs.getDouble("PrecioUnitario");
				System.out.println(idproducto + " - " + nombre + " " + descripcion + " " + cantidad + " " + preciounitario );
				}
			System.out.println("Cancelar seleccione presione 0");
			int prod  = sc.nextInt();
			sc.nextLine();
			
			if(prod!=0) {
				System.out.println("#######################");
				System.out.println("Datos del Producto a Modificar");
				System.out.println("#######################");
				System.out.println("Nombre :");
				String nombre1 = sc.nextLine(); 
				System.out.println("Descripcion :");
				String descripcion1 = sc.nextLine();
				System.out.println("Cantidad :");
				int cantidad1 = sc.nextInt();
				System.out.println("Precio Unitario");
				Double preciounitario1 = sc.nextDouble();
								
				statement = conexion.createStatement();
				sql = "SELECT idproductos FROM productos WHERE idproductos = "+prod+";";
				rs = statement.executeQuery(sql);
				int idproducto1 = 0;
				while(rs.next())  
				{
					idproducto1 = rs.getInt("idproductos");
				}	
				
				System.out.println("Datos ingresados, modificando...");
				
				stmt = conexion.prepareStatement("UPDATE productos SET descripcion="+descripcion1+" WHERE idProductos = "+prod+"; ");
						//+ " Nombre = ?,"
						//+ " Descripcion = ?,"
						//+ " Cantidad = ?,"
						//+ " PrecioUnitario = ?,"
					//	+ " detalleventas_idDetalleVentas = ?,"
					//	+ " detalleventas_ventas_idventas = ?,"
					//	+ " categoriaproductos_idCategoria = ?,"
					//	+ " WHERE idProductos = ?; " );
	        //	stmt.setString(1,nombre1);
	        //	stmt.setString(2,descripcion1);
	        	//stmt.setString(3,cantidad1);
	        //	stmt.setString(4,preciounitario1);
	        //	stmt.setString(5,"1");
	        //	stmt.setString(6,"1");
	        //	stmt.setString(7,"1");
	        //	stmt.setInt(8,prod);
	        	
	        	int response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("se actualizo producto correctamente");
	        		sc.close();
	        	}	
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