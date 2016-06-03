package jogo.principal;
import java.util.Scanner;

import jogo.erros.LeituraArquivoException;
import jogo.mecanicas.FabricaMecanicaDoJogo;
import jogo.mecanicas.MecanicaDoJogo;

public class Principal {

	private static FabricaMecanicaDoJogo fabricaMecanicaDoJogo;
	private static MecanicaDoJogo mecanicaDoJogo;
	private static Scanner scanner;
	private static int opcaoMenu;

	public static void main(String[] args) {
		
		fabricaMecanicaDoJogo = new FabricaMecanicaDoJogo();
		
		scanner = new Scanner(System.in);
		
		do{
			menuPrincipal();
			
			if(opcaoMenu == 1){
				
				try {
					mecanicaDoJogo = fabricaMecanicaDoJogo.getMecanicaParaOJogo();
				} catch (LeituraArquivoException e1) {
					e1.printStackTrace();
					System.exit(0);
				}

				desenhaSeparador();
				System.out.println("\t\t\tCOMEÇOU");
				desenhaSeparador();
				System.out.println("\t\tREGRA DA RODADA\n\n" + mecanicaDoJogo.getDescricaoDaRegra());
				desenhaSeparador();
				do{
					try {
						
						String palavraEmbaralhada = mecanicaDoJogo.sortearPalavra();
						System.out.println("A PALAVRA DA VEZ É: " + palavraEmbaralhada);
						
						System.out.println("\nA palavra embaralhada é: ");
						String chute = scanner.nextLine();
						
						desenhaSeparador();
						if(mecanicaDoJogo.isUsuarioAcertou(chute)){
							System.out.println("\t\tACERTOU! Pontuação Atual: " + mecanicaDoJogo.getPontuacaoFinal());
						}else{
							System.out.println("\t\tERROU! Pontuação Atual: " + mecanicaDoJogo.getPontuacaoFinal());
						}
						desenhaSeparador();
						
					} catch (LeituraArquivoException e) {
						e.printStackTrace();
						System.exit(0);
					}
					
				}while(mecanicaDoJogo.isJogoNaoAcabou());
				
				desenhaSeparador();
				System.out.println("\t\tFIM DE JOGO - Pontuação Final: " + mecanicaDoJogo.getPontuacaoFinal());
				desenhaSeparador();
			}
			
		}while(opcaoMenu != 0);
	}
	
	public static void menuPrincipal(){
		desenhaSeparador();
		System.out.println("\t\tJOGO DAS PALAVRAS EMBARALHADAS");
		desenhaSeparador();
		System.out.println("Menu Principal\n");
		System.out.println("1 - Iniciar o Jogo");
		System.out.println("0 - SAIR");
		System.out.println("\nDigite sua opção:");
		
		try{
			opcaoMenu = Integer.parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			opcaoMenu = Integer.MAX_VALUE;
		}
		
		desenhaSeparador();
	}
	
	public static void desenhaSeparador(){
		System.out.println("--------------------------------------------------------------");
	}
	
}
