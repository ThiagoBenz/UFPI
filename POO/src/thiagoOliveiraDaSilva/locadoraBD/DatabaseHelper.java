/**
 * 
 */
package thiagoOliveiraDaSilva.locadoraBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @author thiago
 *
 */
public class DatabaseHelper {
	private static Connection instanceConnection = null;
	public static Connection getConnection() throws SQLException {
		if (instanceConnection != null) {
			return instanceConnection;
		}
		String userString = "root";
		String passwordString = "1234";
		String hostUrl = "localhost:3306";
		String databaseName = "locadora_thiagosilva";
		String databaseUriString = String.format("jdbc:mysql://%s/%s?useSSL=false", hostUrl, databaseName);
		Connection connection;
		connection = DriverManager.getConnection(databaseUriString, userString, passwordString);
		DatabaseHelper.instanceConnection = connection;
		return connection;
	}
	
	public static void Log(String messageString) {
		Date nowDate = new Date();
		String timestampString = String.format("[ %s ]", nowDate.toString());
		String fullMessageString = String.format("%s %s", timestampString, messageString);
		System.out.println(fullMessageString);
	}
	
	public static String generateUuid() {
		String uidString = UUID.randomUUID().toString();
		return uidString;
	}
	
	static void insertVeiculoOnDatabase(Veiculo v) throws SQLException {
		int tipo = DAOVeiculo.TIPO_VEICULO_DESCONHECIDO;
		int cilindrada = 0;
		double carga = 0.0; 
		int categoria = 0;
		int capacidadePassageiros = 0;
		if (v.getClass() == Moto.class) {
			cilindrada = ((Moto) v).getCilindrada();
			tipo = DAOVeiculo.TIPO_VEICULO_MOTO;
		}
		if (v.getClass() == Caminhao.class) {
			carga = ((Caminhao) v).getCarga();
			tipo = DAOVeiculo.TIPO_VEICULO_CAMINHAO;
		}
		if (v.getClass() == Carro.class) {
			categoria = ((Carro) v).getCategoria();
			tipo = DAOVeiculo.TIPO_VEICULO_CARRO;
		}
		if (v.getClass() == Onibus.class) {
			capacidadePassageiros = ((Onibus) v).getCapacidadePassageiros();
			tipo = DAOVeiculo.TIPO_VEICULO_ONIBUS;
		}
		DAOVeiculo.insert(v.getMarca(), v.getPlaca(), v.getModelo(), v.getAnoDeFabricacao(), v.getValorDiaria(), v.getValorAvaliado(), v.getSeguro(), tipo, cilindrada, carga, categoria, capacidadePassageiros);
	}
	
	static void updateVeiculoOnDatabase(int id, Veiculo v) throws SQLException {
		int tipo = DAOVeiculo.TIPO_VEICULO_DESCONHECIDO;
		int cilindrada = 0;
		double carga = 0; 
		int categoria = 0;
		int capacidadePassageiros = 0;
		if (v.getClass() == Moto.class) {
			cilindrada = ((Moto) v).getCilindrada();
			tipo = DAOVeiculo.TIPO_VEICULO_MOTO;
		}
		if (v.getClass() == Caminhao.class) {
			carga = ((Caminhao) v).getCarga();
			tipo = DAOVeiculo.TIPO_VEICULO_CAMINHAO;
		}
		if (v.getClass() == Carro.class) {
			categoria = ((Carro) v).getCategoria();
			tipo = DAOVeiculo.TIPO_VEICULO_CARRO;
		}
		if (v.getClass() == Onibus.class) {
			capacidadePassageiros = ((Onibus) v).getCapacidadePassageiros();
			tipo = DAOVeiculo.TIPO_VEICULO_ONIBUS;
		}
		DAOVeiculo.update(id, v.getMarca(), v.getPlaca(), v.getModelo(), v.getAnoDeFabricacao(), v.getValorDiaria(), v.getValorAvaliado(), v.getSeguro(), tipo, cilindrada, carga, categoria, capacidadePassageiros);
	}
	
	static ArrayList<Veiculo> getAllVehiclesFromDatabase() {
		ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		ArrayList<Integer> idsList;
		try {
			idsList = DAOVeiculo.list();
			for (int id : idsList) {
				Veiculo v;
				v = DAOVeiculo.get(id);
				listaVeiculos.add(v);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return listaVeiculos;
	}
	
	static ArrayList<Aluguel> getAllAlugueisFromDatabase() throws SQLException {
		ArrayList<Aluguel> listaAlugueis = new ArrayList<Aluguel>();
		ArrayList<Integer> idsList;
		idsList = DAOAluguel.list();
		for (int id : idsList) {
			Aluguel v;
			v = DAOAluguel.get(id);
			listaAlugueis.add(v);
		}
		return listaAlugueis;
	}
	
	static ArrayList<Cliente> getAllClientesFromDatabase() throws SQLException {
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		ArrayList<Integer> idsList;
		idsList = DAOCliente.list();
		for (int id : idsList) {
			Cliente cliente;
			cliente = DAOCliente.get(id);
			listaClientes.add(cliente);
		}
		return listaClientes;
	}
	
	public static SimpleDateFormat retrieveDatabaseFormatter(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter;
	}
}
