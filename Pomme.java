package model;

public class Pomme {
	private int ligne;
	private int colonne;
	
	public Pomme(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public boolean enPosition(int ligne, int colonne) {
		return this.ligne == ligne && this.colonne == colonne;
	}
}
