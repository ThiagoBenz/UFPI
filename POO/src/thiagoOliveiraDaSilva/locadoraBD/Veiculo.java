package thiagoOliveiraDaSilva.locadoraBD;

import java.util.ArrayList;

public abstract class Veiculo {

	private String marca, modelo, placa;
	private int anoFabricacao;
	private double valorDiaria, valorBem, seguro;
	private int tipo;
	ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

	
	
	
	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the seguro
	 */
	public double getSeguro() {
		return seguro;
	}

	/**
	 * @param seguro the seguro to set
	 */
	public void setSeguro(Double seguro) {
		this.seguro = seguro;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the anoFabricacao
	 */
	public int getAnoDeFabricacao() {
		return anoFabricacao;
	}

	/**
	 * @param anoFabricacao the anoFabricacao to set
	 */
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	/**
	 * @return the valorDiaria
	 */
	public double getValorDiaria() {
		return valorDiaria;
	}

	/**
	 * @param valorDiaria the valorDiaria to set
	 */
	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	/**
	 * @return the valorBem
	 */
	public double getValorAvaliado() {
		return valorBem;
	}

	/**
	 * @param valorBem the valorBem to set
	 */
	public void setValorBem(double valorBem) {
		this.valorBem = valorBem;
	}

	public abstract double calcularAluguel(String placa, int dias);

	public Veiculo pesquisa(String placa) {
		ArrayList<Veiculo> listaVeiculos = DatabaseHelper.getAllVehiclesFromDatabase();
		for (Veiculo veiculo : listaVeiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				return veiculo;
			}
		}

		return null;
	}

}
