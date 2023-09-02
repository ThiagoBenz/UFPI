package thiagoOliveiraDaSilva.banco;

public class Pessoa {
	
	private String nome;
	private int cpf;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCpf() {
		return cpf;
	}
	
	public Pessoa(int num) {
		cpf = num;
	}
	
	public Pessoa(int num, String nm) {
		cpf = num;
		nome = nm;
	}

}
