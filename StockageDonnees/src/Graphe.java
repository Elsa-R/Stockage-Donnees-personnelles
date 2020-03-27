import org.jgrapht.alg.*;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.ArrayList;
import java.util.List;

public class Graphe<Noeud,Edge> extends SimpleWeightedGraph {
	
	private ArrayList<Donnees> listeDonneesAPlacer = new ArrayList<Donnees>();
	private ArrayList<NoeudSysteme> listeNoeud = new ArrayList<NoeudSysteme>();
	
	public Graphe(Class arg0) {
		super(arg0);
	}
	
	
	// METHODE ADD
	
	public void ajouterDonneesAPlacer(ArrayList<Donnees> donnee) {
		for(int i = 0;i<donnee.size();i++) {
			this.listeDonneesAPlacer.add(donnee.get(i));
		}
	}
	
	public void ajouterNoeudGraphe(NoeudSysteme noeud) {
		this.listeNoeud.add(noeud);
	}
	
	
	// GETTERS
	
	public List getCheminLePlusCourt(NoeudSysteme n1,NoeudSysteme n2) {
		DijkstraShortestPath<NoeudSysteme,Edge> dij = new DijkstraShortestPath<NoeudSysteme,Edge> (this, n1, n2);
		return dij.findPathBetween(this, n1, n2);
	}
	
	public NoeudSysteme getNoeudById(int idNoeud) {
		for(int i = 0;i<this.listeNoeud.size();i++) {
			if (this.listeNoeud.get(i).getIdNoeud() == idNoeud){
				return this.listeNoeud.get(i);
			}
		}
		System.out.println("Le noeud cherche n'est pas dans le graphe");
		return null;
	}
	
	public ArrayList<NoeudSysteme> getNoeudAccessibleDesNoeudsAccessibles(NoeudSysteme n) {
		ArrayList<NoeudSysteme> listeNoeuds = new ArrayList<NoeudSysteme>();
		// On va recuperer tous les noeuds accessibles des noeuds accessibles
		for (NoeudSysteme noeudAccessible : n.getNoeudAccessibles()) {
			for(NoeudSysteme noeudAccessible2 : noeudAccessible.getNoeudAccessibles()) {
				listeNoeuds.add(noeudAccessible2);
			}
		}
		return listeNoeuds;
	}
	
	public ArrayList<Donnees> getCopieListe(ArrayList<Donnees> listeDonnees){
		ArrayList<Donnees> copieListe = new ArrayList<Donnees>();
		for(Donnees donnee : listeDonnees) {
			copieListe.add(donnee);
		}
		return copieListe;
	}
	
	
	// PLACER LES DONNEES
	
	public void placerDonneeDansGraphe(Utilisateurs util) {
		//initialisation des listes pour placer les donnees
		ArrayList<Donnees> copieListe = this.getCopieListe(this.listeDonneesAPlacer);
		//on recupere le noeud auquel l'utilisateur est lie
		NoeudSysteme noeudUtil = this.getNoeudById(util.getNoeudAccessible());
		//pour chaque donnee a placer
		for (int j = 0;j<this.listeDonneesAPlacer.size();j++) {
			//si la taille de la donnee est inférieure ou egale a la capacite du noeud
			if (this.listeDonneesAPlacer.get(j).getTaille() <= noeudUtil.getCapacite()) {
				//on ajoute la donnee dans ce noeud 
				noeudUtil.ajouterDonnee(this.listeDonneesAPlacer.get(j));
				//on retire cette donnee de la liste des donnees a ajouter
				copieListe.remove(this.listeDonneesAPlacer.get(j));
			//si la taille de la donnee est superieure a la capacite du noeud 
			}else {
				//on recupere tous les noeuds accessible depuis le noeud courant
				ArrayList<NoeudSysteme> nAccessibles = noeudUtil.getNoeudAccessibles();
				double min = 200;
				NoeudSysteme noeudSuivant = null;
				//on cherche le chemin le plus court possible entre le noeud courant et les autres noeuds accessibles
				for(NoeudSysteme n : nAccessibles) {
					//si la valeur de l'arc entre le noeud courant et le noeud accessible et < au min
					// et si le noeud accessible a assez de place pour stocker la donnee
					if (this.getEdgeWeight(this.getEdge(noeudUtil,n))< min & n.getCapacite()>= this.listeDonneesAPlacer.get(j).getTaille()) {
						//le min devient la valeur de l'arc entre le noeud courant et le noeud de la liste
						min = this.getEdgeWeight(this.getEdge(noeudUtil,n));
						noeudSuivant = n;
					}
				}
				// Si un noeud suivant existe, on ajoute la donnee dans ce noeud
				if(noeudSuivant != null) {
					noeudSuivant.ajouterDonnee(this.listeDonneesAPlacer.get(j));
					// On supprime la donnee qui a ete ajoute de la liste copieListe
					copieListe.remove(this.listeDonneesAPlacer.get(j));
				// S'il n'y a pas de noeud suivant
				}else {
					// On regarde sur les noeuds d'un autre chemin
					this.placerDonneesAutreChemin(noeudUtil, this.listeDonneesAPlacer.get(j));
					copieListe.remove(this.listeDonneesAPlacer.get(j));
				}
			}
		// S'il reste des donnees a placees
		}if(copieListe.size() > 0 ) {
			System.out.println(copieListe);
			System.out.println("Il n'y a actuellement pas assez de place pour placer cette donnee");
		// Si toutes les donnees ont pu etre placees
		}else {
			System.out.println("Les donnees de l'utilisateur "+util.getIdUtil()+" ont été placé");
		}
	}	
	
	public void placerDonneesAutreChemin(NoeudSysteme n,Donnees don) {
		//initialise la liste dont on a besoin
		ArrayList<NoeudSysteme> arrNoeud = this.getNoeudAccessibleDesNoeudsAccessibles(n);
		//pour chaque noeud descendant de n
		for(NoeudSysteme noeud : arrNoeud) {
			//si le noeud de la liste peut accueillir la donnee alors 
			if(noeud.getCapacite() >= don.getTaille()) {
				noeud.ajouterDonnee(don);
			}
		}
	}	
	
	public void placerDonneeAvec2Utilisateurs(Utilisateurs util1,Utilisateurs util2,Donnees don) {
		//on cherche le chemin le plus court entre les deux noeuds accessibles des utilisateurs
		ArrayList<Edge> cheminLePlusCourt = (ArrayList) this.getCheminLePlusCourt(this.getNoeudById(util1.getNoeudAccessible()),this.getNoeudById(util2.getNoeudAccessible()));
		//on va recuperer l'arc qui se trouve a la moitie du chemin entre les deux utilisateurs
		int indice = cheminLePlusCourt.size()/2;
		// On recupere le noeud de droite de l'arc
		String noeud = ""+cheminLePlusCourt.get(indice).toString().charAt(1);
		int idNoeud = Integer.parseInt(noeud);
		
		// S'il y a assez de place sur le noeud
		if(this.getNoeudById(idNoeud).getCapacite() >= don.getTaille()) {
			this.getNoeudById(idNoeud).ajouterDonnee(don);
		
		// Sinon
		}else {
			// On recupere le noeud de gauche de l'arc
			noeud = ""+cheminLePlusCourt.get(indice).toString().charAt(5);
			idNoeud = Integer.parseInt(noeud);
			
			if(this.getNoeudById(idNoeud).getCapacite() >= don.getTaille()) {
				this.getNoeudById(idNoeud).ajouterDonnee(don);
			}
			
		}
	}	
	
	public void placerDonneeMKP(Utilisateurs util) {
		String reponse="";
		// On recupere la copie de la liste des donnees a placer pour cet utilisateur
		ArrayList<Donnees> copieListe = this.getCopieListe(listeDonneesAPlacer);
		// On recupere le noeud accessible de l'utilisateur
		NoeudSysteme noeudUtilisateur = this.getNoeudById(util.getNoeudAccessible());
		// On cherche a placer le plus de donnees possibles sur le premier noeud 
		ArrayList<Donnees> donneesAEnlever=this.maxDonneesSurNoeud(noeudUtilisateur,copieListe);
		// On place les donnees sur ce noeud et on les enleve de la liste des donnees a placer
		for (int i=0;i<donneesAEnlever.size();i++) {
			noeudUtilisateur.ajouterDonnee(donneesAEnlever.get(i));
			copieListe.remove(donneesAEnlever.get(i));
		}
		//on regarde tous les noeuds accessible depuis le noeud courant
		ArrayList<NoeudSysteme> nAccessibles = noeudUtilisateur.getNoeudAccessibles();
		double min = 200;
		NoeudSysteme nSuivant = null;
		//on cherche le chemin le plus court possible entre le noeud courant et les autres noeuds
		//pour tous les noeuds dans la liste
		for(NoeudSysteme n : nAccessibles) {
			//si le poid de l'arc entre le noeud courant et le noeud de la liste et inf au min
			// et si le noeud de la liste a assez de memoire pour stocker la donnee
			if (this.getEdgeWeight(this.getEdge(noeudUtilisateur,n))< min & this.maxDonneesSurNoeud(n,copieListe).size()!=0) {
							//le min devient le poid de l'arc entre le noeud courant et le noeud de la liste
							min = this.getEdgeWeight(this.getEdge(noeudUtilisateur,n));
							//le noeud suivant devient le noeud de la liste
							nSuivant = n;
			}
		}
		//on ajoute la donnee dans le noeud suivant si un noeud suivant est disponible
		if(nSuivant != null) {
			reponse=this.placerDonneeMKPRecursif(copieListe,util,nSuivant);	
			System.out.println(reponse);
		}if(copieListe.size() > 0 ) {
			System.out.println(copieListe);
			System.out.println("Il n'y a actuellement pas assez de place pour placer cette donnee");
		}else {
			System.out.println("Les donnees de l'utilisateur "+util.getIdUtil()+" ont été placé");
		}
	}
	
	public String placerDonneeMKPRecursif(ArrayList<Donnees>liste,Utilisateurs util,NoeudSysteme n) {
		ArrayList<Donnees> listeDonnee=this.maxDonneesSurNoeud(n,liste);
		for (int i=0;i<listeDonnee.size();i++) {
			n.ajouterDonnee(listeDonnee.get(i));
			liste.remove(listeDonnee.get(i));
		}
		//on regarde tous les noeuds accessible depuis le noeud courant
		ArrayList<NoeudSysteme> nAccessibles = n.getNoeudAccessibles();
		double min = 200;
		NoeudSysteme nSuivant = null;
		//on cherche le chemin le plus court possible entre le noeud courant et les autres noeuds
		//pour tous les noeuds dans la liste
		for(NoeudSysteme noeud : nAccessibles) {
		//si le poid de l'arc entre le noeud courant et le noeud de la liste et inf au min
		// et si le noeud de la liste a assez de memoire pour stocker la donnee
			if (this.getEdgeWeight(this.getEdge(n,noeud))< min & this.maxDonneesSurNoeud(noeud,liste).size()!=0) {
				//le min devient le poid de l'arc entre le noeud courant et le noeud de la liste
				min = this.getEdgeWeight(this.getEdge(n,noeud));
				//le noeud suivant devient le noeud de la liste
				nSuivant = noeud;
			}
		}
		if (nSuivant != null & liste.size()!=0) {
			placerDonneeMKPRecursif(liste,util,nSuivant);
		}if (nSuivant == null & liste.size()!=0) {
			return "Toutes les donnees n'ont pas pu etre placees";
		}else {
			return "Toutes les donnees ont pu etre placees";
		}
	}
	
	// On cherche a placer le plus de donnees possible sur un noeud, cette methode renvoie la plus grande liste de donnees
	// qui peuvent etre placees sur le noeud passe en parametre
	public ArrayList<Donnees> maxDonneesSurNoeud(NoeudSysteme n,ArrayList<Donnees> listeDonnees) {
		int cptFinal=0;
		ArrayList<Donnees> listeDonneesPlaceesFinal = new ArrayList<Donnees>();
		
		for(int i=0;i<listeDonnees.size();i++) {
			ArrayList<Donnees> listeDonneesPlacees = new ArrayList<Donnees>();
			int cpt=0;
			listeDonneesPlacees.add(listeDonnees.get(i));
			int taille = n.getCapacite();
			if (listeDonnees.get(i).getTaille()<=taille){
				cpt=cpt+1;
				taille = taille - listeDonnees.get(i).getTaille();
				if (i!=listeDonnees.size()) {
					for(int j=i+1;j<listeDonnees.size();j++) {
						if (listeDonnees.get(j).getTaille()<=taille){
							cpt=cpt+1;
							listeDonneesPlacees.add(listeDonnees.get(j));
							taille = taille - listeDonnees.get(j).getTaille();
						}
					}
				}if(cpt>cptFinal) {
					cptFinal=cpt;
					listeDonneesPlaceesFinal=listeDonneesPlacees;
				}	
			}
		}
		return listeDonneesPlaceesFinal;
	}
	
	
	// AFFICHAGE GRAPHE
	
	public void affichageNoeudGraphe() {
		for(NoeudSysteme n : this.listeNoeud) {
			System.out.println("Noeud : " + n.getIdNoeud() + " capacite : "+n.getCapacite() + " donnees placees : "+n.getListeDonneesStockees());
		}
	}
}