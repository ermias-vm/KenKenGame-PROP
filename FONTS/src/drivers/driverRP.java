package drivers;

import java.util.*;
import main.domini.controladors.*;
import main.domini.excepcions.*;

public class driverRP {

	private static void mostraOpcions() {
		System.out.println("Opcions:");
		System.out.println("1: Nombre de partides");
		System.out.println("2: Nombre de pistes usades (en mitjana)");
		System.out.println("3: Millors temps");
		System.out.println("4: Veure-ho tot");
		System.out.println("0: Sortir");
	}
	
	public static void mostraRankingPersonal(Scanner scan) {
		System.out.println("Ranking Personal:");
		System.out.println("----------------");
		System.out.println("De qui vols consultar el rànquing?");
		try {
			String u = null;
			if (scan.hasNext()) u = scan.next();
			else System.err.println("Aquest usuari no es correcte");
			creadorRanking RF = new creadorRanking();
			if (CtrlUser.getUsuari(u) == null) throw (new ExcepcionUsuariNoExisteix());
			else {
				RankingPersonal RP = RF.generarRankingPersonal(u);				
				int opt2;
				mostraOpcions();
				while (scan.hasNextInt() && (opt2 = scan.nextInt()) != 0) {
					switch(opt2) {
					case 1:
						System.out.println(u+" ha jugat "+ RP.getResolts()+" partida/es");
						break;
					case 2:
						System.out.println(u+" ha usat "+ RP.getPistes()+" pistes en mitjana");
						break;
					case 3:
						System.out.println("Dificultat\t\tTemps");
						for (String d : Dificultat.getAll()) {
							if (RP.getBestTime(d) != null) {
								System.out.println(d + ":\t\t" + RP.getBestTime(d));
							}
							else System.out.println(d + ":\t\t-");
						}
						break;
					case 4:
						System.out.println("Usuari: " + u);
						System.out.println(u+" ha jugat " + RP.getResolts()+ " partida/es");
						System.out.println(u+" ha usat " + RP.getPistes()+ " pistes en mitjana");
						System.out.println("Dificultat\tTemps");
						for (String d : Dificultat.getAll()) {
							if (RP.getBestTime(d) != null) {
								System.out.println(d + ":\t\t" + RP.getBestTime(d));
							}
							else System.out.println(d + ":\t\t-");

						}	
						break;
					default:
						System.err.println("Opcio erronia");
						break;
					}
					mostraOpcions();
				}
				if (!scan.hasNextInt()) System.out.println("No has introduit una opcio correcta");
			}	
		} catch (ExcepcionUsuariNoExisteix e) {
			System.out.println(e.getMessage());
		}				
		System.out.println("Fi Ranking Personal");
	}
}