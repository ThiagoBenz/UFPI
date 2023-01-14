package thiagoOliveiraDaSilva.locadoraBD;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MinhaLocadora extends Locadora {

	@Override
	public boolean inserir(Veiculo v) {
		// Passo 1: validar veiculo. Se nao for valido, retornar falso.
		if (v == null) {
			return false;
		}
		// Passo 2: verificar se o veiculo ja existe
		String placaNovoVeiculo = v.getPlaca();
		Veiculo veiculoExistente = null;
		try {
			veiculoExistente = pesquisar(placaNovoVeiculo);
		} catch (VeiculoNaoCadastrado e1) {
			e1.printStackTrace();
		}
		if (veiculoExistente != null) {
			// Veículo já existe
			return false;
		}

		// this.veiculos.add(v);
		try {
			DatabaseHelper.insertVeiculoOnDatabase(v);
		} catch (SQLException e) {
			System.err.println("Erro ao inserir veículo no banco de dados [breakpoint]");
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Veiculo> pesquisarMoto(int cilindrada) {
		// Retorna as motos com cilindrada maior ou igual a pesquisada.

		// ArrayList<Veiculo> motosArrayList = new ArrayList<Veiculo>();
		// for (Veiculo veiculo : this.veiculos) {
		// if (veiculo.getClass() == Moto.class) {
		// Moto moto = (Moto) veiculo;
		// if (moto.getCilindrada() >= cilindrada) {
		// motosArrayList.add(veiculo);
		// }
		// }
		// }

		ArrayList<Veiculo> motosArrayList = new ArrayList<Veiculo>();

		// Obter a lista de veiculos
		try {
			ArrayList<Integer> veiculosArrayList = DAOVeiculo.list();
			for (int id : veiculosArrayList) {
				Veiculo resultVeiculo = DAOVeiculo.get(id);
				if (resultVeiculo.getClass() == Moto.class) {
					if (((Moto) resultVeiculo).getCilindrada() >= cilindrada) {
						motosArrayList.add(resultVeiculo);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return motosArrayList;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) {
		// tipo de carro
		// 1 (passeio), 2 (SUV), 3 (pickup)
		// ArrayList<Veiculo> carroArrayList = new ArrayList<Veiculo>();
		// for (Veiculo veiculo : this.veiculos) {
		// if (veiculo.getClass() == Carro.class) {
		// Carro carro = (Carro) veiculo;
		// if (carro.getTipo() == tipoCarro) {
		// carroArrayList.add(carro);
		// }
		// }
		// }

		ArrayList<Veiculo> carroArrayList = new ArrayList<Veiculo>();

		// Obter a lista de veiculos
		try {
			ArrayList<Integer> veiculosArrayList = DAOVeiculo.list();
			for (int id : veiculosArrayList) {
				Veiculo resultVeiculo = DAOVeiculo.get(id);
				if (resultVeiculo.getClass() == Carro.class) {
					if (((Carro) resultVeiculo).getTipo() == tipoCarro) {
						carroArrayList.add(resultVeiculo);
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return carroArrayList;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCaminhao(int carga) {

		// ArrayList<Veiculo> caminhaoArrayList = new ArrayList<Veiculo>();
		// for (Veiculo veiculo : this.veiculos) {
		// if (veiculo.getClass() == Caminhao.class) {
		// Caminhao caminhao = (Caminhao) veiculo;
		// if (caminhao.getCarga() >= carga) {
		// caminhaoArrayList.add(caminhao);
		// }
		// }
		// }

		ArrayList<Veiculo> caminhaoArrayList = new ArrayList<Veiculo>();

		// Obter a lista de veiculos
		try {
			ArrayList<Integer> veiculosArrayList = DAOVeiculo.list();
			for (int id : veiculosArrayList) {
				Veiculo resultVeiculo = DAOVeiculo.get(id);
				if (resultVeiculo.getClass() == Caminhao.class) {
					if (((Caminhao) resultVeiculo).getCarga() >= carga) {
						caminhaoArrayList.add(resultVeiculo);
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return caminhaoArrayList;
	}

	@Override
	public ArrayList<Veiculo> pesquisarOnibus(int passageiros) {

		// ArrayList<Veiculo> onibusArrayList = new ArrayList<Veiculo>();
		// for (Veiculo veiculo : this.veiculos) {
		// if (veiculo.getClass() == Onibus.class) {
		// Onibus onibus = (Onibus) veiculo;
		// if (onibus.getCapacidadePassageiros() >= passageiros) {
		// onibusArrayList.add(onibus);
		// }
		// }
		// }

		ArrayList<Veiculo> onibusArrayList = new ArrayList<Veiculo>();

		// Obter a lista de veiculos
		try {
			ArrayList<Integer> veiculosArrayList = DAOVeiculo.list();
			for (int id : veiculosArrayList) {
				Veiculo resultVeiculo = DAOVeiculo.get(id);
				if (resultVeiculo.getClass() == Onibus.class) {
					Onibus o = (Onibus) resultVeiculo;
					if (o.getCapacidadePassageiros() >= passageiros) {
						onibusArrayList.add(resultVeiculo);
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return onibusArrayList;
	}

	@Override
	public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado {
		// retorna -1 caso o veiculo nao seja encontrado
		Veiculo veiculo = pesquisar(placa);
		if (veiculo == null) {
			throw new VeiculoNaoCadastrado();
		}
		double v = veiculo.calcularAluguel(placa, dias);

		return v;
	}

	@Override
	public boolean registrarAluguel(String placa, Date data, int dias, int cpf)
			throws VeiculoNaoCadastrado, ClienteNaoCadastrado, VeiculoAlugado {

		Veiculo veiculo = pesquisar(placa);
		if (veiculo == null) {
			throw new VeiculoNaoCadastrado();
		}

		// Checar se o cliente existe
		Cliente cliente = null;
		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
		try {
			listaCliente = DatabaseHelper.getAllClientesFromDatabase();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Cliente c : listaCliente) {
			if (c.getCpf() == cpf) {
				cliente = c;
			}
		}
		if (cliente == null) {
			throw new ClienteNaoCadastrado();
		}

		Calendar calendar = Calendar.getInstance();

		ArrayList<Aluguel> listaAlugueis;
		try {
			listaAlugueis = DatabaseHelper.getAllAlugueisFromDatabase();

			for (Aluguel aluguel : listaAlugueis) {
				// Pula se o aluguel não for do veiculo
				if (!aluguel.getVeiculoAlugado().getPlaca().equals(placa)) {
					continue;
				}
				// Pula se o aluguel não for do mesmo cliente
				if (aluguel.getCpfCliente() != cpf) {
					continue;
				}

				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());
				Date dataFinalAluguel = calendar.getTime();

				// Pular a iteração com esse aluguel quando o mesmo já foi devolvido
				if (aluguel.isDevolvido() == true) {
					continue;
				}

				for (int i = 0; i <= dias; i++) {
					calendar.setTime(data);
					calendar.add(Calendar.DATE, i);
					Date dataVerificacao = calendar.getTime();

					// Verificação de datas
					// a1 <- data de inicio
					// a2 <- data de fim
					// d <- data de verificação (data do aluguel)
					// a1 == d, a2 > d
					// a1 < d, a2 > d
					// a1 < d, a2 == d
					if (dataVerificacao.equals(aluguel.getDataInicio()) && dataVerificacao.before(dataFinalAluguel)) {
						// Já existe um veículo alugado. Lançar exceção, nesses casos onde há conflito
						// de datas
						throw new VeiculoAlugado();
					}
					if (dataVerificacao.after(aluguel.getDataInicio()) && dataVerificacao.before(dataFinalAluguel)) {
						// Já existe um veículo alugado. Lançar exceção, nesses casos onde há conflito
						// de datas
						throw new VeiculoAlugado();
					}
					if (dataVerificacao.after(aluguel.getDataInicio()) && dataVerificacao.equals(dataFinalAluguel)) {
						// Já existe um veículo alugado. Lançar exceção, nesses casos onde há conflito
						// de datas
						throw new VeiculoAlugado();
					}
				}
			}

			Aluguel aluguel = new Aluguel();
			aluguel.setDataInicio(data);
			aluguel.setDias(dias);
			aluguel.setVeiculoAlugado(veiculo);
			aluguel.setCpfCliente(cpf);
			aluguel.setDevolvido(false);
			// alugueis.add(aluguel);
			SimpleDateFormat formatter = DatabaseHelper.retrieveDatabaseFormatter();
			int idVeiculo = DAOVeiculo.search(veiculo.getPlaca());
			DAOAluguel.insert(data.getTime(), dias, idVeiculo, cpf, false);

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean registrarDevolucao(String placa, Date dataDevolucao, int cpf)
			throws VeiculoNaoCadastrado, VeiculoNaoAlugado {

		Veiculo veiculo = pesquisar(placa);
		if (veiculo == null) {
			throw new VeiculoNaoCadastrado();
		}

		try {
			ArrayList<Aluguel> listaAlugueis = DatabaseHelper.getAllAlugueisFromDatabase();
			ArrayList<Integer> listaIds = DAOAluguel.list();
			for (int i = 0; i < listaAlugueis.size(); i++) {
				Aluguel a = listaAlugueis.get(i);
				int idVeiculo = DAOVeiculo.search(a.getVeiculoAlugado().getPlaca());
				if (a.getVeiculoAlugado().getPlaca().equals(placa)) {
					// Verificar se o CPF do cliente que está devolvendo o veículo é o mesmo que
					// alugou
					if (a.getCpfCliente() == cpf) {
						// Verificar a data de devolução - ela deve ser maior ou igual a data de início
						// do aluguel
						if (a.getDataInicio().before(dataDevolucao) || a.getDataInicio().equals(dataDevolucao)) {
							// Proibir a devolução de um item já devolvido
							if (a.isDevolvido() != true) {
								DAOAluguel.update(listaIds.get(i).intValue(), a.getDataInicio().getTime(), a.getDias(),
										idVeiculo, a.getCpfCliente(), true);
								return true;
							}
						}
					}
				}
			}
		} catch (

		Exception e) {

			e.printStackTrace();
		}

		// Ao chegar ao final do processo, e não ter encontrado o veículo, lançar
		// exceção
		throw new VeiculoNaoAlugado();
	}

	@Override
	public void depreciarVeiculos(int tipo, double taxaDepreciacao) {

		if (taxaDepreciacao <= 0) {
			return;
		}

		ArrayList<Veiculo> veiculosArrayList = DatabaseHelper.getAllVehiclesFromDatabase();

		for (Veiculo veiculo : veiculosArrayList) {
			switch (tipo) {
				case 1:
					if (veiculo.getClass() == Moto.class) {
						veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
					}
					break;
				case 2:
					if (veiculo.getClass() == Carro.class) {
						veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
					}
					break;
				case 3:
					if (veiculo.getClass() == Caminhao.class) {
						veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
					}
					break;
				case 4:
					if (veiculo.getClass() == Onibus.class) {
						veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
					}
					break;
				default:
					veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
					break;
			}
		}

		ArrayList<Integer> idsList;
		try {
			idsList = DAOVeiculo.list();
			for (int i = 0; i < veiculosArrayList.size(); i++) {
				DatabaseHelper.updateVeiculoOnDatabase(idsList.get(i), veiculosArrayList.get(i));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void aumentarDiaria(int tipo, double taxaAumento) {
		if (taxaAumento <= 0) {
			return;
		}

		ArrayList<Veiculo> veiculosArrayList = DatabaseHelper.getAllVehiclesFromDatabase();

		for (Veiculo veiculo : veiculosArrayList) {
			switch (tipo) {
				case 1:
					if (veiculo.getClass() == Moto.class) {
						veiculo.setValorDiaria(veiculo.getValorDiaria() * (1 + taxaAumento));
					}
					break;
				case 2:
					if (veiculo.getClass() == Carro.class) {
						veiculo.setValorDiaria(veiculo.getValorDiaria() * (1 + taxaAumento));
					}
					break;
				case 3:
					if (veiculo.getClass() == Caminhao.class) {
						veiculo.setValorDiaria(veiculo.getValorDiaria() * (1 + taxaAumento));
					}
					break;
				case 4:
					if (veiculo.getClass() == Onibus.class) {
						veiculo.setValorDiaria(veiculo.getValorDiaria() * (1 + taxaAumento));
					}
					break;
				default:
					veiculo.setValorDiaria(veiculo.getValorDiaria() * (1 + taxaAumento));
					break;
			}
		}

		ArrayList<Integer> idsList;
		try {
			idsList = DAOVeiculo.list();
			for (int i = 0; i < veiculosArrayList.size(); i++) {
				DatabaseHelper.updateVeiculoOnDatabase(idsList.get(i), veiculosArrayList.get(i));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public double faturamentoTotal(int tipo, Date inicio, Date fim) {
		double faturamento = 0;
		Calendar calendar = Calendar.getInstance();

		ArrayList<Aluguel> listaAlugueis;
		try {
			listaAlugueis = DatabaseHelper.getAllAlugueisFromDatabase();
			for (Aluguel aluguel : listaAlugueis) {
				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (aluguel.getDataInicio().equals(fim) || aluguel.getDataInicio().before(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						switch (tipo) {
							case 1:
								if (veiculo.getClass() == Moto.class) {
									Moto moto = ((Moto) veiculo);
									double valorComSeguro = moto.calcularAluguel(moto.getPlaca(), aluguel.getDias());
									faturamento += valorComSeguro;
								}
								break;
							case 2:
								if (veiculo.getClass() == Carro.class) {
									Carro carro = ((Carro) veiculo);
									double valorComSeguro = carro.calcularAluguel(carro.getPlaca(), aluguel.getDias());
									faturamento += valorComSeguro;
								}
								break;
							case 3:
								if (veiculo.getClass() == Caminhao.class) {
									Caminhao caminhao = ((Caminhao) veiculo);
									double valorComSeguro = caminhao.calcularAluguel(caminhao.getPlaca(),
											aluguel.getDias());
									faturamento += valorComSeguro;
								}
								break;
							case 4:
								if (veiculo.getClass() == Onibus.class) {
									Onibus onibus = ((Onibus) veiculo);
									double valorComSeguro = onibus.calcularAluguel(onibus.getPlaca(),
											aluguel.getDias());
									faturamento += valorComSeguro;
								}
								break;
							default:
								double valorComSeguro = veiculo.calcularAluguel(veiculo.getPlaca(), aluguel.getDias());
								faturamento += valorComSeguro;
								break;
						}
					}
				}
			}
		} catch (Exception e) {
			return faturamento;
		}

		return faturamento;
	}

	@Override
	public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
		int diarias = 0;
		Calendar calendar = Calendar.getInstance();

		ArrayList<Aluguel> listaAlugueis;
		try {
			listaAlugueis = DatabaseHelper.getAllAlugueisFromDatabase();
			for (Aluguel aluguel : listaAlugueis) {
				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (aluguel.getDataInicio().equals(fim) || aluguel.getDataInicio().before(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						switch (tipo) {
							case 1:
								if (veiculo.getClass() == Moto.class) {
									diarias += aluguel.getDias();
								}
								break;
							case 2:
								if (veiculo.getClass() == Carro.class) {
									diarias += aluguel.getDias();
								}
								break;
							case 3:
								if (veiculo.getClass() == Caminhao.class) {
									diarias += aluguel.getDias();
								}
								break;
							case 4:
								if (veiculo.getClass() == Onibus.class) {
									diarias += aluguel.getDias();
								}
								break;
							default:
								diarias += aluguel.getDias();
								break;
						}
					}
				}
			}
		} catch (Exception e) {
			return diarias;
		}

		return diarias;
	}

	public Veiculo pesquisar(String placa) throws VeiculoNaoCadastrado {
		// for (Veiculo veiculo : veiculos) {
		// if (veiculo.getPlaca().equals(placa)) {
		// return veiculo;
		// }
		// }
		Veiculo resultVeiculo = null;
		try {
			int searchResultId = DAOVeiculo.search(placa);
			if (searchResultId != -1) {
				resultVeiculo = DAOVeiculo.get(searchResultId);
			}
		} catch (SQLException e) {
			System.err.println("Erro na pesquisa SQL [breakpoint]");
		} catch (Exception e) {
			System.err.println("Erro na pesquisa [breakpoint]");
		}
		if (resultVeiculo == null) {
			throw new VeiculoNaoCadastrado();
		}
		return resultVeiculo;
	}

	@Override
	protected Cliente pesquisarCliente(int cpf) {
		ArrayList<Cliente> listaClientes;
		try {
			listaClientes = DatabaseHelper.getAllClientesFromDatabase();
			for (Cliente c : listaClientes) {
				if (c.getCpf() == cpf) {
					return c;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean inserir(Cliente c) {
		Cliente cliente = pesquisarCliente(c.getCpf());
		if (cliente == null) {
			try {
				DAOCliente.insert(c.getCpf(), c.getNome());
				return true;
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return false;
	}

	public void removerTudo() {
		try {
			DAOAluguel.removeall();
			DAOVeiculo.removeall();
			DAOCliente.removeall();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Tudo removido :thumbsup:");
		}
	}
}
