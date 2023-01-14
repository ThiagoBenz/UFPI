package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public class ProdutoJaCadastrado extends Exception {
	public ProdutoJaCadastrado() {
		super("Produto ja esta cadastrado no banco de dados!");
	}

}
