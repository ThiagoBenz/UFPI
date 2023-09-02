package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public class ListaDeProduto extends EstruturaDeDadosEstoque {

	private Produto produto = null;
	private int contador = 0;
	private ListaDeProduto prox = null;

	
	
	
	/**
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}

	@Override
	public boolean inserir(Produto p) {
		
		if (pesquisar(p.getCodigo()) != null) {
			return false;
		}
		if (produto == null) {
			produto = p;
			prox = new ListaDeProduto();
			this.setContador(this.getContador()+1);
		}

		return inserir(p);
	}

	@Override
	public Produto pesquisar(int cod) {
		
		if (produto ==null ) {
			return null;
		}
		if (produto.getCodigo() == cod) {
			return produto;
		}else {
			return prox.pesquisar(cod);
		}

	}

}
