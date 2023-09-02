package thiagoOliveiraDaSilva.banco;



public class Banco {
	
	private Conta contas[] = new Conta[10];
	private int indice = 0;
	
	public boolean cadastro(Conta c) {
		Conta outra = pesquisar(c.getNumero());
		if (outra != null) {
			System.out.println("Conta já cadastrada.");
			return false;
		} else {
		  contas[indice++] = c;
		  return true;
		}
	}
	
	private Conta pesquisar(int num){
		for (int i = 0; i < indice; i++) {
			if (contas[i].getNumero() == num) {
				return contas[i];
			}
		}
		return null;
	}
	
	public void deposito(int num, double val) {
		Conta c = pesquisar(num);
		if (c != null) { 
  		  c.credito(val);
 		} else {
		  System.out.println("Conta inexistente.");
		} 
	}
	
	public void saque(int num, double val) {
		Conta c = pesquisar(num);
		if (c != null) { 
		  c.debito(val);
 		} else {
 	      System.out.println("Conta inexistente.");
 		} 
	}
	
	public double saldo(int num) {
		Conta c = pesquisar(num);
		if (c != null) { 
		  return c.getSaldo();
 		} else {
 	      System.out.println("Conta inexistente.");
 		} 
		return -9999999;
	}
	
	public String extrato(int num) {
		Conta c = pesquisar(num);
		if (c != null) { 
		  return c.getExtrato();
 		} else {
 	      System.out.println("Conta inexistente.");
 		} 
		return "";
	}
	
	public Pessoa titular(int num) {
		Conta c = pesquisar(num);
		if (c != null) { 
		  return c.getTitular();
 		} else {
 	      System.out.println("Conta inexistente.");
 		} 
		return null;
	}

}
