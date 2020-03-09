
public class Donnees {
	private static int cpt=0;
	private int id;
	private int taille;
	
	public Donnees(int taille) {
		cpt++;
		this.id=cpt;
		this.taille = taille;
	}

	public String toString() {
		return "Donnees [taille=" + taille + ", id=" + id + "]";
	}

}
