package thiagoOliveiraDaSilva.banco;

public class UsoDeContas {
	
	public static void main(String[] args) {
		
		Banco b = new Banco();
		
		Pessoa p = new Pessoa(1, "Pedro");
		Conta c = new Conta(100, p);
		b.cadastro(c);

		Pessoa p1 = new Pessoa(2, "Joao");
		Conta c1 = new Conta(101, p1);
		b.cadastro(c1);
		
		Pessoa p2 = new Pessoa(3, "Maria");
		Conta c2 = new Conta(102, p2);
		b.cadastro(c2);
		
		b.deposito(100, 111);
		b.deposito(101, 222);
		b.deposito(102, 333);
		
		b.saque(100, 1);
		b.saque(101, 2);
		b.saque(102, 3);
		
		System.out.println("Saldo 100:" + b.saldo(100));
		System.out.println("Saldo 101:" + b.saldo(101));
		System.out.println("Saldo 102:" + b.saldo(102));

		b.deposito(1, 111);
		b.saque(2, 1);
		b.saldo(3);
	}

}
