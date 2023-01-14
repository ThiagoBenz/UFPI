package thiagoOliveiraDaSilva.estoque;

public abstract class EstruturaDeDadosEstoque {

	
	public abstract boolean inserir(Produto p);
	public abstract Produto pesquisar(int cod);
	public abstract int getContador();
}
