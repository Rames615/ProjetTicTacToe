import java.util.Scanner;

public class TicTacToe {
    private static char[][] plateau = new char[3][3];
    private static char joueurActuel = 'X';

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        initialiserPlateau();
        boolean partieEnCours = true;

        System.out.println("=========================================");
        System.out.println("       BIENVENUE AU TIC-TAC-TOE !        ");
        System.out.println("=========================================");
        System.out.println("Les coordonnées vont de 0 à 2 (Ligne et Colonne).\n");

        while (partieEnCours) {
            afficherPlateau();
            System.out.println("Tour du Joueur " + joueurActuel);
            
            int ligne, colonne;
            
            while (true) {
                System.out.print("Entrez la ligne (0, 1, 2) : ");
                if (!clavier.hasNextInt()) {
                    System.out.println("⚠️ Entrée invalide ! Entrez un chiffre.");
                    clavier.next();
                    continue;
                }
                ligne = clavier.nextInt();

                System.out.print("Entrez la colonne (0, 1, 2) : ");
                if (!clavier.hasNextInt()) {
                    System.out.println("⚠️ Entrée invalide ! Entrez un chiffre.");
                    clavier.next();
                    continue;
                }
                colonne = clavier.nextInt();
                
                if (ligne >= 0 && ligne < 3 && colonne >= 0 && colonne < 3) {
                    if (plateau[ligne][colonne] == ' ') {
                        break; 
                    } else {
                        System.out.println("⚠️ Case déjà occupée ! Réessayez.");
                    }
                } else {
                    System.out.println("⚠️ Coordonnées hors limites ! Réessayez.");
                }
            }

            plateau[ligne][colonne] = joueurActuel;

            if (checkWin()) {
                afficherPlateau();
                System.out.println("🎉 FÉLICITATIONS ! Le joueur " + joueurActuel + " a gagné ! 🎉");
                partieEnCours = false;
            } else if (isBoardFull()) {
                afficherPlateau();
                System.out.println("🤝 MATCH NUL ! La grille est pleine. 🤝");
                partieEnCours = false;
            } else {
                joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
            }
        }
        clavier.close();
    }

    public static void initialiserPlateau() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                plateau[i][j] = ' ';
            }
        }
    }

    public static void afficherPlateau() {
        System.out.println("\n  0   1   2 "); 
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); 
            for (int j = 0; j < 3; j++) {
                System.out.print(plateau[i][j]);
                if (j < 2) System.out.print(" | "); 
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------"); 
        }
        System.out.println();
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plateau[i][j] == ' ') {
                    return false; 
                }
            }
        }
        return true; 
    }

    public static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (plateau[i][0] == joueurActuel && plateau[i][1] == joueurActuel && plateau[i][2] == joueurActuel) {
                return true;
            }
            if (plateau[0][i] == joueurActuel && plateau[1][i] == joueurActuel && plateau[2][i] == joueurActuel) {
                return true;
            }
        }
        if (plateau[0][0] == joueurActuel && plateau[1][1] == joueurActuel && plateau[2][2] == joueurActuel) {
            return true;
        }
        if (plateau[0][2] == joueurActuel && plateau[1][1] == joueurActuel && plateau[2][0] == joueurActuel) {
            return true;
        }
        return false; 
    }
}
