
import java.util.ArrayList;

public class Utilisateurs {
	
	private static int id=0;
	private int idUtil;
	private ArrayList<Integer> listeIdDonnees = new ArrayList<Integer>();
	private int noeudAccessible;
	private int poidsArcs;
	
	
	public Utilisateurs(ArrayList<Integer> listeIdDonnees, int noeudAccessible,int poidsArcs) {
		id++;
		this.idUtil = id;
		this.listeIdDonnees = listeIdDonnees;
		this.noeudAccessible = noeudAccessible;
		this.poidsArcs = poidsArcs;
	}

	
	// GETTERS
	
	public int getIdUtil() {
		return idUtil;
	}
	
	public int getPoidsArcs() {
		return this.poidsArcs;
	}

	public int getNoeudAccessible() {
		return noeudAccessible;
	}

	public ArrayList<Integer> getListeIdDonnees() {
		return listeIdDonnees;
	}
	
	
	// SETTERS

	public void setListeIdDonnees(ArrayList<Integer> listeIdDonnees) {
		this.listeIdDonnees = listeIdDonnees;
	}

	public void setNoeudAccessible(int noeudAccessible) {
		this.noeudAccessible = noeudAccessible;
	}

	
	// PERMET D'AFFICHER L'UTILISATEUR
	
	public String toString() {
		return "Utilisateurs [idUtil=" + idUtil + ", listeIdDonnees=" + listeIdDonnees + ", noeudAccessible="
				+ noeudAccessible + "]";
	}	
	
	public void reinitialise() {
		id=0;
	}
}