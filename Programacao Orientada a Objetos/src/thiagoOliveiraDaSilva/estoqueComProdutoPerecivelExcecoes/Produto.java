package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public class Produto {
	private double precoCompra;
	private double precoVenda, lucro;
	private int codigo;
	private int estoqueMinimo, quantidade;
	private String descricao;
	private Fornecedor fornecedor;

	public Produto(int cod, String desc, int min, double lucro, Fornecedor forn) {

		this.codigo = cod;
		this.descricao = desc;
		this.lucro = lucro;
		this.fornecedor = forn;
		
	}

	/**
	 * @return the lucro
	 */
	public double getLucro() {
		return lucro;
	}

	/**
	 * @param lucro the lucro to set
	 */
	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	/**
	 * @return the precoCompra
	 */
	public double getPrecoDeCompra() {
		return precoCompra;
	}

	/**
	 * @param precoCompra the precoCompra to set
	 */
	public void setPrecoDeCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	/**
	 * @return the precoVenda
	 */
	public double getPrecoDeVenda() {
		return precoVenda;
	}

	/**
	 * @param precoVenda the precoVenda to set
	 */
	public void setPrecoDeVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the estoqueMin
	 */
	public int getEstoqueMinimo() {
		return this.estoqueMinimo;
	}

	/**
	 * @param estoqueMin the estoqueMin to set
	 */
	public void setEstoqueMinimo(int estoqueMin) {
		this.estoqueMinimo = estoqueMin;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	void compra(int quant, double val) {
		// (10 * 2.5 + 10 *. 7.5) / 20 = R$5.0.
		if (quant < 0 || val < 0) {
			return;
		} else {
			setPrecoDeCompra(((getQuantidade() * getPrecoDeCompra()) + (quant * val)) / (quant + getQuantidade()));
			double fatorLucro = lucro + 1;
			setPrecoDeVenda(fatorLucro * getPrecoDeCompra());
			setQuantidade(quant + getQuantidade());
		}

	}

	double venda(int quant) throws DadosInvalidos {
		if ((getQuantidade() - quant) >= estoqueMinimo) {
			setQuantidade(getQuantidade() - quant);
			double l = lucro + 1;
			setPrecoDeVenda(l * getPrecoDeCompra());
			return quant * getPrecoDeVenda();

		} else {
			return -1;
		}
	}

}
