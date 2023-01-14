package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

import java.util.ArrayList;

public class ArrayListDeProdutos extends EstruturaDeDadosEstoque {

	 ArrayList<Produto> produtos = new ArrayList<>();

	 public ArrayListDeProdutos() {
		 produtos = new ArrayList<Produto>();
	 }
	
	@Override
	public boolean inserir(Produto p) throws ProdutoJaCadastrado, ProdutoInexistente, DadosInvalidos{
		
		if (p == null) {
			//throw new DadosInvalidos();	
			return false;
		}
		// Verificar se o produto já existe
		Produto auxProduto;
		try {
			auxProduto = pesquisar(p.getCodigo());
		}
		catch (ProdutoInexistente e) {
			auxProduto = null;
		}
		if (auxProduto != null && auxProduto.equals(p)) {
			return false;
		}
		
		// Se não existe, incluir.
		produtos.add(p);
		return true;
	}

	@Override
	public Produto pesquisar(int cod) throws ProdutoInexistente {
		for (Produto i : produtos) {
			if (cod == i.getCodigo()) {
				return i;
			}
		}
		throw new ProdutoInexistente();

	}

	@Override
	public int getContador() {
		
		return 0;
	}

}
