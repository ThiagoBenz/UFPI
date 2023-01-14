package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

import java.util.Date;

public class Lote {
	
	private int quantidade;
	private Date validade;
	
	
	
	public Lote(int quantidade, Date validade) {
		super();
		this.quantidade = quantidade;
		this.validade = validade;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	

}
