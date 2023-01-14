package thiagoOliveiraDaSilva.locadora;

public class Cliente {
	private int cpf;
	private String nome;
	/**
	 * @return the cpf
	 */
	public int getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(int cpf) {
		this.cpf = cpf;
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
	
	public Cliente(int cpf, String nome) {
		this.setCpf(cpf);
		this.setNome(nome);
	}
	
	

}
