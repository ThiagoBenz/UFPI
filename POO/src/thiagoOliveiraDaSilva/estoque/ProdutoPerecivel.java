package thiagoOliveiraDaSilva.estoque;

import java.util.Calendar;
import java.util.Date;

public class ProdutoPerecivel extends Produto{
	
	
	private double lucro;
	private int codigo;
	private int estoqueMin, quantidade;
	private String descricao;
	private Fornecedor forn;
	private Date validade ;
	

	public ProdutoPerecivel(int cod, String desc, int min, double lucro,
			Fornecedor forn, Date val) {
		super(cod, desc, min, lucro, forn);
		
		
		this.setCodigo(cod);
		this.setDescricao(desc);
		this.setEstoqueMin(min);
		this.setLucro(lucro);
		this.setForn(forn);
		this.setValidade(val);
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
	/**
	 * @return the forn
	 */
	public Fornecedor getForn() {
		return forn;
	}
	/**
	 * @param forn the forn to set
	 */
	public void setForn(Fornecedor forn) {
		this.forn = forn;
	}
	/**
	 * @return the val
	 */
	public Date getValidade() {
		return validade;
	}
	/**
	 * @param val the val to set
	 */
	public void setValidade(Date val) {
		this.validade = val;
	}
	
	double venda(int quant) {
		if ((getQuantidade() - quant) >= estoqueMin) {
			Date d = Calendar.getInstance().getTime();
			if (d.after(getValidade())) {
				setQuantidade(getQuantidade() - quant);
				double l = lucro + 1;
				setPrecoVenda(l * getPrecoCompra());
				return quant * getPrecoVenda();
			}else {
				return -1;
			}
			

		} else {
			return -1;
		}
	}
	
	
	

}
