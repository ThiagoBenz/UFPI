package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public class ProdutoVencido extends Exception {
	public ProdutoVencido() {
		super("produto fora da validade!");
	}

}
