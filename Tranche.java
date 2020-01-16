package model;

public class Tranche {
	private int ligne;
	private int colonne;
	
	public Tranche(int ligne, int colonne) {
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
	
	public Tranche trancheSuivante(Direction direction) {
		if (direction == Direction.Bas) {
			return new Tranche(getLigne()+1,getColonne());
		} else 
			if (direction == Direction.Haut) {
			return new Tranche(getLigne()-1,getColonne());
		} else 
			if (direction == Direction.Droite) {
			return new Tranche(getLigne(),getColonne()+1);
		} else 
			if (direction == Direction.Gauche) {
			return new Tranche(getLigne(),getColonne()-1);
		}
		return null;
	}
	
	public Tranche trancheSuivanteinv(Direction direction) {
		if (direction == Direction.Bas) {
			return new Tranche(getLigne()-1,getColonne());
		} else 
			if (direction == Direction.Haut) {
			return new Tranche(getLigne()+1,getColonne());
		} else 
			if (direction == Direction.Droite) {
			return new Tranche(getLigne(),getColonne()-1);
		} else 
			if (direction == Direction.Gauche) {
			return new Tranche(getLigne(),getColonne()+1);
		}
		return null;
	}
	
}
