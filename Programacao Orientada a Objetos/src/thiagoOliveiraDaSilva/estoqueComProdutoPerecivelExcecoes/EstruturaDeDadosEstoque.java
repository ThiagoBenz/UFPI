package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public abstract class EstruturaDeDadosEstoque {

	
	public abstract boolean inserir(Produto p) throws ProdutoJaCadastrado, ProdutoInexistente, DadosInvalidos;
	public abstract Produto pesquisar(int cod) throws ProdutoInexistente;
	public abstract int getContador();
}
