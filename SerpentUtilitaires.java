package model;

import java.awt.Color;
import java.awt.Graphics;

public class SerpentUtilitaires {

	public static void afficher(Serpent serpent) {
		for (int ligne = 0; ligne < serpent.getNbLignes(); ligne++)
			afficherLigne(serpent, ligne);
	}

	private static void afficherLigne(Serpent serpent, int ligne) {
		for (int colonne = 0; colonne < serpent.getNbColonnes(); colonne++)
			afficherCase(serpent, ligne, colonne);
		System.out.println();
	}

	private static void afficherCase(Serpent serpent, int ligne, int colonne) {
		if (serpent.caseVide(ligne, colonne)) {
			System.out.print("-");
			return;
		}

		if (serpent.caseTete(ligne, colonne)) {
			System.out.print("X");
			return;
		}

		if (serpent.caseTranche(ligne, colonne)) {
			System.out.print("0");
			return;
		}

		if (serpent.casePomme(ligne, colonne)) {
			System.out.print("P");
			return;
		}
	}

//	dessinerLigne(serpent, ligne);
	/**
	 * Permet de dessiner le serpent dans une zone de dessin.
	 * 
	 * @param serpent Le serpent.
	 * @param g       La zone de dessin.
	 * @param width   La largeur de la zone de dessin.
	 * @param height  La hauteur de la zone de dessin.
	 */
	public static void dessiner(Serpent serpent, Graphics g, int width, int height) {

		for (int ligne = 0; ligne < serpent.getNbLignes(); ligne++)
			dessinerLigne(serpent, g, ligne);
		
		g.setColor(Color.CYAN);
		for (int ligne = 0; ligne <= 600; ligne = ligne + 30)
			g.drawLine(0, ligne, 600,ligne);
		
		for (int colonne = 0; colonne <= 600; colonne = colonne + 30)
			g.drawLine(colonne,0 , colonne, 600);
	}

	private static void dessinerLigne(Serpent serpent, Graphics g, int ligne) {
		for (int colonne = 0; colonne < serpent.getNbColonnes(); colonne++)
			dessinerCase(serpent, g, ligne, colonne);
	}

	private static int getPositionxy(int Taille, int point) {
		return Taille * point;
	}

	private static void dessinerCase(Serpent serpent, Graphics g, int ligne, int colonne) {
		int TailleCase = 30;
		int x = getPositionxy(TailleCase, colonne);
		int y = getPositionxy(TailleCase, ligne);
		Color color = Color.BLACK;
		
		if (serpent.caseVide(ligne, colonne)) {
			color = Color.GRAY;
		}

		if (serpent.caseTranche(ligne, colonne)) {
			color = Color.BLUE;
		}
		
		if (serpent.caseTete(ligne, colonne)) {
			color = Color.RED;
		}
		
		if (serpent.casePomme(ligne, colonne)) {
			color = Color.GREEN;
		}
		g.setColor(color);
		g.drawRect(x, y, TailleCase, TailleCase);
		g.fillRect(x, y, TailleCase, TailleCase);
	}

//		g.setColor(Color.MAGENTA);
//		g.drawString("*", 50, 340);
//		g.fillRect(0, 0, width, height);		
//		g.drawRect(50, 330, 400, 50);
}
