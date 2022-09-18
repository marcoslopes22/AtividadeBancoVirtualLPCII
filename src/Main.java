import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
=========================================================================================================
ETE PORTO DIGITAL, RECIFE, XIX-IX-XXXXII;
DESENVOLVIMENTO DE SISTEMAS - MÓDULO II;
LÓGICA E PENSAMENTO COMPUTACIONAL II - WALDECK;

ATIVIDADE 01: BANCO VIRTUAL PORTINHO;
GRUPO: LUCAS VIKTOR, VITÓRIA NEVES, GUILHERME FREITAS, RAVEL CESAR, MARCOS AURÉLIO, ELVIS BRUNO;
=========================================================================================================
*/

public class Main {
	
	static final float SALDOIN	= 150f;
	static float saldo			= SALDOIN;
	static float rendimento		= 1.14f;
	static boolean adicionarRendimento = false;
	static int escolha;

	static StringBuilder extrato = new StringBuilder();
	static DateTimeFormatter dataAtual = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	public static void main(String[] args) throws InterruptedException, IOException{

		GerenciarRendimento();

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
					ConsultarSaldo();
					LimparTela();
					break;
				
				case 4:
					ConsultarExtrado();
					LimparTela();
					break;

				case 5:
					VerRendimento();
					LimparTela();
					break;

				case 6:
					SairDoSistema();
					break;

				default:
					OpcaoInvalida();
					LimparTela();
					break;
			};
		} while(escolha != 6);
	};

	/* IMPRIMIR TELA */
	public static int MostrarTela(){
		int opcaoEscolhida;
		Scanner scan = new Scanner(System.in);
		System.out.println("BEM VINDO AO BANCO VIRTUAL PORTINHO");
		System.out.println("======================================");
		System.out.println("1 - Depositar");
		System.out.println("2 - Sacar");
		System.out.println("3 - Consultar saldo");
		System.out.println("4 - Consultar extrato");
		System.out.println("5 - Rendimento");
		System.out.println("6 - Sair");
		System.out.println("======================================");

		System.out.print("Digite sua operação: ");
		opcaoEscolhida = scan.nextInt();
		System.out.println("======================================");
		return opcaoEscolhida;
	};

	/* LIMPAR A TELA; */
	public static void LimparTela(){
		Scanner scan = new Scanner(System.in);
		System.out.println("\nPressione ENTER para continuar...\n");
		String limpar = scan.nextLine();
		System.out.print("\033[H\033[2J");
	};

	/* DEPOSITAR; */
	public static void Depositar() {
		float deposito;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.print("Digite o valor a ser depositado: ");
			deposito = scan.nextFloat();
			if (deposito > 0) {
				saldo += deposito;
				System.out.printf("\nDepositado com sucesso! Valor: R$ %s reais.\n", deposito);
				extrato.append("Depósito: R$ "+deposito+" reais | "+dataAtual.format(LocalDateTime.now())+"\n");
			} else {
				System.out.print("Valor inválido. Por favor, tente novamente:\n");
			};
		} while(deposito <= 0);
	};

	/* SACAR; */
	public static void Sacar(){
		float saque;
		do {
			Scanner escolha = new Scanner(System.in);
			System.out.print("Digite o valor a ser sacado: ");
			saque = escolha.nextFloat();
			if(saque <= saldo && saque > 0) {
				saldo -= saque;
				System.out.printf("Saque de R$ %s reais efetuado com sucesso!\n", saque);
				extrato.append("Saque: R$ "+saque+" reais | "+dataAtual.format(LocalDateTime.now())+"\n");
				break;
			} else {
				System.out.println("Saque Inválido! Por favor, tente novamente.");
			};
		} while(saque > saldo || saque <= 0);
	};

	/* CONSULTAR SALDO; */
	public static void ConsultarSaldo(){
		System.out.printf("O seu saldo é de R$ %s reais\n", saldo);
	};

	/* CONSULTAR HISTÓRICO */
	public static void ConsultarExtrado(){
		System.out.println("EXTRATO");
		System.out.println("-------------------------------------");
		if(extrato.length() <= 0){
			System.out.println("Sem históricos registrados.");
		} else {
			System.out.println(extrato);
		};
		System.out.println("=====================================");
	};

	/* VERIFICAR SE O VALOR GERADO COM O RENDIMENTO JÁ FOI ATRIBUIDO AO SALDO; */
	public static void GerenciarRendimento(){
		if(adicionarRendimento == false){
			float saldoComRendimento = (rendimento/100)*saldo;
			saldo = saldo + saldoComRendimento;
			adicionarRendimento = true;
		} else {
			adicionarRendimento = true;
		};
	};

	/* CONSULTAR O SALDO COM RENDIMENTO; */
	public static void VerRendimento(){
		if(adicionarRendimento){
			System.out.println("Rendimento mensal: "+rendimento+"%");
			if(adicionarRendimento){
				System.out.println("Status: Atribuído!");
			};
			float rendimentoProximoMes = rendimento/100*saldo;
			System.out.println("Previsão do próximo mês: +"+rendimentoProximoMes+" reais");
			// System.out.printf("Saldo com rendimento: R$ %s reais\n", saldo);
		} else {
			GerenciarRendimento();
		};
	};

	/* PARAR A EXECUÇÃO DO CÓDIGO */
	public static void SairDoSistema(){
		System.out.println("Saindo do sistema...");
	};

	/* TRATAMENTO PARA OPÇÕES INVÁLIDAS; */
	public static void OpcaoInvalida(){
		System.out.println("Opção Inválida! Tente novamente!");
	};
};
