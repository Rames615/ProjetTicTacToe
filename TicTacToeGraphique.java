import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGraphique extends JFrame implements ActionListener {
    // Un tableau de 9 boutons pour représenter la grille 3x3
    private JButton[][] boutons = new JButton[3][3];
    private char joueurActuel = 'X';
    private JLabel barreStatut;

    public TicTacToeGraphique() {
        // Configuration de la fenêtre principale Windows
        setTitle("Tic-Tac-Toe (Morpion) - Version Windows");
        setSize(400, 450); // Largeur, Hauteur en pixels
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setLayout(new BorderLayout());

        // 1. CRÉATION DE LA GRILLE (PANEL GRAPHIQUE)
        JPanel grillePanel = new JPanel();
        grillePanel.setLayout(new GridLayout(3, 3, 5, 5)); // Grille 3x3 avec espacements de 5px
        grillePanel.setBackground(Color.BLACK); // Fond noir pour simuler les lignes séparatrices

        // Initialisation des boutons de la grille
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutons[i][j] = new JButton("");
                boutons[i][j].setFont(new Font("Arial", Font.BOLD, 50)); // Gros texte lisible (X / O)
                boutons[i][j].setFocusPainted(false);
                boutons[i][j].setBackground(Color.WHITE); // Case blanche
                
                // On attache un écouteur de clic à chaque bouton
                boutons[i][j].addActionListener(this);
                grillePanel.add(boutons[i][j]);
            }
        }

        // 2. BARRE DE STATUT (EN BAS)
        barreStatut = new JLabel("Tour du Joueur X", SwingConstants.CENTER);
        barreStatut.setFont(new Font("Arial", Font.PLAIN, 18));
        barreStatut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des composants dans la fenêtre Windows
        add(grillePanel, BorderLayout.CENTER);
        add(barreStatut, BorderLayout.SOUTH);

    }

    // CETTE MÉTHODE S'ÉCUTE À CHAQUE FOIS QU'ON CLIQUE SUR UN BOUTON
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boutonClique = (JButton) e.getSource();

        // Si le bouton est déjà cliqué, on ne fait rien
        if (!boutonClique.getText().equals("")) {
            return;
        }

        // On écrit le symbole du joueur actuel sur le bouton graphique
        boutonClique.setText(String.valueOf(joueurActuel));
        if (joueurActuel == 'X') {
            boutonClique.setForeground(Color.BLUE); // X sera en bleu
        } else {
            boutonClique.setForeground(Color.RED);  // O sera en rouge
        }

        // Vérification de la victoire ou du match nul
        if (checkWin()) {
            barreStatut.setText("🎉 LE JOUEUR " + joueurActuel + " A GAGNÉ ! 🎉");
            verrouillerGrille();
            proposerRecommencer("Le joueur " + joueurActuel + " a gagné !");
        } else if (isBoardFull()) {
            barreStatut.setText("🤝 MATCH NUL ! La grille est pleine. 🤝");
            verrouillerGrille();
            proposerRecommencer("Match nul !");
        } else {
            // Changement de joueur
            joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
            barreStatut.setText("Tour du Joueur " + joueurActuel);
        }
    }

    // LOGIQUE DE VECTOIRE (Analogue à la version console mais lit le texte du bouton)
    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Lignes
            if (boutons[i][0].getText().equals(String.valueOf(joueurActuel)) &&
                boutons[i][1].getText().equals(String.valueOf(joueurActuel)) &&
                boutons[i][2].getText().equals(String.valueOf(joueurActuel))) return true;
            // Colonnes
            if (boutons[0][i].getText().equals(String.valueOf(joueurActuel)) &&
                boutons[1][i].getText().equals(String.valueOf(joueurActuel)) &&
                boutons[2][i].getText().equals(String.valueOf(joueurActuel))) return true;
        }
        // Diagonales
        if (boutons[0][0].getText().equals(String.valueOf(joueurActuel)) &&
            boutons[1][1].getText().equals(String.valueOf(joueurActuel)) &&
            boutons[2][2].getText().equals(String.valueOf(joueurActuel))) return true;
            
        if (boutons[0][2].getText().equals(String.valueOf(joueurActuel)) &&
            boutons[1][1].getText().equals(String.valueOf(joueurActuel)) &&
            boutons[2][0].getText().equals(String.valueOf(joueurActuel))) return true;

        return false;
    }

    // Vérifie s'il reste des boutons vides
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boutons[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    // Empêche de continuer à cliquer après une victoire
    public void verrouillerGrille() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutons[i][j].setEnabled(false);
            }
        }
    }

    // Boîte de dialogue Windows pour recommencer
    public void proposerRecommencer(String message) {
        int choix = JOptionPane.showConfirmDialog(this, message + "\nVoulez-vous rejouer ?", "Fin de partie", JOptionPane.YES_NO_OPTION);
        if (choix == JOptionPane.YES_OPTION) {
            // Réinitialisation
            joueurActuel = 'X';
            barreStatut.setText("Tour du Joueur X");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    boutons[i][j].setText("");
                    boutons[i][j].setEnabled(true);
                }
            }
        } else {
            System.exit(0); // Ferme l'application
        }
    }

    public static void main(String[] args) {
        // Lance l'application graphique de manière sécurisée
        SwingUtilities.invokeLater(() -> {
            TicTacToeGraphique fenetre = new TicTacToeGraphique();
            fenetre.setVisible(true);
        });
    }
}
