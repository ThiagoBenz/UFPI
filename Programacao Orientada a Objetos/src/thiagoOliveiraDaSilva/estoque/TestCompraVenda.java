package thiagoOliveiraDaSilva.estoque;


import java.util.Calendar;
import java.util.Date;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

class TestCompraVenda {

	@Test
	void testCompra() {
		//testa a compra de um produto
		
		Produto produto = new Produto(123, "test", 1, 0.5, null);
		produto.setQuantidade(10);
		produto.setPrecoCompra(2.5);
		produto.compra(10, 7.5);

		assert (produto.getPrecoCompra() == 5);
	}

	@Test
	void testVenda() {
		//testa a venda de um produto
		
		Produto produto = new Produto(123, "test", 1, 0.5, null);
		produto.setEstoqueMin(1);
		produto.setQuantidade(6);
		assert (produto.venda(6) == -1);

		
	}
	
	@Test
	void testVendaPerecivel() {
		//testa a venda de um produto perecivel
		
		ProdutoPerecivel perecivel= new ProdutoPerecivel(123, "test", 1, 0.5, null, null);
		Date date = Calendar.getInstance().getTime() ;
		
		perecivel.setQuantidade(5);
		perecivel.setPrecoCompra(1);
		perecivel.setValidade(date);
		
		assert(perecivel.venda(1)==1.5);
		
	}

}
