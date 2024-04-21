package main.Drivers;

import main.domini.controladors.ControladorPartida;
import main.domini.controladors.CtrlTauler;
import main.domini.excepcions.*;
import main.persistencia.ControladorPersistenciaPartida;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver per a simular una partida de Kenken.
 * @author Nil Beascoechea Vàzquez
 */
public class DriverJugarPartida {
    private final static inout io = new inout();
    public static void main(String[] args) {
        ControladorPartida controladorPartida = new ControladorPartida();
        ControladorPersistenciaPartida controladorPersistenciaPartida = new ControladorPersistenciaPartida();
        CtrlTauler controladorTaulers = new CtrlTauler();
        controladorPartida.setControladorTauler(controladorTaulers);
        controladorPartida.setControladorPersistenciaPartida(controladorPersistenciaPartida);
        System.out.println("Benvingut/da al driver de jugar partida! \n M'encarrego de" +
                " provar les funcions relacionades amb el flux de joc, no comprovaré que el teu usuari sigui correcte.");
        System.out.println("Introdueix el nom d'usuari, només lletres i números són permesos:");
        Scanner scanner = new Scanner(System.in);
        String nomUsuari = scanner.nextLine();
        System.out.println("Hola " + nomUsuari + "!");
        while (true) {
            System.out.println("Funcionalitats (escriu únicament el número):");
            System.out.println("1. Continuar jugant última partida guardada per aquest usuari.\n" +
                    "2. Continuar jugant una de les partides guardades per aquest usuari.\n" +
                    "3. Jugar una partida amb un tauler de mida específica.\n" +
                    "4. Jugar una partida amb un tauler introduït per l'usuari.\n" +
                    "5. Sortir.");
            int opcio = scanner.nextInt();
            switch (opcio) {
                case 1:
                    try {
                        String[] estat = controladorPartida.carregarUltimaPartidaGuardada(nomUsuari);
                        jugar(scanner, controladorPartida, estat, nomUsuari);
                    }
                    catch (ExcepcioCarregaPartida| ExcepcioNoPermisUsuari e) {
                        System.out.println(e.getMessage());
                    }
                    catch (ExcepcioInicialitzacioPersistenciaPartida e) {
                        controladorPartida.setControladorPersistenciaPartida(controladorPersistenciaPartida);
                        System.out.println("Hi ha hagut un error en la inicialització de la persistència de la partida, torna a intentar-ho.");
                    }
                    catch ( ExcepcioPartidaEnCurs e) {
                        System.out.println("No es pot carregar una partida mentre s'està jugant una altra.");
                    }
                    catch (Exception e) {
                        System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                    }
                    break;
                case 2:
                    ArrayList<String> identificadors_partides = new ArrayList<>();
                    if (controladorPartida.getPartidesGuardadesUsuari().length == 0) {
                        try {
                            identificadors_partides = controladorPartida.carregarPartidesGuardadesUsuari(nomUsuari);
                        } catch (ExcepcioCarregaPartida e) {
                            System.out.println(e.getMessage());
                            break;
                        } catch (ExcepcioInicialitzacioPersistenciaPartida e) {
                            controladorPartida.setControladorPersistenciaPartida(controladorPersistenciaPartida);
                            System.out.println("Hi ha hagut un error en la inicialització de la persistència de la partida, torna a intentar-ho.");
                            break;
                        }
                    }
                    boolean sortir = false;
                    do{
                        System.out.println("Per a que no es facin trampes no s'ensenyarà el tauler de la partida fins que no es comenci a jugar.\n " +
                                "Si es vol inicialitzar directament la partida escriviu l'identificador de la partida guardada.\n" +
                                "Si es volgués veure una preview de l'estat de la partida guardada escriviu Preview: identificador.\n" +
                                "Si es volgués retornar al menú anterior escriviu Sortir.");
                        System.out.println("Identificadors de les partides guardades per aquest usuari: ");
                        for (String identificadorsPartida : identificadors_partides) {
                            System.out.println(identificadorsPartida);
                        }
                        String identificador = scanner.nextLine();
                        if (identificador.contains("Preview: ")) {
                            try {
                                String[] preview = controladorPartida.iniciarPartidaGuardada(identificador.split(" ")[1],nomUsuari);
                                escriuEstatPartida(preview[0]);
                                System.out.println("Vols jugar aquesta partida? (Y/N)");
                                String resposta = scanner.nextLine();
                                if (resposta.equals("Y")) {
                                    jugar(scanner, controladorPartida, preview, nomUsuari);
                                    break;
                                }
                                else {
                                    controladorPartida.tancaPartida();
                                }
                            } catch (ExcepcioCarregaPartida | ExcepcioPartidaEnCurs e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                            }
                        }
                        else if (identificadors_partides.contains(identificador)){
                            try {
                                String[] estat = controladorPartida.iniciarPartidaGuardada(identificador, nomUsuari);
                                jugar(scanner, controladorPartida, estat, nomUsuari);
                                break;
                            } catch (ExcepcioCarregaPartida | ExcepcioPartidaEnCurs e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                            }
                        }
                        else if (identificador.equals("Sortir")){
                            sortir = true;
                        }
                    }
                    while(!sortir);
                    break;
                case 3:
                    System.out.println("Introdueix el grau del tauler:");
                    int mida = scanner.nextInt();
                    try {
                        boolean jugat = true;
                        String identificadorTauler;
                        do {
                            identificadorTauler = controladorTaulers.selecionaTaulerAleatori(mida);
                            if (controladorPartida.haJugat(identificadorTauler, nomUsuari)){
                                jugat = false;
                            }
                        }
                        while (jugat);
                        if (identificadorTauler.isEmpty()) {
                            System.out.println("No s'ha pogut trobar cap tauler de la mida especificada que no s'hagi jugat.");
                            break;
                        }
                        String[] estat = controladorPartida.iniciaPartidaIdentificadorTauler(identificadorTauler, nomUsuari);
                        jugar(scanner, controladorPartida, estat, nomUsuari);
                        break;
                    }
                    catch (ExcepcioPartidaEnCurs e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Vols introduïr el tauler des d'un fitxer .txt o manualment? (Fitxer/Manualment)");
                    String decisio = scanner.nextLine();
                    if (decisio.equals("Fitxer")){
                        boolean trobat = false;
                        BufferedReader llegir = null;
                        System.out.println("El fitxer hauria d'estar a la carpeta Input, introdueix el nom del fitxer amb l'extensió:");
                        do {
                            String nomFitxer = scanner.nextLine();
                            if (nomFitxer.equals("Sortir")) {
                                break;
                            }
                            try {
                                llegir = new BufferedReader(new FileReader("Input/" + nomFitxer));
                                trobat = true;
                            } catch (FileNotFoundException e) {
                                System.out.println("No s'ha trobat el fitxer especificat, torna a introduir el nom del fitxer o, si vols sortir escriu Sortir.");
                            }
                        }
                        while(!trobat);
                        StringBuilder dadesTauler = new StringBuilder();
                        String linia;
                        try{
                            while ((linia = llegir.readLine()) != null) {
                                dadesTauler.append(linia);
                                dadesTauler.append("\n");
                            }
                            llegir.close();
                        }
                        catch (Exception e) {
                            System.out.println("Error en la lectura del fitxer: " + e.getMessage());
                            break;
                        }
                        try{
                            String[] estat = controladorPartida.iniciaPartidaDadesTauler(dadesTauler.toString(),  nomUsuari);
                            jugar(scanner, controladorPartida, estat, nomUsuari);
                        } catch (ExcepcioCarregaTauler|ExcepcioPartidaEnCurs e) {
                            System.out.println(e.getMessage());
                        } catch (ExcepcioInicialitzacioControladorTauler e) {
                            System.out.println("Hi ha hagut un error en la inicialització del controlador de taulers, torna a intentar-ho.");
                            controladorPartida.setControladorTauler(controladorTaulers);
                        }
                    }
                    else {
                        System.out.println("Introdueix el tauler seguint el format descrit a l'enunciat, recorda que si en qualsevol moment vols sortir pots escriure Sortir.");
                        StringBuilder dadesTauler = new StringBuilder();
                        String linia;
                        linia = scanner.nextLine();
                        if (linia.equals("Sortir")) {
                            break;
                        }
                        dadesTauler.append(linia).append('\n');
                        for (int i = 0; i < Integer.parseInt(linia.split(" ")[1]); i++){
                            linia = scanner.nextLine();
                            if (linia.equals("Sortir")) {
                                break;
                            }
                            dadesTauler.append(linia).append("\n");
                        }
                        try{
                            String[] estat = controladorPartida.iniciaPartidaDadesTauler(dadesTauler.toString(),  nomUsuari);
                            jugar(scanner, controladorPartida, estat, nomUsuari);
                        } catch (ExcepcioCarregaTauler|ExcepcioPartidaEnCurs e) {
                            System.out.println(e.getMessage());
                        } catch (ExcepcioInicialitzacioControladorTauler e) {
                            System.out.println("Hi ha hagut un error en la inicialització del controlador de taulers, torna a intentar-ho.");
                            controladorPartida.setControladorTauler(controladorTaulers);
                        }
                    }
                    break;
                case 5:
                    scanner.close();
                    return;
            }
        }
    }
    private static void jugar(Scanner scanner, ControladorPartida controladorPartida, String[] estatInicial, String nomUsuari){
        System.out.println("Estat inicial de la partida:");
        try {
            escriuEstatPartida(estatInicial[0]);
        } catch (Exception e) {
            System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
        }
        System.out.print(estatInicial[1]);
        while (true) {
            System.out.println("Funcionalitats que ofereixo en la partida (escriu únicament el número):");
            System.out.println("Tingues en compte que si guardes la partida o utilitzes pistes no podràs participar als rankings, és l'has de solucionar (4. Acabar) d'una tirada sense ajudes.");
            System.out.println("Es permet desfer i refer moviments il·limitadament sense penalització.");
            System.out.println("1. Posar valor.\n" +
                    "2. Desfer moviment.\n" +
                    "3. Refer moviment.\n" +
                    "4. Pista.\n" +
                    "5. Guardar partida.\n" +
                    "6. Acabar partida (si vols que s'avaluï), es tancarà la partida si està bé.\n" +
                    "7. Tancar i guardar partida.\n" +
                    "8. Sortir de la partida sense guardar.");
            int opcio = scanner.nextInt();
            switch (opcio) {
                case 1:
                    System.out.println("Introdueix la fila, la columna i el valor que vols posar com a: [fila columna valor] sense els [].");
                    String dades = scanner.nextLine();
                    int fila = Integer.parseInt(dades.split(" ")[0]);
                    int columna = Integer.parseInt(dades.split(" ")[1]);
                    int valor = Integer.parseInt(dades.split(" ")[2]);
                    try {
                        String[] estat = controladorPartida.introduirValor(fila, columna, valor);
                        System.out.println("Estat de la partida:");
                        try {
                            escriuEstatPartida(estat[0]);
                        } catch (Exception e) {
                            System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                        }
                        System.out.print(estat[1]);
                    }
                    catch (ExcepcioCarregaPartida|ExcepcioPartidaTancada|ExcepcioPartidaAcabada e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                    catch (ExcepcionPosicioIncorrecta|ExcepcioValorInvalid e) {
                        System.out.println(e.getMessage());
                        System.out.println("Tingues en compte que la fila i la columna comencen per 0 fins a grau-1 i el valor ha d'estar entre 1 i la grau del tauler.");
                    }
                    break;
                case 2:
                    try {
                        String[] estat = controladorPartida.desferMoviment();
                        System.out.println("Estat de la partida:");
                        try {
                            escriuEstatPartida(estat[0]);
                        } catch (Exception e) {
                            System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                        }
                        System.out.print(estat[1]);
                    } catch (ExcepcioPartidaTancada|ExcepcioPartidaAcabada e) {
                        System.out.println(e.getMessage());
                        return;
                    } catch (ExcepcioValorInvalid|ExcepcioDoUndo|ExcepcionPosicioIncorrecta e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 3:
                    try {
                        String[] estat = controladorPartida.referMoviment();
                        System.out.println("Estat de la partida:");
                        try {
                            escriuEstatPartida(estat[0]);
                        } catch (Exception e) {
                            System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                        }
                        System.out.print(estat[1]);
                    } catch (ExcepcioPartidaTancada|ExcepcioPartidaAcabada e) {
                        System.out.println(e.getMessage());
                        return;
                    } catch (ExcepcioValorInvalid|ExcepcioDoUndo|ExcepcionPosicioIncorrecta e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 4:
                    try{
                        String[] estat = controladorPartida.donaPista();
                        System.out.println("Estat de la partida:");
                        try {
                            escriuEstatPartida(estat[0]);
                        } catch (Exception e) {
                            System.out.println("Error en el print de l'estat de la partida:" + e.getMessage());
                        }
                        System.out.print(estat[1]);
                    } catch (ExcepcioCarregaPartida|ExcepcioPartidaTancada|ExcepcioPartidaAcabada e) {
                        System.out.println(e.getMessage());
                        return;
                    } catch (ExcepcioValorInvalid|ExcepcionPosicioIncorrecta e) {
                        System.out.println("Error a l'hora de generar una pista, disculpi les molèsties:" + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        boolean guardada = controladorPartida.guardarPartida(nomUsuari);
                        if (guardada) System.out.println("Partida guardada correctament.");
                        else System.out.println("No s'ha pogut guardar la partida, torna-ho a intentar.");
                    } catch (ExcepcioPartidaTancada | ExcepcioCarregaPartida | ExcepcioPartidaAcabada |
                             ExcepcioNoPermisUsuari e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                    break;
                case 6:
                    try {
                        String[] acabada = controladorPartida.acabarPartida();
                        if (Boolean.parseBoolean(acabada[0])){
                            System.out.println("Felicitats!!! La solució és correcta.");
                            int segons = Integer.parseInt(acabada[2]);
                            java.time.Duration duracio = java.time.Duration.ofSeconds(segons);
                            int minuts = (int) duracio.toMinutes();
                            int segonsfinals = segons - minuts*60;
                            System.out.println("Has trigat " + minuts + " minuts i " + segonsfinals + " segons en resoldre el tauler.");
                            if (Boolean.parseBoolean(acabada[1])) {
                                System.out.println("Has acabat la partida sense ajuda ni guardant-la, això et permetrà participar als rankings. :)");
                            }
                            else {
                                System.out.println("Has acabat la partida amb ajuda o guardant-la, no podràs participar als rankings. :(");
                            }
                            return;
                        }
                        else System.out.println("No s'ha pogut acabar la partida, torna-ho a intentar.");
                    } catch (ExcepcioPartidaTancada | ExcepcioCarregaPartida | ExcepcioPartidaAcabada e) {
                        System.out.println(e.getMessage());
                        return;
                    } catch (ExcepcioPartidaMalament e) {
                        System.out.println("La partida és incorrecta continua buscant una solució.");
                        break;
                    }
                    break;
                case 7:
                    try{
                        boolean guardada = controladorPartida.tancarIguardarPartida();
                        if (guardada){
                            System.out.println("Partida guardada correctament.");
                            return;
                        }
                        else System.out.println("No s'ha pogut guardar la partida, torna-ho a intentar.");
                    } catch (ExcepcioPartidaTancada|ExcepcioCarregaPartida|ExcepcioPartidaAcabada e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                    break;
                case 8:
                        controladorPartida.tancaPartida();
                        return;
            }
        }
    }
    public static void escriuEstatPartida(String grid) throws Exception {
        String[] files = grid.split("\n");
        char [][] tauler = new char[files.length][files.length];
        for (int i = 0; i < files.length; i++) {
            String[] chars = files[i].split(" ");
            for (int j = 0; j < chars.length; j++) {
                tauler[i][j] = chars[j].charAt(0);
            }
        }
        io.write(' ');
        io.write(' ');
        io.write(' ');
        for (int i = 0; i < tauler[0].length; i++) {
            io.write(' ');
            io.write(' ');
            io.write(i);
            io.write(' ');
        }
        io.writeln();
        for (int i = 0; i < tauler.length; i++) {
            io.write(i);
            io.write(' ');
            io.write('|');
            io.write(' ');
            for (int j = 0; j < tauler[i].length; j++) {
                if (tauler[i][j] == '0') {
                    tauler[i][j] = '_';
                }
                io.write(' ');
                io.write(tauler[i][j]);
                io.write(' ');
            }
            io.write('|');
            io.writeln();
        }
    }
}
