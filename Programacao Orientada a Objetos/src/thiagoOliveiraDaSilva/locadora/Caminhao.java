package thiagoOliveiraDaSilva.locadora;

public class Caminhao extends Veiculo {

	private double carga;

	/**
	 * @return the carga
	 */
	public double getCarga() {
		return carga;
	}

	/**
	 * @param carga the carga to set
	 */
	public void setCarga(double carga) {
		this.carga = carga;
	}

	public Caminhao(String marca, String modelo, int ano, double valorBem, double diaria, String placa, double carga) {

		this.setMarca(marca);
		this.setModelo(modelo);
		this.setAnoFabricacao(ano);
		this.setValorBem(valorBem);
		this.setValorDiaria(diaria);
		this.setPlaca(placa);
		this.setCarga(carga);

	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		
		Veiculo veiculo =  pesquisa(placa);
		if (veiculo == null) {
			return -1;
		}
		double diaria = veiculo.getValorDiaria();
		
		this.setSeguro((this.getValorAvaliado() * (8 / 100)) / 365);

		return (diaria + getSeguro()) * dias;
	}

}
