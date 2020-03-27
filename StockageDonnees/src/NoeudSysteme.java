
import java.util.ArrayList;

public class NoeudSysteme implements Noeud {
	private static int id=0;
	private int idNoeudSysteme;
	private int capacite;
	private ArrayList<Integer> listeDonneesStockees = new ArrayList<Integer>();
	private ArrayList<NoeudSysteme> listeNoeudAccessibles = new ArrayList<NoeudSysteme>();
	
	public NoeudSysteme( int capacite) {
		id++;
		this.idNoeudSysteme = id;
		this.capacite = capacite;
	}
	
	
	// GETTERS
	
	public ArrayList<Integer> getListeDonneesStockees() {
		return listeDonneesStockees;
	}

	public int getCapacite() {
		return this.capacite;
	}
	
	public int getIdNoeud() {
		// TODO Auto-generated method stub
		return this.idNoeudSysteme;
	}
	
	public ArrayList<NoeudSysteme> getNoeudAccessibles() {
		return listeNoeudAccessibles;
	}
	
	
	// SETTERS

	public void setListeDonneesStockees(ArrayList<Integer> listeDonneesStockees) {
		this.listeDonneesStockees = listeDonneesStockees;
	}
	
	public void setNoeudAccessibles(ArrayList<NoeudSysteme> listeNoeudAccessibles) {
		this.listeNoeudAccessibles = listeNoeudAccessibles;
	}
	
	public void reinitialise() {
		id=0;
	}

	
	// ADD
	
	public void ajouterNoeudAccessible(NoeudSysteme noeud) {
		this.listeNoeudAccessibles.add(noeud);
	}
	
	public void ajouterDonnee(Donnees donnee) {
		this.listeDonneesStockees.add(donnee.getIDdonnee());
		this.capacite = this.capacite - donnee.getTaille() ;
	}


	@Override
	public String toString() {
		return ""+idNoeudSysteme;
	}
}