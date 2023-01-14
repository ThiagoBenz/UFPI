package thiagoOliveiraDaSilva.locadora;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MinhaLocadora extends Locadora {

	@Override
	public boolean inserir(Veiculo v) {
		// Passo 1: validar veiculo. Se nao for valido, retornar falso.
		if (v == null) {
			return false;
		} else if (pesquisar(v.getPlaca()) != null) {
			return false;
		}
		// Passo 2: verificar se o veiculo ja existe
		this.veiculos.add(v);
		return true;
	}

	@Override
	public ArrayList<Veiculo> pesquisarMoto(int cilindrada) {
		// Retorna as motos com cilindrada maior ou igual a pesquisada.
		ArrayList<Veiculo> motosArrayList = new ArrayList<Veiculo>();
		for (Veiculo veiculo : this.veiculos) {
			if (veiculo.getClass() == Moto.class) {
				Moto moto = (Moto) veiculo;
				if (moto.getCilindrada() >= cilindrada) {
					motosArrayList.add(veiculo);
				}
			}
		}

		return motosArrayList;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) {
		// tipo de carro
		// 1 (passeio), 2 (SUV), 3 (pickup)
		ArrayList<Veiculo> carroArrayList = new ArrayList<Veiculo>();
		for (Veiculo veiculo : this.veiculos) {
			if (veiculo.getClass() == Carro.class) {
				Carro carro = (Carro) veiculo;
				if (carro.getTipo() == tipoCarro) {
					carroArrayList.add(carro);
				}
			}
		}
		return carroArrayList;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCaminhao(int carga) {

		ArrayList<Veiculo> caminhaoArrayList = new ArrayList<Veiculo>();
		for (Veiculo veiculo : this.veiculos) {
			if (veiculo.getClass() == Caminhao.class) {
				Caminhao caminhao = (Caminhao) veiculo;
				if (caminhao.getCarga() >= carga) {
					caminhaoArrayList.add(caminhao);
				}
			}

		}
		return caminhaoArrayList;
	}

	@Override
	public ArrayList<Veiculo> pesquisarOnibus(int passageiros) {

		ArrayList<Veiculo> onibusArrayList = new ArrayList<Veiculo>();
		for (Veiculo veiculo : this.veiculos) {

			if (veiculo.getClass() == Onibus.class) {
				Onibus onibus = (Onibus) veiculo;
				if (onibus.getCapacidadePassageiros() >= passageiros) {
					onibusArrayList.add(onibus);
				}
			}
		}
		return onibusArrayList;
	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		// retorna -1 caso o veiculo nao seja encontrado
		Veiculo veiculo = pesquisar(placa);
		if (veiculo == null) {
			return -1;
		}
		double v = veiculo.calcularAluguel(placa, dias);

		return v;
	}

	@Override
	public boolean registrarAluguel(String placa, Date data, int dias, int cpf) {

		Veiculo veiculo = pesquisar(placa);
		if (veiculo == null) {
			return false;
		}

		Calendar calendar = Calendar.getInstance();

		for (Aluguel aluguel : this.alugueis) {

			for (int i = 0; i < dias; i++) {
				calendar.setTime(data);
				calendar.add(Calendar.DATE, i);
				Date dataChecagemDate = calendar.getTime();
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());
				Date dataFinalAluguel = calendar.getTime();
				if (dataChecagemDate.after(aluguel.getDataInicio()) && dataChecagemDate.before(dataFinalAluguel)) {
					return false;
				}
				if (dataChecagemDate.equals(aluguel.getDataInicio()) || dataChecagemDate.equals(dataFinalAluguel)) {
					return false;
				}
			}
		}

		Aluguel aluguel = new Aluguel();
		aluguel.setDataInicio(data);
		aluguel.setDias(dias);
		aluguel.setVeiculoAlugado(veiculo);
		alugueis.add(aluguel);

		return true;
	}

	@Override
	public boolean registrarDevolucao(String placa) {
		
		Veiculo veiculo = pesquisar(placa);
		if (veiculo == null) {
			return false;
		}

		for (Aluguel aluguel : this.alugueis) {
			if (aluguel.getVeiculoAlugado().equals(veiculo)) {
				this.alugueis.remove(aluguel);
				return true;
			}
		}

		return false;
	}

	@Override
	public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
		
		if (taxaDepreciacao <= 0) {
			return;
		} else if (tipo < 0 || tipo > 4) {
			return;
		}

		switch (tipo) {
		case 0:
			for (Veiculo veiculo : veiculos) {
				veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
			}
			break;
		case 1:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Moto.class) {
					veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
				}
			}
			break;
		case 2:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Carro.class) {
					veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
				}
			}

			break;
		case 3:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Caminhao.class) {
					veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
				}
			}

			break;
		case 4:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Onibus.class) {
					veiculo.setValorBem(veiculo.getValorAvaliado() * (1 - taxaDepreciacao));
				}
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void aumentarDiaria(int tipo, double taxaAumento) {

		if (taxaAumento <= 0) {
			return;
		} else if (tipo > 4 || tipo < 0) {
			return;
		}

		switch (tipo) {
		case 0:
			for (Veiculo veiculo : veiculos) {
				veiculo.setValorDiaria(veiculo.getValorAvaliado() * (1 + taxaAumento));
			}
			break;

		case 1:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Moto.class) {
					veiculo.setValorDiaria(veiculo.getValorAvaliado() * (1 + taxaAumento));
				}
			}
			break;

		case 2:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Carro.class) {
					veiculo.setValorDiaria(veiculo.getValorAvaliado() * (1 + taxaAumento));
				}
			}
			break;

		case 3:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Caminhao.class) {
					veiculo.setValorDiaria(veiculo.getValorAvaliado() * (1 + taxaAumento));
				}
			}
			break;

		case 4:
			for (Veiculo veiculo : veiculos) {
				if (veiculo.getClass() == Onibus.class) {
					veiculo.setValorDiaria(veiculo.getValorAvaliado() * (1 + taxaAumento));
				}
			}
			break;

		default:
			break;
		}

	}

	@Override
	public double faturamentoTotal(int tipo, Date inicio, Date fim) {

		// se o tipo nao for valido o metodo retorna -1
		if (tipo < 0 || tipo > 4) {
			return -1;
		}
		double faturamento = 0;
		Calendar calendar = Calendar.getInstance();

		switch (tipo) {
		case 0:
			for (Aluguel aluguel : alugueis) {
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						faturamento += veiculo.getValorDiaria();
					}
				}
			}
			break;

		case 1:
			for (Aluguel aluguel : alugueis) {

				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Moto.class) {
							faturamento += veiculo.getValorDiaria();
						}
					}
				}
			}
			break;

		case 2:
			for (Aluguel aluguel : alugueis) {

				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Carro.class) {
							faturamento += veiculo.getValorDiaria();
						}
					}
				}
			}
			break;

		case 3:
			for (Aluguel aluguel : alugueis) {

				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Caminhao.class) {
							faturamento += veiculo.getValorDiaria();
						}
					}
				}
			}
			break;

		case 4:
			for (Aluguel aluguel : alugueis) {

				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Onibus.class) {
							faturamento += veiculo.getValorDiaria();
						}
					}
				}
			}
			break;

		default:
			break;
		}

		return faturamento;
	}

	@Override
	public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {

		// metodo retorna -1 caso o tipo nao seja valido
		if (tipo < 0 || tipo > 4) {
			return -1;
		}
		
		int diarias = 0;
		Calendar calendar = Calendar.getInstance();
		
		switch (tipo) {
		
		case 0:
			for (Aluguel aluguel : alugueis) {
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						diarias += aluguel.getDias();
					}
				}
			}
			break;
			
		case 1:
			for (Aluguel aluguel : alugueis) {
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Moto.class) {
							diarias += aluguel.getDias();
						}
						
					}
				}
			}
			break;
			
		case 2:
			for (Aluguel aluguel : alugueis) {
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Carro.class) {
							diarias += aluguel.getDias();
						}
						
					}
				}
			}
			break;
			
		case 3:
			for (Aluguel aluguel : alugueis) {
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Caminhao.class) {
							diarias += aluguel.getDias();
						}
						
					}
				}
			}
			break;
			
		case 4:
			for (Aluguel aluguel : alugueis) {
				calendar.setTime(aluguel.getDataInicio());
				calendar.add(Calendar.DATE, aluguel.getDias());

				Date dataFinalDeAluguelDate = calendar.getTime();

				if (aluguel.getDataInicio().equals(inicio) || aluguel.getDataInicio().after(inicio)) {
					if (dataFinalDeAluguelDate.equals(fim) || dataFinalDeAluguelDate.after(fim)) {
						
						Veiculo veiculo = aluguel.getVeiculoAlugado();
						if (veiculo.getClass() == Onibus.class) {
							diarias += aluguel.getDias();
						}
						
					}
				}
			}
			break;

		
		
		default:
			break;
		}

		return diarias;
	}

	public Veiculo pesquisar(String placa) {
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				return veiculo;
			}
		}
		return null;
	}

}
