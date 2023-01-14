package thiagoOliveiraDaSilva.locadoraBD;

import java.util.Date;

public class Aluguel {
	private Date dataInicio;
	private int dias;
	private Veiculo veiculoAlugado;
	private int cpfCliente;
	private boolean devolvido;
	
	
	
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
	public int getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(int cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	/**
	 * @return the devolvido
	 */
	public boolean isDevolvido() {
		return devolvido;
	}
	/**
	 * @param devolvido the devolvido to set
	 */
	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}
	
	

}
