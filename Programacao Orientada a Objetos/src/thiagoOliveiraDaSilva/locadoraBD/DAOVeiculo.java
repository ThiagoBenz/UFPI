package thiagoOliveiraDaSilva.locadoraBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOVeiculo {

	public static final int TIPO_VEICULO_DESCONHECIDO = 0;
	public static final int TIPO_VEICULO_CARRO = 1;
	public static final int TIPO_VEICULO_CAMINHAO = 2;
	public static final int TIPO_VEICULO_ONIBUS = 3;
	public static final int TIPO_VEICULO_MOTO = 4;

	// Retorna uma lista de IDs dos veículos no Banco de Dados
	public static ArrayList<Integer> list() throws SQLException {
		ArrayList<Integer> vehicleIds = new ArrayList<Integer>();
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [list] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM veiculos;");
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			vehicleIds.add(id);
			DatabaseHelper.Log(String.format("Adding id \"%d\"", id));
		}

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return vehicleIds;
	}

	// Remove all
	public static void removeall() throws SQLException {
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [veiculos,removeall] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM veiculos;");
		statement.executeUpdate();

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
	}

	// Pesquisa por dados específicos, e retorna o ID do mesmo na tabela de
	// veículos.
	// Retorna -1 quando não encontrado.
	public static int search(String placa) throws SQLException {
		int result = -1;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [search] \"%s\"", conUidString));

		ResultSet resultSet;
		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM veiculos WHERE placa = ?;");
		statement.setString(1, placa);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			result = resultSet.getInt(1);
			DatabaseHelper.Log(String.format("Found result id \"%d\"", result));
		}

		DatabaseHelper.Log(String.format("Returning result id \"%d\"", result));
		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Obtém os dados de um veículo a partir de seu ID
	public static Veiculo get(int id) throws SQLException {
		Veiculo resultVeiculo = null;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [get] \"%s\"", conUidString));

		ResultSet resultSet;
		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM veiculos WHERE id = ?;");
		statement.setInt(1, id);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			// Get type field and switch behavior depending of its value
			String modeloString = resultSet.getString(2);
			String placaString = resultSet.getString(3);
			String marcaString = resultSet.getString(4);
			int anoFabricacaoInt = resultSet.getInt(5);
			double valorDiariaDouble = resultSet.getDouble(6);
			double valorBemDouble = resultSet.getDouble(7);
			double seguroDouble = resultSet.getDouble(8);
			int tipoInt = resultSet.getInt(9);
			int cilindradaInt = resultSet.getInt(10);
			double cargaDouble = resultSet.getDouble(11);
			int categoriaInt = resultSet.getInt(12);
			int capacidadePassageirosInt = resultSet.getInt(13);
			switch (tipoInt) {
				case TIPO_VEICULO_CARRO:
					resultVeiculo = new Carro(marcaString, modeloString, anoFabricacaoInt, valorBemDouble,
							valorDiariaDouble, placaString, categoriaInt);
					break;
				case TIPO_VEICULO_CAMINHAO:
					resultVeiculo = new Caminhao(marcaString, modeloString, anoFabricacaoInt, valorBemDouble,
							valorDiariaDouble, placaString, cargaDouble);
					break;
				case TIPO_VEICULO_ONIBUS:
					resultVeiculo = new Onibus(marcaString, modeloString, anoFabricacaoInt, valorBemDouble,
							valorDiariaDouble, placaString, capacidadePassageirosInt);
					break;
				case TIPO_VEICULO_MOTO:
					resultVeiculo = new Moto(marcaString, modeloString, anoFabricacaoInt, valorBemDouble,
							valorDiariaDouble, placaString, cilindradaInt);
					break;
				default:
					throw new SQLException("Tipo de veiculo invalido");
			}
			DatabaseHelper.Log(String.format("Returning found vehicle"));
		}

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return resultVeiculo;
	}

	// Adiciona um veículo no banco de dados
	public static boolean insert(String marca, String placa, String modelo, int anoFabricacao, double valorDiaria,
			double valorBem, double seguro, int tipo, int cilindrada, double carga, int categoria,
			int capacidadePassageiros) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [insert] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO veiculos (marca, placa, modelo, anoFabricacao, valorDiaria, valorBem, seguro, tipo, cilindrada, carga, categoria, capacidadePassageiros) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		statement.setString(1, marca);
		statement.setString(2, placa);
		statement.setString(3, modelo);
		statement.setInt(4, anoFabricacao);
		statement.setDouble(5, valorDiaria);
		statement.setDouble(6, valorBem);
		statement.setDouble(7, seguro);
		statement.setInt(8, tipo);
		statement.setInt(9, cilindrada);
		statement.setDouble(10, carga);
		statement.setInt(11, categoria);
		statement.setInt(12, capacidadePassageiros);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Vehicle inserted"));
		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Remove um veículo do banco de dados através do ID do mesmo
	public static boolean remove(int id) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [remove] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM veiculos WHERE id = ?;");
		statement.setInt(1, id);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Vehicle delete comamand sent successfully"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}

	// Atualiza um veículo no banco de dados, passando o ID e os dados do mesmo para
	// preenchimento dos seus valores na tabela do banco de dados
	public static boolean update(int id, String marca, String placa, String modelo, int anoFabricacao,
			double valorDiaria, double valorBem, double seguro, int tipo, int cilindrada, double carga, int categoria,
			int capacidadePassageiros) throws SQLException {
		boolean result = false;
		String conUidString = DatabaseHelper.generateUuid();
		DatabaseHelper.Log(String.format("Starting connection [update] \"%s\"", conUidString));

		Connection connection = DatabaseHelper.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"UPDATE veiculos SET marca = ?, placa = ?, modelo = ?, anoFabricacao = ?, valorDiaria = ?, valorBem = ?, seguro = ?, tipo = ?, cilindrada = ?, carga = ?, categoria = ?, capacidadePassageiros = ? WHERE id = ?;");
		statement.setString(1, marca);
		statement.setString(2, placa);
		statement.setString(3, modelo);
		statement.setInt(4, anoFabricacao);
		statement.setDouble(5, valorDiaria);
		statement.setDouble(6, valorBem);
		statement.setDouble(7, seguro);
		statement.setInt(8, tipo);
		statement.setInt(9, cilindrada);
		statement.setDouble(10, carga);
		statement.setInt(11, categoria);
		statement.setInt(12, capacidadePassageiros);
		statement.setInt(13, id);
		statement.executeUpdate();
		result = true;
		DatabaseHelper.Log(String.format("Veiculo Update command successfully sent"));

		DatabaseHelper.Log(String.format("Ending connection \"%s\"", conUidString));
		return result;
	}
}
