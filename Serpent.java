package model;

import java.util.Random;
import java.util.Vector;

public class Serpent {
	private Pomme pomme;
	private int nbColonnes;
	private int nbLignes;
	private Vector<Tranche> tranches;
	private Direction direction;

	public Serpent() {
		this(10, 20);
	}

	public Serpent(int nbLignes, int nbColonnes) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.tranches = new Vector<>();
		tranches.add(new Tranche(nbLignes / 2, (nbColonnes / 2)));
		tranches.add(new Tranche(nbLignes / 2, (nbColonnes / 2) - 1));
		tranches.add(new Tranche(nbLignes / 2, (nbColonnes / 2) - 2));
		this.pomme = tirerPomme();
		this.direction = Direction.Droite;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public boolean caseVide(int ligne, int colonne) {
		return !casePomme(ligne, colonne) && !caseTranche(ligne, colonne);
	}

	public boolean caseTete(int ligne, int colonne) {
		return tranches.get(0).enPosition(ligne, colonne);
	}

	public boolean caseTranche(int ligne, int colonne) {
		for (Tranche tranche : tranches)
			if (tranche.enPosition(ligne, colonne))
				return true;
		return false;
	}

	public boolean casePomme(int ligne, int colonne) {
		return pomme.enPosition(ligne, colonne);
	}

	public Pomme tirerPomme() {
		Pomme pomme = null;
		Random random = new Random();
		do {
			pomme = new Pomme(random.nextInt(getNbLignes()-1), random.nextInt(getNbColonnes()-1));
		} while (caseTranche(pomme.getLigne(), pomme.getColonne()));

		return pomme;
	}

	public boolean partiePerdue() {
		return (tranches.get(0).getColonne() == getNbColonnes() - 1)
				|| (tranches.get(0).getColonne() == 0)
				|| (tranches.get(0).getLigne() == 0)
				|| (tranches.get(0).getLigne() == getNbLignes() - 1);	
	}
		
	public boolean snakeEat() {
		return ((tranches.get(0).getColonne() == pomme.getColonne()) && ((tranches.get(0).getLigne() == pomme.getLigne())));
	}
	
	public boolean snakeDead() {
		for (int index = 1 ; index < tranches.size(); index++) {
			if ((tranches.get(0).getColonne() == tranches.get(index).getColonne()) && (tranches.get(0).getLigne() == tranches.get(index).getLigne()))
				return true;
		}
		return false;
	}
	
		public void avancer() {
		if (partiePerdue())
			return;
		
		Tranche tete = tranches.get(0);	
		getDirection();
		Vector<Tranche> tran = new Vector<Tranche>();
		tran.add(0,tete.trancheSuivante(getDirection()));
//		System.out.println("iter " + tren.size());
//		System.out.println("Direction  " + 	getDirection());
//		System.out.println("colonne 0 " + (tran.get(0).getColonne() == tranches.get(0).getColonne()));
//		System.out.println("colonne 1 " + (tran.get(0).getColonne() == tranches.get(0).getColonne()));
//		System.out.println("colonne 2 " + (tran.get(0).getColonne() == tranches.get(1).getColonne()));
//		System.out.println("Ligne 0 " + (tran.get(0).getLigne() == tranches.get(0).getLigne()));
//		System.out.println("Ligne 1 " + (tran.get(0).getLigne() == tranches.get(1).getLigne()));
//		System.out.println("Ligne 2 " + (tran.get(0).getLigne() == tranches.get(2).getLigne()));

		if ((tran.get(0).getColonne() == tranches.get(1).getColonne()) && (tran.get(0).getLigne() == tranches.get(1).getLigne())){
			tran.add(0,tete.trancheSuivanteinv(getDirection()));
		}

		for (int index = 1 ; index < tranches.size(); index = index + 1 ) {
		if ((tran.get(0).getColonne() == tranches.get(index).getColonne()) && (tran.get(0).getLigne() == tranches.get(index).getLigne()))
			return;
		}
		
		tranches.add(0,tran.get(0));
		tranches.remove(tranches.size() - 1);
		
		if (snakeEat()) {
			tranches.add(0,tran.get(0));
			pomme = tirerPomme();
		}
		
		return;
	}

	public Direction getDirection() {
		return direction;
	}

	public void changerDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Permet d'obtenir la vitesse du serpent en milliseconde.
	 * 
	 * @return La vitesse.
	 */
	public int getDelay() {
		return 200;
	}
	

}
