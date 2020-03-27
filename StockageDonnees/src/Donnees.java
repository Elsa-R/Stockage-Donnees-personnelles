

public class Donnees {
	
	private static int id=0;
	private int IDdonnee;
	private int taille;	
	
	public Donnees(int taille) {
		id++;
		IDdonnee = id;
		this.taille = taille;
	}
	
	
	// GETTERS

	public int getTaille() {
		return taille;
	}
	
	public int getIDdonnee() {
		return IDdonnee;
	}
	

	// SETTERS
	
	public void reinitialise() {
		id=0;
	}
	
	// PERMET D'AFFICHER LA DONNEE
	
	public String toString() {
		return "Donnees : "+this.IDdonnee + " taille : "+this.getTaille();
	}
}