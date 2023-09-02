
package thiagoOliveiraDaSilva.estoqueComProdutoPerecivelExcecoes;

public class Fornecedor {
	private int cnpj;
	private String nome;
	
	public Fornecedor (int cod, String nome) {
		this.setCnpj(cod);
		this.setNome(nome);
	}
	
	
	
	/**
	 * @return the cnpj
	 */
	public int getCnpj() {
		return cnpj;
	}
	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
