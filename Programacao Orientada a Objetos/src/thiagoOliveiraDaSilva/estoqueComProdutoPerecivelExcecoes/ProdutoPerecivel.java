package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProdutoPerecivel extends Produto{
	
	
	private double lucro;
	private int codigo;
	private int estoqueMin, quantidade;
	private String descricao;
	private Fornecedor forn;
	private Date validade ;
	
	private ArrayList<Lote> lotes = new ArrayList<>();
	

	public ProdutoPerecivel(int cod, String desc, int min, double lucro,
			Fornecedor forn) {
		super(cod, desc, min, lucro, forn);
		
		
		this.setCodigo(cod);
		this.setDescricao(desc);
		this.setEstoqueMin(min);
		this.setLucro(lucro);
		this.setForn(forn);
		//this.setValidade(val);
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
	
	double venda(int quant) throws DadosInvalidos {
		// Verificar se está tentando vender uma quantidade inválida
		if (quant < 0) {
			throw new DadosInvalidos();
		}
		
		// Verificar quantidade em estoque
		int totalProdutosEmEstoque = 0;
		for (Lote lote : lotes) {
			totalProdutosEmEstoque += lote.getQuantidade();
		}
		if ((totalProdutosEmEstoque - quant) < estoqueMin) {
			throw new DadosInvalidos();
		}
		
		// Verificar se há lotes na validade o suficiente
		int produtosEmValidade = 0;
		Date dataAtual = new Date();
		for (Lote lote : lotes) {
			if (!lote.getValidade().before(dataAtual)) {
				produtosEmValidade += lote.getQuantidade();
			}
		}
		if (produtosEmValidade <= quant) {
			throw new DadosInvalidos();
		}
		
		// Aplicar algoritmo de vendas, diminuindo lotes
		int totalAVender = quant;
		Date d = new Date();
		while (quant > 0) {
			// Obter lote mais antigo
			Lote maisAntigo = lotes.get(0);
			for (Lote lote : lotes) {
				// Verifica se o lote está na validade
				if (!lote.getValidade().before(d)) {
					// Pula se sim
					continue;
				}
				// Verifica se o lote é mais antigo que o cursor
				if (lote.getValidade().before(maisAntigo.getValidade())) {
					maisAntigo = lote;
				}
			}
			
			// Com lote mais antigo, subtrair o máximo possível do lote
			int maximoPossivel = Math.min(totalAVender, maisAntigo.getQuantidade());
			totalAVender -= maximoPossivel;
			maisAntigo.setQuantidade(maisAntigo.getQuantidade() - maximoPossivel);			
			
			// Se lote está vazio... remover ele da lista de lotes.
			if (maisAntigo.getQuantidade() <= 0) {
				lotes.remove(maisAntigo);
			}
		}
		
		
		double fatorLucro = lucro + 1;
		double novoPrecoDeVenda = fatorLucro * getPrecoDeCompra();
	//	double novoPrecoDeCompra = ();
		// Atualizar preço de compra
		
		// Atualizar preço de venda
		setPrecoDeVenda(novoPrecoDeVenda);
		double lucroPresumido = quant * getPrecoDeVenda();
		return lucroPresumido;
	}
	
	
	

}
