package thiagoOliveiraDaSilva.estoque;

public class Produto {
	private double precoCompra;
	private double precoVenda, lucro;
	private int codigo;
	private int estoqueMin, quantidade;
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
	public double getPrecoCompra() {
		return precoCompra;
	}

	/**
	 * @param precoCompra the precoCompra to set
	 */
	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	/**
	 * @return the precoVenda
	 */
	public double getPrecoVenda() {
		return precoVenda;
	}

	/**
	 * @param precoVenda the precoVenda to set
	 */
	public void setPrecoVenda(double precoVenda) {
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
	public int getEstoqueMin() {
		return estoqueMin;
	}

	/**
	 * @param estoqueMin the estoqueMin to set
	 */
	public void setEstoqueMin(int estoqueMin) {
		this.estoqueMin = estoqueMin;
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
			setPrecoCompra(((getQuantidade() * getPrecoCompra()) + (quant * val)) / (quant + getQuantidade()));
			setQuantidade(quant + getQuantidade());
		}

	}

	double venda(int quant) {
		if ((getQuantidade() - quant) >= estoqueMin) {
			setQuantidade(getQuantidade() - quant);
			double l = lucro + 1;
			setPrecoVenda(l * getPrecoCompra());
			return quant * getPrecoVenda();

		} else {
			return -1;
		}
	}

}
