package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public class ProdutoNaoPerecivel extends Exception {
	public ProdutoNaoPerecivel() {
		super("produto nao e do tipo perecivel");
	}

}
