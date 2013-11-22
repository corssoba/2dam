import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;

public class ejercicio4 {
	public static void main(String[] args) {
		int existe = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el ID del autor: ");
		String id = sc.next();
		System.out.println();
		System.out.print("Introduce el salario nuevo: ");
		String salario = sc.next();
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/autores","root","");
		    Statement sentencia = conexion.createStatement();
		    ResultSet result1 = sentencia.executeQuery("SELECT id FROM tb_autores");
		    while(result1.next()) {
				if(Integer.parseInt(id) == result1.getInt(1)) {
					existe = 1;
				} 
		    }
		    result1.close();
		    if(existe == 1) {
		    	try {
		    		sentencia.executeUpdate("UPDATE tb_autores SET salario = " + salario + " WHERE id = " + id);
		    		System.out.println("Salario actualizado.");
		    		ResultSet result = sentencia.executeQuery("SELECT * FROM tb_autores WHERE id = " + id);
		    		result.next();
		    		System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+" "+result.getString(5)+" "+result.getInt(6)+" "+result.getString(7)+" "+result.getString(8)+" "+result.getInt(9));
		    		result.close();
		    	} catch(SQLException sql) { System.out.println("No se ha podido actualizar el salario.\n" + sql.getMessage()); }
		    } else {
		    	System.out.println("El autor no existe.");
		    }
		    sentencia.close();
		    conexion.close();
		} catch(SQLException e) { e.printStackTrace(); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
	}
}