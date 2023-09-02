package thiagoOliveiraDaSilva.locadoraBD;

public class Onibus extends Veiculo {

	private int capacidadePassageiros;

	/**
	 * @return the capacidadePassageiros
	 */
	public int getCapacidadePassageiros() {
		return capacidadePassageiros;
	}

	/**
	 * @param capacidadePassageiros the capacidadePassageiros to set
	 */
	public void setCapacidadePassageiros(int capacidadePassageiros) {
		this.capacidadePassageiros = capacidadePassageiros;
	}

	public Onibus(String marca, String modelo, int ano, double valorBem, double diaria, String placa, int capacidade) {
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setAnoFabricacao(ano);
		this.setValorBem(valorBem);
		this.setValorDiaria(diaria);
		this.setPlaca(placa);
		this.setCapacidadePassageiros(capacidade);
	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		
		Veiculo veiculo = pesquisa(placa);
		if (veiculo == null) {
			return -1;
		}
		double diaria = veiculo.getValorDiaria();
		this.setSeguro((this.getValorAvaliado() * (20.0 / 100.0)) / 365);

		return (diaria + getSeguro()) * dias;
	}

}
