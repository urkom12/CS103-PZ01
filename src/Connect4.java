import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Urkom
 */
public class Connect4 {
    private static final int RED = 6;
    private static final int KOLONA = 7;
    private static final char PRAZNO = '-';
    private static final char PrviIgrac = 'C';
    private static final char DrugiIgrac = 'Z';

    private char[][] tabla;
    private char TrenutniIgrac;

    public Connect4() {
        tabla = new char[RED][KOLONA];
        TrenutniIgrac = PrviIgrac;
        initializeBoard();
    }

    private void initializeBoard() {
        for (char[] row : tabla) {
            Arrays.fill(row, PRAZNO);
        }
    }

    public void Zapocni() {
        boolean kraj = false;
        int brojac = 0;

        while (!kraj) {
            prikaziTablu();

            int kolona = KolonaIgraca();
            boolean validan = ubaciDisk(kolona);

            if (!validan) {
                System.out.println("Upisi pravilan potez.");
                continue;
            }

            brojac++;

            if (proveraPobede()) {
                prikaziTablu();
                System.out.println("Igrac " + TrenutniIgrac + " je pobedio!");
                kraj = true;
            } else if (brojac == RED * KOLONA) {
                prikaziTablu();
                System.out.println("Neresno!");
                kraj = true;
            }

            TrenutniIgrac = (TrenutniIgrac == PrviIgrac) ? DrugiIgrac : PrviIgrac;
        }
    }

    private void prikaziTablu() {
        for (int red = RED - 1; red >= 0; red--) {
            for (int kol = 0; kol < KOLONA; kol++) {
                System.out.print(tabla[red][kol] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        for (int kol = 0; kol < KOLONA; kol++) {
            System.out.print(kol + " ");
        }
        System.out.println();
    }

    private int KolonaIgraca() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Igrac " + TrenutniIgrac + ", bira kolonu (0-" + (KOLONA - 1) + "): ");
        return sc.nextInt();
    }

    private boolean ubaciDisk(int kolona) {
        if (kolona < 0 || kolona >= KOLONA || tabla[RED - 1][kolona] != PRAZNO) {
            return false;
        }

        for (int red = 0; red< RED; red++) {
            if (tabla[red][kolona] == PRAZNO) {
                tabla[red][kolona] = TrenutniIgrac;
                return true;
            }
        }

        return false; // kolona je puna
    }

    private boolean proveraPobede() {
        // provera reda
        for (int red = 0; red < RED; red++) {
            for (int kol = 0; kol < KOLONA - 3; kol++) {
                if (tabla[red][kol] == TrenutniIgrac &&
                    tabla[red][kol + 1] == TrenutniIgrac &&
                    tabla[red][kol + 2] == TrenutniIgrac &&
                    tabla[red][kol + 3] == TrenutniIgrac) {
                    return true;
                }
            }
        }

        // provera kolona
        for (int red = 0; red < RED - 3; red++) {
            for (int kol = 0; kol < KOLONA; kol++) {
                if (tabla[red][kol] == TrenutniIgrac &&
                    tabla[red + 1][kol] == TrenutniIgrac &&
                    tabla[red + 2][kol] == TrenutniIgrac &&
                    tabla[red + 3][kol] == TrenutniIgrac) {
                    return true;
                }
            }
        }

        // provera dijagonala (gore levo - dole desno)
        for (int row = 0; row < RED - 3; row++) {
            for (int kol = 0; kol< KOLONA - 3; kol++) {
                if (tabla[row][kol] == TrenutniIgrac &&
                    tabla[row + 1][kol + 1] == TrenutniIgrac &&
                    tabla[row + 2][kol + 2] == TrenutniIgrac &&
                    tabla[row + 3][kol + 3] == TrenutniIgrac) {
                    return true;
                }
            }
        }

        // provera dijagonala (gore desno - dole levo)
        for (int red= 0; red < RED - 3; red++) {
            for (int kol = 3; kol < KOLONA; kol++) {
                if (tabla[red][kol] == TrenutniIgrac &&
                    tabla[red + 1][kol - 1] == TrenutniIgrac &&
                    tabla[red + 2][kol - 2] == TrenutniIgrac &&
                    tabla[red + 3][kol - 3] == TrenutniIgrac) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        game.Zapocni();
    }
}
