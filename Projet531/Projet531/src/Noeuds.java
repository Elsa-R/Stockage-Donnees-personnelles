import java.util.ArrayList;


public class Noeuds {
	private static int cpt=0;
	private int id;
	private int capacite;
	private ArrayList<Integer> listeIdDonnee;
	private ArrayList<Noeuds> listeNoeudUtilisateur;
	private ArrayList<Utilisateur> listeUtilisateur;
	

	public Noeuds(int capacite) {
		cpt++;
		this.id=cpt;
		this.capacite = capacite;
		listeIdDonnee= new ArrayList<Integer>();
		listeNoeudUtilisateur= new ArrayList<Noeuds>();
		listeUtilisateur= new ArrayList<Utilisateur>();
	}


	public String toString() {
		return "Noeuds [id=" + id + ", capacite=" + capacite
				+ ", listeIdDonnee=" + listeIdDonnee
				+ ", listeNoeudUtilisateur=" + listeNoeudUtilisateur + "]";
	}
	
	
	
}
