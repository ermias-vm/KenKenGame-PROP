package main.Drivers;

import java.util.ArrayList;

public class EscriptorPartida {
    private final static inout io = new inout() ;
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
    public static void main(String[] args) throws Exception {
        String grid = "1 2 3\n4 5 6\n7 8 9\n";
        escriuEstatPartida(grid);
        ArrayList<String> regions = new ArrayList<String>() {{
            add("v(0,0) + v(0,1) + v(0,2) = 8");
            add("v(1,0) / v(1,1) = 4");
            add("v(1,2) * v(2,0) * v(2,1) * v(2,2) = 126");
        }};
        System.out.print("on: \n");
        for (String region : regions) {
            System.out.print(region + "\n");
        }
        System.out.println();
    }
}
