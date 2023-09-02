package thiagoOliveiraDaSilva.banco;



public class Conta {
	private int numero;
	private double saldo;
	private String extrato = "";
	private Pessoa titular;
	
	Conta(int n, Pessoa t){
		numero = n;
		titular = t;
	}
	
	double getSaldo() {
		return saldo;
	}
	
	int getNumero() {
		return numero;
	}
	
	String getExtrato() {
		return extrato;
	}
	
	Pessoa getTitular() {
		return titular;
	}
	
	void credito(double val) {
		saldo = saldo + val;
		extrato = extrato + "Credito: " + val + 
				". Saldo: " + saldo + ".\n";
	}
	
	void debito(double val) {
		saldo = saldo - val;
		extrato = extrato + "Debito: " + val + 
				". Saldo: " + saldo + ".\n";
	}

}
