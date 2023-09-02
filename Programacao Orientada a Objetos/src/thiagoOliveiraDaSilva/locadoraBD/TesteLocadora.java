package thiagoOliveiraDaSilva.locadoraBD;

import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;


public class TesteLocadora {

	@Test
	public void testeInserirVeiculo() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();
		Veiculo carro1 = new Carro("Ford", "F-1000", 1980, 10000, 50, "LVF-1000", 3);
		locadora.inserir(carro1);
		Veiculo recuperado = locadora.pesquisar("LVF-1000");
		assertEquals("Ford", recuperado.getMarca());
		assertEquals("F-1000", recuperado.getModelo());
		assertEquals(1980, recuperado.getAnoDeFabricacao());
		assertEquals(10000, recuperado.getValorAvaliado(), 0.0001);
		assertEquals(50, recuperado.getValorDiaria(), 0.001);
		assertEquals(3, ((Carro) recuperado).getTipo());	
	}
	
	@Test
	public void testeInserirCliente() throws ClienteJaCadastrado, ClienteNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();
		Cliente cli1 = new Cliente(1234, "Zé Carlos");
		locadora.inserir(cli1);
		Cliente cli2 = locadora.pesquisarCliente(1234);
		assertEquals("Zé Carlos", cli2.getNome());	
	}
	
	@Test
	public void testePesquisarVeiculo() throws VeiculoJaCadastrado, SQLException, VeiculoNaoCadastrado {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();
		Veiculo carro1 = new Carro("Ford", "F-1000", 1980, 10000, 50, "LVF-1000", 3);
		Veiculo carro2 = new Carro("Ford", "KA", 2010, 30000, 100, "LVF-3000", 1);
		
		locadora.inserir(carro1);
		locadora.inserir(carro2);
		Veiculo pesquisa = locadora.pesquisar("LVF-3000");
		// Teste para saber se a pesquisa deu certo
		assertEquals("KA", pesquisa.getModelo());
		
		Veiculo pesquisa2;
		try {
			pesquisa2 = locadora.pesquisar("LVF-1111");
			fail("Não deveria encontrar veiculo");
		} catch (VeiculoNaoCadastrado e) {
		}
	}

	@Test
	public void testePesquisarMoto() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "AXQ-9111", 50);
		Veiculo moto2 = new Moto("Joca Motores", "Zulu", 1978, 10000, 30, "QUX-1234", 40);
		Veiculo moto3 = new Moto("Cálcio Motores", "Molar", 1985, 18000, 50, "WAA-3121", 50);
		
		locadora.inserir(moto1);
		locadora.inserir(moto2);
		locadora.inserir(moto3);
		
		ArrayList<Veiculo> motos50c = locadora.pesquisarMoto(50);
		
		// Confirmando numero de motos com 50 cilindradas
		assertEquals(2, motos50c.size());
	}

	@Test
	public void testePesquisarCarro() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo carro1 = new Carro("Ford", "KA", 1980, 23000, 50, "ABC-1200", 1);
		Veiculo carro2 = new Carro("Toyota", "Corola", 1978, 20000, 40, "QUB-1123", 3);
		Veiculo carro3 = new Carro("GM", "Onix", 1985, 25000, 60, "WIA-2321", 2);
		
		locadora.inserir(carro1);
		locadora.inserir(carro2);
		locadora.inserir(carro3);
		
		ArrayList<Veiculo> carrosPasseio = locadora.pesquisarCarro(1);
		
		// Confirmando numero de carro de passeio
		assertEquals(1, carrosPasseio.size());
	}
	
	@Test
	public void testePesquisarCaminhao() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "XXX-9111", 199);
		Veiculo caminhao2 = new Caminhao("Joca Motores", "Malbará", 1978, 45000, 80, "QQQ-1234", 300);
		Veiculo caminhao3 = new Caminhao("Cálcio Motores", "Incisivo", 1985, 60000, 90, "WWW-3210", 200);
		
		locadora.inserir(caminhao1);
		locadora.inserir(caminhao2);
		locadora.inserir(caminhao3);
		
		ArrayList<Veiculo> caminhoesCarga200 = locadora.pesquisarCaminhao(200);
		
		// Confirmando numero de caminhoes com carga 200
		assertEquals(2, caminhoesCarga200.size());
	}
	
	@Test
	public void testePesquisarOnibus() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo onibus1 = new Onibus("Estrela", "Aldebarã", 1975, 30000, 60, "XXX-9111", 49);
		Veiculo onibus2 = new Onibus("Joca Motores", "Kallanggo", 1978, 40000, 70, "QQQ-1234", 50);
		Veiculo onibus3 = new Onibus("Cálcio Motores", "Bicusp", 1985, 50000, 85, "WWW-3210", 70);
		
		locadora.inserir(onibus1);
		locadora.inserir(onibus2);
		locadora.inserir(onibus3);
		
		ArrayList<Veiculo> onibus50p = locadora.pesquisarOnibus(50);
		
		// Confirmando numero de onibus com 50 passageiros
		assertEquals(2, onibus50p.size());
	}
	
	@Test
	public void testeCalcularAluguel() throws VeiculoJaCadastrado, SQLException, VeiculoNaoCadastrado {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "XXX-9111", 50);
		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "ABC-1000", 1);
		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "SSS-1234", 200);
		Veiculo onibus1 = new Onibus("Estrela", "Aldebara", 1975, 30000, 60, "III-4121", 50);
		locadora.inserir(moto1);
		locadora.inserir(carro1);
		locadora.inserir(caminhao1);
		locadora.inserir(onibus1);
		
		double aluguelMoto = locadora.calcularAluguel("XXX-9111", 5);
		double aluguelCarro = locadora.calcularAluguel("ABC-1000", 5);
		double aluguelCaminhao = locadora.calcularAluguel("SSS-1234", 5);
		double aluguelOnibus = locadora.calcularAluguel("III-4121", 5);
		
		// Confirmando valor do aluguel da moto: (40(diaria) + 4.52(seguro diario)) * 5 dias = 222.6
		assertEquals(222.6, aluguelMoto, 0.01);
		// Confirmando valor do aluguel do carro: (50(diária) + 1.64(seguro diario)) * 5 dias = 258.22
		assertEquals(258.22, aluguelCarro, 0.01);
		// Confirmando valor do aluguel do caminhao: (70(diaria) + 6.58(seguro diario)) * 5 dias = 382.88
		assertEquals(382.88, aluguelCaminhao, 0.01);
		// Confirmando valor do aluguel do onibus: (60(diaria) + 16.44(seguro diario)) * 5 dias = 382.19
		assertEquals(382.19, aluguelOnibus, 0.01);
		
		// Testando calcular aluguel para placa inexistente
		try {
			assertEquals(0, locadora.calcularAluguel("X-999", 10), 0.01);
			fail("Não deveria calcular");
		} catch (VeiculoNaoCadastrado e) {
		}
	}
	
	@Test
	public void testeRegistrarAluguel() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, ClienteNaoCadastrado, ClienteJaCadastrado, VeiculoAlugado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "AAA-1000", 1);
		Cliente cli1 = new Cliente(1234, "Zé Carlos");
		locadora.inserir(carro1);
		locadora.inserir(cli1);
		
		Date hoje = new Date();
		
		// Registrar aluguel de cliente inexistente
		try {
			locadora.registrarAluguel("AAA-1000", hoje, 5, 1111);
			fail("Registrar aluguel indevido");
		} catch (ClienteNaoCadastrado e) {
		}
		
		locadora.registrarAluguel("AAA-1000", hoje, 5, 1234);
		
		// Registrar aluguel de veiculo já registrado
		try {
			locadora.registrarAluguel("AAA-1000", hoje, 5, 1234);
			fail("Registrar aluguel indevido");
		} catch (VeiculoAlugado e) {
		}
		
		// Registrar aluguel de veiculo inexistente
		try {
			locadora.registrarAluguel("AAA-1111", hoje, 5, 1234);
			fail("Registrar aluguel indevido");
		} catch (VeiculoNaoCadastrado e) {
		}		
	}
	
	@Test
	public void testeRegistrarDevolucao()
			throws VeiculoJaCadastrado, VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, ClienteJaCadastrado, VeiculoNaoAlugado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "AAA-1000", 1);
		Cliente cli1 = new Cliente(1234, "Zé Carlos");
		locadora.inserir(carro1);
		locadora.inserir(cli1);
		
		Date hoje = new Date();
		Date dev = new Date();
		dev.setDate(hoje.getDate()+5);
		
		locadora.registrarAluguel("AAA-1000", hoje, 5, 1234);
		locadora.registrarDevolucao("AAA-1000", dev, 1234);

		// Tentar devolução de veiculo não alugado
		try {
			locadora.registrarDevolucao("AAA-1000", dev, 1234);
		} catch (VeiculoNaoAlugado e){
			
		}
		
		// Tentar devolução de veiculo de veiculo não existente
		try {
			locadora.registrarDevolucao("AAA-1111", dev, 1234);
		} catch (VeiculoNaoCadastrado e) {
			
		}
		
	}
	
	@Test
	public void testeDepreciarVeiculos() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "S-123", 200);
		Veiculo onibus1 = new Onibus("Estrela", "Aldebarã", 1975, 30000, 60, "I-412", 50);
		locadora.inserir(moto1);
		locadora.inserir(carro1);
		locadora.inserir(caminhao1);
		locadora.inserir(onibus1);
		
		locadora.depreciarVeiculos(1, 0.1);		// Depreciando motos em 10%
		locadora.depreciarVeiculos(2, 0.2);		// Depreciando carros em 20%
		locadora.depreciarVeiculos(3, 0.05);	// Depreciando caminhões em 5%
		locadora.depreciarVeiculos(4, 0.15);	// Depreciando ônibus em 15%
		
		assertEquals(13500, locadora.pesquisar("X-911").getValorAvaliado(), 0.01);
		assertEquals(16000, locadora.pesquisar("A-100").getValorAvaliado(), 0.01);
		assertEquals(28500, locadora.pesquisar("S-123").getValorAvaliado(), 0.01);
		assertEquals(25500, locadora.pesquisar("I-412").getValorAvaliado(), 0.01);
		
		locadora.depreciarVeiculos(0, 0.1);		// Depreciando todos veículos em 10%
		
		assertEquals(12150, locadora.pesquisar("X-911").getValorAvaliado(), 0.01);
		assertEquals(14400, locadora.pesquisar("A-100").getValorAvaliado(), 0.01);
	}
	
	@Test
	public void testeAumentarDiaria() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, SQLException {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "S-123", 200);
		Veiculo onibus1 = new Onibus("Estrela", "Aldebarã", 1975, 30000, 60, "I-412", 50);
		locadora.inserir(moto1);
		locadora.inserir(carro1);
		locadora.inserir(caminhao1);
		locadora.inserir(onibus1);
		
		locadora.aumentarDiaria(1, 0.1);	// Aumentando diária de motos em 10%
		locadora.aumentarDiaria(2, 0.2);	// Aumentando diária de carros em 20%
		locadora.aumentarDiaria(3, 0.05);	// Aumentando diária de caminhões em 5%
		locadora.aumentarDiaria(4, 0.15);	// Aumentando diária de ônibus em 15%
		
		assertEquals(44, locadora.pesquisar("X-911").getValorDiaria(), 0.01);
		assertEquals(60, locadora.pesquisar("A-100").getValorDiaria(), 0.01);
		assertEquals(73.5, locadora.pesquisar("S-123").getValorDiaria(), 0.01);
		assertEquals(69, locadora.pesquisar("I-412").getValorDiaria(), 0.01);
		
		locadora.aumentarDiaria(0, 0.1);	// Aumentando diária de todos veículos em 10%
		
		assertEquals(48.4, locadora.pesquisar("X-911").getValorDiaria(), 0.01);
		assertEquals(66, locadora.pesquisar("A-100").getValorDiaria(), 0.01);
	}
	
	@Test
	public void testeFaturamentoTotal() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, SQLException, ClienteJaCadastrado, VeiculoNaoAlugado {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "S-123", 200);
		Veiculo onibus1 = new Onibus("Estrela", "Aldebarã", 1975, 30000, 60, "I-412", 50);
		locadora.inserir(moto1);
		locadora.inserir(carro1);
		locadora.inserir(caminhao1);
		locadora.inserir(onibus1);
		
		Cliente cli1 = new Cliente(1234, "Zé Carlos");
		locadora.inserir(cli1);
		Date hoje = new Date();
		Date ontem = new Date(hoje.getTime() - 1);
		Date amanha = new Date(hoje.getTime() + 1);
		
		locadora.registrarAluguel("X-911", hoje, 5, 1234);	// Valor do aluguel = 222.6  (moto)
		locadora.registrarDevolucao("X-911", amanha, 1234);
		locadora.registrarAluguel("A-100", hoje, 5, 1234);	// Valor do aluguel = 258.22 (carro)
		locadora.registrarDevolucao("A-100", amanha, 1234);
		locadora.registrarAluguel("S-123", hoje, 5, 1234);	// Valor do aluguel = 382.88 (caminhão)
		locadora.registrarDevolucao("S-123",  amanha, 1234);
		locadora.registrarAluguel("I-412", hoje, 5, 1234);	// Valor do aluguel = 382.19 (ônibus)
		locadora.registrarDevolucao("I-412",  amanha, 1234);
		
		assertEquals(222.6, locadora.faturamentoTotal(1, ontem, amanha), 0.01);	// Faturamento total de motos
		assertEquals(258.22, locadora.faturamentoTotal(2, ontem, amanha), 0.01);	// Faturamento total de carros
		assertEquals(382.88, locadora.faturamentoTotal(3, ontem, amanha), 0.01);	// Faturamento total de caminhões
		assertEquals(382.19, locadora.faturamentoTotal(4, ontem, amanha), 0.01);	// Faturamento total de ônibus
		assertEquals(1245.89, locadora.faturamentoTotal(0, ontem, amanha), 0.01);	// Faturamento total
	}
	
	@Test
	public void testeQuantidadeTotalDeDiarias() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, SQLException, ClienteJaCadastrado, VeiculoNaoAlugado {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "S-123", 200);
		Veiculo onibus1 = new Onibus("Estrela", "Aldebarã", 1975, 30000, 60, "I-412", 50);
		locadora.inserir(moto1);
		locadora.inserir(carro1);
		locadora.inserir(caminhao1);
		locadora.inserir(onibus1);
		
		Cliente cli1 = new Cliente(1234, "Zé Carlos");
		locadora.inserir(cli1);
		
		Date hoje = new Date();
		Date ontem = new Date(hoje.getTime() - 1);
		Date amanha = new Date(hoje.getTime() + 1);
		
		locadora.registrarAluguel("X-911", hoje, 5, 1234);	// 5 diárias de moto
		locadora.registrarAluguel("A-100", hoje, 10, 1234);	// 10 diárias de carro
		locadora.registrarAluguel("S-123", hoje, 7, 1234);	// 7 diárias de caminhão
		locadora.registrarAluguel("I-412", hoje, 2, 1234);	// 2 diárias de ônibus
		locadora.registrarDevolucao("X-911",  amanha, 1234);
		locadora.registrarDevolucao("A-100",  amanha, 1234);
		locadora.registrarDevolucao("S-123",  amanha, 1234);
		locadora.registrarDevolucao("I-412",  amanha, 1234);

		assertEquals(5, locadora.quantidadeTotalDeDiarias(1, ontem, amanha));	// Quantidade de diárias de moto
		assertEquals(10, locadora.quantidadeTotalDeDiarias(2, ontem, amanha));	// Quantidade de diárias de carro
		assertEquals(7, locadora.quantidadeTotalDeDiarias(3, ontem, amanha));	// Quantidade de diárias de caminhão
		assertEquals(2, locadora.quantidadeTotalDeDiarias(4, ontem, amanha));	// Quantidade de diárias de ônibus
		assertEquals(24, locadora.quantidadeTotalDeDiarias(0, ontem, amanha));	// Quantidade de diárias total
	}
	
	@Test
	public void testeQuantidadeTotalDeDiarias2() throws VeiculoJaCadastrado, VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, SQLException, ClienteJaCadastrado, VeiculoNaoAlugado {
		MinhaLocadora locadora = new MinhaLocadora();
		locadora.removerTudo();

		Veiculo moto1 = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro1 = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao1 = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "S-123", 200);
		Veiculo onibus1 = new Onibus("Estrela", "Aldebarã", 1975, 30000, 60, "I-412", 50);
		locadora.inserir(moto1);
		locadora.inserir(carro1);
		locadora.inserir(caminhao1);
		locadora.inserir(onibus1);
		
		Cliente cli1 = new Cliente(1234, "Zé Carlos");
		locadora.inserir(cli1);
		
		Date hoje = new Date();
		Date ontem = new Date(hoje.getTime() - 1);
		Date amanha = new Date(hoje.getTime() + 1);
		
		locadora.registrarAluguel("X-911", ontem, 5, 1234);	// 5 diárias de moto
		locadora.registrarAluguel("A-100", ontem, 10, 1234);	// 10 diárias de carro
		locadora.registrarAluguel("S-123", ontem, 7, 1234);	// 7 diárias de caminhão
		locadora.registrarAluguel("I-412", ontem, 2, 1234);	// 2 diárias de ônibus
		locadora.registrarDevolucao("X-911",  amanha, 1234);
		locadora.registrarDevolucao("A-100",  amanha, 1234);
		locadora.registrarDevolucao("S-123",  amanha, 1234);
		locadora.registrarDevolucao("I-412",  amanha, 1234);

		assertEquals(0, locadora.quantidadeTotalDeDiarias(1, hoje, amanha));	// Quantidade de diárias de moto
		assertEquals(0, locadora.quantidadeTotalDeDiarias(2, hoje, amanha));	// Quantidade de diárias de carro
		assertEquals(0, locadora.quantidadeTotalDeDiarias(3, hoje, amanha));	// Quantidade de diárias de caminhão
		assertEquals(0, locadora.quantidadeTotalDeDiarias(4, hoje, amanha));	// Quantidade de diárias de ônibus
		assertEquals(0, locadora.quantidadeTotalDeDiarias(0, hoje, amanha));	// Quantidade de diárias total
	}
	
}
