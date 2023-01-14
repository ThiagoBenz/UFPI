package thiagoOliveiraDaSilva.locadoraBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCliente {
	// Retorna uma lista de IDs dos veículos no Banco de Dados
	public static ArrayList<Integer> list() throws SQLException {
		ArrayList<Integer> clientesIds = new ArrayList<Integer>();
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [cliente,list] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes;");
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			clientesIds.add(id);
			DatabaseHelper.Log(String.format("Adding id \"%d\"", id));
		}

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return clientesIds;
	}

	// Remove all
	public static void removeall() throws SQLException {
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [cliente,removeall] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM clientes;");
		statement.executeUpdate();

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
	}

	// Obtém os dados de um veículo a partir de seu ID
	public static Cliente get(int id) throws SQLException {
		Cliente resultCliente = null;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [cliente,get] \"%s\"", conUidString));

		ResultSet resultSet;
		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes WHERE id = ?;");
		statement.setInt(1, id);
		System.out.println(statement);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			System.out.println(resultSet.toString());
			// Get type field and switch behavior depending of its value
			int cpfValue = resultSet.getInt(2);
			String nomeValue = resultSet.getString(3);

			resultCliente = new Cliente(cpfValue, nomeValue);

			DatabaseHelper.Log(String.format("Returning found cliente"));
		} else {
			DatabaseHelper.Log(String.format("Client not found"));
		}

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return resultCliente;
	}

	// Adiciona um veículo no banco de dados
	public static boolean insert(int cpf, String nome) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [cliente,insert] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO clientes (cpf, nome) VALUES (?, ?);");
		statement.setInt(1, cpf);
		statement.setString(2, nome);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Cliente inserted"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Remove um veículo do banco de dados através do ID do mesmo
	public static boolean remove(int id) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [cliente,remove] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM clientes WHERE id = ?;");
		statement.setInt(1, id);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Cliente delete comamand sent successfully"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Atualiza um veículo no banco de dados, passando o ID e os dados do mesmo para
	// preenchimento dos seus valores na tabela do banco de dados
	public static boolean update(int id, int cpf, String nome) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [cliente,update] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"UPDATE clientes SET cpf = ?, nome = ? WHERE id = ?;");
		statement.setInt(1, cpf);
		statement.setString(2, nome);
		statement.setInt(3, id);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Cliente Update command successfully sent"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}
}
