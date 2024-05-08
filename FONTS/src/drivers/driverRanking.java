package drivers;

import java.util.Scanner;

import main.persistencia.*;

public class driverRanking {

	private static void mostraOpcions() {
		System.out.println("MENU RANKING");
		System.out.println("1 Consulta Ranking General");
		System.out.println("2 Consulta Ranking per Tipus");
		System.out.println("3 Consulta Ranking Personal");
		System.out.println("0 Sortir");
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		mostraOpcions();
		int op;
		while (scan.hasNextInt() && (op = scan.nextInt()) != 0) {
			switch (op) {
		
			case 1:
				driverRT.mostraRankingPerTipus(scan);
				break;
			case 2:
				driverRP.mostraRankingPersonal(scan);
				break;
			default:
				System.err.println("Opcio incorrecte");
			}
			System.out.println();
			mostraOpcions();
		}
		System.out.println("Fi del programa");
	}
}