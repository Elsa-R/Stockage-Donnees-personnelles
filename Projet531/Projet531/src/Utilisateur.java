import java.util.ArrayList;
public class Utilisateur {
	private static int cpt=0;
	private int id;
	private ArrayList<Integer> listeIdDonnees;
	private int idNoeud;
	
	public Utilisateur(int idNoeud) {
		cpt++;
		this.id=cpt;
		this.idNoeud = idNoeud;
		listeIdDonnees= new ArrayList<Integer>();
	}

	public String toString() {
		return "Utilisateur [id=" + id + ", listeIdDonnees=" + listeIdDonnees
				+ ", idNoeud=" + idNoeud + "]";
	}
}
