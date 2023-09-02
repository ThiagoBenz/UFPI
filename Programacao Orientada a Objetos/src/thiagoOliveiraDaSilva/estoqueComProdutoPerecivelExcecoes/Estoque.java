package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;

public class Estoque {

	// ArrayList<Produto> produtos = new ArrayList<>();
	private EstruturaDeDadosEstoque produtos;
	
	public Estoque() {
		// Inicializar todas as propriedades da classe
		this.produtos = new ArrayListDeProdutos();
	}

	public Produto pesquisar(int cod) throws ProdutoInexistente {
		Produto resultado;
		try {
			resultado = produtos.pesquisar(cod);
		} catch (ProdutoInexistente e) {
			throw e;
		}
		return resultado;
	}

	public void incluir(Produto p) throws DadosInvalidos, ProdutoJaCadastrado, ProdutoInexistente {

		if (p.getCodigo() < 0) {
			throw new DadosInvalidos();
		}
		if (p.getPrecoDeCompra() < 0) {
			throw new DadosInvalidos();
		}
		if (p.getEstoqueMinimo() < 0) {
			throw new DadosInvalidos();
		}
		if (p.getPrecoDeVenda() < 0) {
			throw new DadosInvalidos();
		}
		if (p.getQuantidade() < 0) {
			throw new DadosInvalidos();
		}
		if (p.getLucro() < 0) {
			throw new DadosInvalidos();
		}
		if (p.getDescricao().equals(null)) {
			throw new DadosInvalidos();
		}
		if (p.getDescricao().equals("")) {
			throw new DadosInvalidos();
		}

		Produto encontradoProduto;	
		try {
			encontradoProduto = pesquisar(p.getCodigo());
			throw new ProdutoJaCadastrado();
		}
		catch (ProdutoInexistente e) {
			try {
				produtos.inserir(p);
			} catch (Exception e2) {
				throw e2;
			}
		}
	}

	public void comprar(int cod, int quant, double preco, Date data) throws ProdutoNaoPerecivel, DadosInvalidos, ProdutoInexistente {

		Produto produto;
		try {
			produto = pesquisar(cod);
		} catch (Exception e) {
			throw e;
		}
		
		if ((quant <= 0) && (preco <= 0)) {
			throw new DadosInvalidos();
		}
		
		if (produto instanceof ProdutoPerecivel) {
			ProdutoPerecivel auxPerecivel = (ProdutoPerecivel) produto;
			auxPerecivel.compra(quant, preco);
		} else {
			produto.compra(quant, preco);
		}

	}

	public double vender(int cod, int quant) throws DadosInvalidos, ProdutoInexistente, ProdutoVencido {

		Produto produto;
		try {
			produto = pesquisar(cod);	
		}
		catch (ProdutoInexistente e) {
			throw e;
		}
		if (quant <= 0) {
			throw new DadosInvalidos();
		}
		if (produto == null) {
			throw new ProdutoInexistente();
		}

		double vendaValue = produto.venda(quant);
		return vendaValue;
	}

	public ArrayList<Produto> estoqueAbaixoDoMinimo() throws ProdutoInexistente {

		ArrayList<Produto> p = new ArrayList<>();

		for (int i = 0; i < produtos.getContador(); i++) {
			if (produtos.pesquisar(i).getQuantidade() < produtos.pesquisar(i).getEstoqueMinimo()) {

				p.add(produtos.pesquisar(i));
			}
		}

		return p;
	}

	public int quantidadeVencidos(int cod) throws ProdutoInexistente{
		
					
		return 0;
	}

	public ArrayList<Produto> estoqueVencido() {
		
		return null;
	}

}
