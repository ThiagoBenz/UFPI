package thiagoOliveiraDaSilva.locadoraBD;

public class Moto extends Veiculo {

	private int cilindrada;

	/**
	 * @return the cilindrada
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	/**
	 * @param cilindrada the cilindrada to set
	 */
	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	
	public Moto(String marca, String modelo, int ano, double valorBem, double diaria,
			String placa, int cilindrada) {
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setAnoFabricacao(ano);
		this.setValorBem(valorBem);
		this.setValorDiaria(diaria);
		this.setPlaca(placa);
		this.setCilindrada(cilindrada);
		
	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		Veiculo veiculo =  pesquisa(placa);
		if (veiculo == null) { return -1; }
		double diaria = veiculo.getValorDiaria();
		this.setSeguro((this.getValorAvaliado() * (11.0 / 100.0)) / 365);
		return (diaria+ getSeguro())*dias;
	}
}
