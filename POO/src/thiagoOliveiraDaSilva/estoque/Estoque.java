package thiagoOliveiraDaSilva.estoque;

import java.util.ArrayList;


public class Estoque {

	// ArrayList<Produto> produtos = new ArrayList<>();
	private EstruturaDeDadosEstoque produtos;

	public Estoque(EstruturaDeDadosEstoque repositorio) {
		this.produtos = repositorio;
	}

	public Produto pesquisa(int cod) {
		/*
		 * for (Produto produto : produtos) { if (produto.getCodigo() == cod) { return
		 * produto; } } return null;
		 */
		return produtos.pesquisar(cod);
	}

	public void incluir(Produto p) {
		produtos.inserir(p);
		
		
	}

	public void comprar(int cod, int quant, double preco) {

		if (pesquisa(cod) != null) {
			if (quant > 0 && preco >= 0) {
				Produto p = pesquisa(cod);
				p.compra(quant, preco);
			}
		} else {
			return;
		}

	}

	public double vender(int cod, int quant) {

		if (pesquisa(cod) != null) {
			if (quant > 0) {
				Produto p = pesquisa(cod);

				double i = p.venda(quant);
				return i;
			} else {
				return -1.0;
			}
		} else {
			return -1.0;
		}

	}

	public ArrayList<Produto> estoqueAbaixoDoMinimo() {

		ArrayList<Produto> p = new ArrayList<>();

		
		for (int i = 0; i < produtos.getContador(); i++) {
			if (produtos.pesquisar(i).getQuantidade()< produtos.pesquisar(i).getEstoqueMin()) {
				
				p.add(produtos.pesquisar(i));
			}
		}

		return p;
	}

}
