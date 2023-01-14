package thiagoOliveiraDaSilva.locadoraBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DAOAluguel {
	// Retorna uma lista de IDs dos veículos no Banco de Dados
	public static ArrayList<Integer> list() throws SQLException {
		ArrayList<Integer> aluguelIds = new ArrayList<Integer>();
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [aluguel,list] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM alugueis;");
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			aluguelIds.add(id);
			DatabaseHelper.Log(String.format("Adding id \"%d\"", id));
		}

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return aluguelIds;
	}

	// Remove all
	public static void removeall() throws SQLException {
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [aluguel,removeall] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM alugueis;");
		statement.executeUpdate();

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
	}

	// Obtém os dados de um veículo a partir de seu ID
	public static Aluguel get(int id) throws SQLException {
		Aluguel resultAluguel = null;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [aluguel,get] \"%s\"", conUidString));

		ResultSet resultSet;
		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM alugueis WHERE id = ?;");
		statement.setInt(1, id);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			// Get type field and switch behavior depending of its value
			long dataInicialLong = resultSet.getLong(2);
			int diasInt = resultSet.getInt(3);
			int veiculoId = resultSet.getInt(4);
			int cpfCliente = resultSet.getInt(5);
			boolean devolvido = (resultSet.getInt(6) == 0) ? false : true;

			Calendar calendar = Calendar.getInstance();
			Date initialDate = new Date(dataInicialLong);
			Veiculo refVeiculo = DAOVeiculo.get(veiculoId);

			resultAluguel = new Aluguel();
			resultAluguel.setDataInicio(initialDate);
			resultAluguel.setDias(diasInt);
			resultAluguel.setVeiculoAlugado(refVeiculo);
			resultAluguel.setCpfCliente(cpfCliente);
			resultAluguel.setDevolvido(devolvido);

			DatabaseHelper.Log(String.format("Returning found aluguel"));
		}

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return resultAluguel;
	}

	// Adiciona um veículo no banco de dados
	public static boolean insert(long dataInicial, int dias, int idVeiculo, int cpfCliente, boolean devolvido)
			throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [aluguel,insert] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO alugueis (dataInicial, dias, veiculoId, cpfCliente, devolvido) VALUES (?, ?, ?, ?, ?);");
		statement.setLong(1, dataInicial);
		statement.setInt(2, dias);
		statement.setInt(3, idVeiculo);
		statement.setInt(4, cpfCliente);
		statement.setInt(5, (devolvido == true) ? 1 : 0);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Aluguel inserted"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Remove um veículo do banco de dados através do ID do mesmo
	public static boolean remove(int id) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [aluguel,remove] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM alugueis WHERE id = ?;");
		statement.setInt(1, id);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Aluguel delete comamand sent successfully"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Atualiza um veículo no banco de dados, passando o ID e os dados do mesmo para
	// preenchimento dos seus valores na tabela do banco de dados
	public static boolean update(int id, long dataInicial, int dias, int idVeiculo, int cpfCliente, boolean devolvido)
			throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [aluguel,update] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"UPDATE alugueis SET dataInicial = ?, dias = ?, veiculoId = ?, cpfCliente = ?, devolvido = ? WHERE id = ?;");
		statement.setLong(1, dataInicial);
		statement.setInt(2, dias);
		statement.setInt(3, idVeiculo);
		statement.setInt(4, cpfCliente);
		statement.setInt(5, (devolvido == true) ? 1 : 0);
		statement.setInt(6, id);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Aluguel Update command successfully sent"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}
}
