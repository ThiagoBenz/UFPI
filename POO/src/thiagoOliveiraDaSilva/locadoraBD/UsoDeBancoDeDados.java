package thiagoOliveiraDaSilva.locadoraBD;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

public class UsoDeBancoDeDados {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(

		"jdbc:mysql://localhost/locad?useSSL=false", "root", "12345678");

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("select * from veiculo");

		while (rs.next()) {

		String placa = rs.getString("placa");

		String marca = rs.getString("marca");

		String modelo = rs.getString("modelo");

		int ano = rs.getInt("ano");

		double valor = rs.getDouble("valordobem");

		System.out.println("Placa: " + placa);

		System.out.println("Marca: " + marca);

		System.out.println("Modelo: " + modelo);

		System.out.println("Ano: " + ano);

		System.out.println("Valor: " + valor);

		}

		st.executeUpdate("insert into veiculo (placa, marca, modelo, ano, valordobem) values  (\'ccc-3333\', \'VW\', \'Fusca\', 1999, 5000)");

		}
	

}
