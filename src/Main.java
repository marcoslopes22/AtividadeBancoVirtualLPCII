import java.util.Scanner;
import java.io.IOException;

/*
===========================================================================================
ETE PORTO DIGITAL, RECIFE, VII-VX-XXXXII;
DESENVOLVIMENTO DE SISTEMAS - MÓDULO II;
LÓGICA E PENSAMENTO COMPUTACIONAL II - PROFESSOR WALDECK;

ATIVIDADE: BANCO VIRTUAL PORTINHO VERSÃO 1.0;
GRUPO: LUCAS VIKTOR, VITÓRIA NEVES, GUILHERME FREITAS, RAVEL CESAR, MARCOS AURÉLIO, ELVIS BRUNO;
===========================================================================================
*/

public class Main {
	
	static final float SALDOIN	= 150f;
	static float saldo			= SALDOIN;
	static int escolha;
	
	public static void main(String[] args) throws InterruptedException, IOException{
		System.out.print("\033[H\033[2J");
		do {
			escolha = MostrarTela();
			switch(escolha) {
				case 1:
					Depositar();
					LimparTela();
					break;
					
				case 2:
					Sacar();
					LimparTela();
					break;

				case 3:
					VerExtrato();
					LimparTela();
					break;

				case 4:
					SairDoSistema();
					break;

				default:
					OpcaoInvalida();
					LimparTela();
					break;
			};
		} while(escolha != 4);
	};

	public static int MostrarTela(){
		int opcaoEscolhida;
		Scanner scan = new Scanner(System.in);
		System.out.println("BEM VINDO AO BANCO VIRTUAL PORTINHO");
		System.out.println("======================================");
		System.out.println("1 - Depositar");
		System.out.println("2 - Sacar");
		System.out.println("3 - Ver Extrato");
		System.out.println("4 - Sair");
		System.out.println("======================================");

		System.out.print("Digite sua operação: ");
		opcaoEscolhida = scan.nextInt();
		System.out.println("======================================");
		return opcaoEscolhida;
	};

	public static void LimparTela(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Pressione ENTER para continuar...\n");
		String limpar = scan.nextLine();
		System.out.print("\033[H\033[2J");
	};

	public static void Depositar() {
		float deposito;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.print("Digite o valor a ser depositado: ");
			deposito = scan.nextFloat();
			if (deposito > 0) {
				saldo += deposito;
				System.out.printf("\nDepositado com sucesso! Valor: R$ %s reais.\n", deposito);
			} else {
				System.out.print("Valor inválido. Por favor, tente novamente:\n");
			};
		} while(deposito < 0);
	};

	public static void Sacar(){
		float saque;
		do {
			Scanner escolha = new Scanner(System.in);
			System.out.print("Digite o valor a ser sacado: ");
			saque = escolha.nextFloat();
			if(saque <= saldo && saque > 0) {
				saldo -= saque;
				System.out.printf("Saque de R$ %s reais efetuado com sucesso!\n", saque);
				break;
			} else {
				System.out.println("Saque Inválido! Por favor, tente novamente.");
			};
		} while(saque > saldo || saque <= 0);
	};

	public static void VerExtrato(){
		System.out.printf("O seu saldo é de R$ %s reais\n", saldo);
	};

	public static void SairDoSistema(){
		System.out.println("Saindo do sistema...");
	};

	public static void OpcaoInvalida(){
		System.out.println("Opção Inválida! Tente novamente!");
	};
};
