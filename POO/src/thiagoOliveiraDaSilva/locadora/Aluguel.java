package thiagoOliveiraDaSilva.locadora;

import java.util.Date;

public class Aluguel {
	private Date dataInicio;
	private int dias;
	private Veiculo veiculoAlugado;
	
	
	
	
	/**
	 * @return the veiculoAlugado
	 */
	public Veiculo getVeiculoAlugado() {
		return veiculoAlugado;
	}
	/**
	 * @param veiculoAlugado the veiculoAlugado to set
	 */
	public void setVeiculoAlugado(Veiculo veiculoAlugado) {
		this.veiculoAlugado = veiculoAlugado;
	}
	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return the dias
	 */
	public int getDias() {
		return dias;
	}
	/**
	 * @param dias the dias to set
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}
	
	

}
