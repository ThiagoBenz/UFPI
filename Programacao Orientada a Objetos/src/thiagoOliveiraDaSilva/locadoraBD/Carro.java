package thiagoOliveiraDaSilva.locadoraBD;

public class Carro extends Veiculo {
	/**
	 * @return the categoria
	 */
	public int getCategoria() {
		return this.getTipo();
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(int categoria) {
		this.setTipo(categoria);
	}

	public Carro(String marca, String modelo, int ano, double valorBem , double valorDiaria ,
			String placa, int categoria) {
		
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setAnoFabricacao(ano);
		this.setValorBem(valorBem);
		this.setValorDiaria(valorDiaria);
		this.setCategoria(categoria);
		this.setPlaca(placa);
	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		
		Veiculo veiculo =  pesquisa(placa);
		if (veiculo == null) {
			return -1;
		}
		double diaria = veiculo.getValorDiaria();
		 this.setSeguro((this.getValorAvaliado() * (3.0 / 100.0)) / 365);
		
		return (diaria+ getSeguro())*dias;
	}

}
