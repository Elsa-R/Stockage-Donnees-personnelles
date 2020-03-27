import java.util.ArrayList;

import org.jgrapht.graph.*;

public class Main {
	
	public static void main(String[] args) {
		
		
		//=============================================================================
		
		// LE GRAPHE g CORRESPOND A LA QUESTION 2 DU SUJET
		
		//=============================================================================
		
		System.out.println("\t Question 2 : \n");
		// CREATION DU GRAPHE
		
		Graphe<NoeudSysteme, Edge> g = new Graphe <>(Edge.class);
		
		
		// CREATION DES DONNEES A PLACER
		
		Donnees donnee1 = new Donnees(40);
		Donnees donnee2 = new Donnees(25);
		Donnees donnee3 = new Donnees(25);
		
		
		// ON CREE UNE LISTE DE CES DONNEES QUE L'ON MET DANS LE GRAPHE
		
		ArrayList<Donnees> listeDonnees = new ArrayList<Donnees>();
		listeDonnees.add(donnee1);
		listeDonnees.add(donnee2);
		listeDonnees.add(donnee3);
		g.ajouterDonneesAPlacer(listeDonnees);
		
		
		// CREATION DES NOEUDS SYSTEME
		
		NoeudSysteme n1 = new NoeudSysteme(50);
		NoeudSysteme n2 = new NoeudSysteme(40);
		NoeudSysteme n3 = new NoeudSysteme(40);
		
		
		// ON AJOUTE LES NOEUDS DANS LE GRAPHE
		
		g.ajouterNoeudGraphe(n1);
		g.ajouterNoeudGraphe(n2);
		g.ajouterNoeudGraphe(n3);
		
		// ON LES RELIE ENTRE EUX
		
		n1.ajouterNoeudAccessible(n2);
		
		n2.ajouterNoeudAccessible(n1);
		n2.ajouterNoeudAccessible(n3);
		
		n3.ajouterNoeudAccessible(n2);
		
		g.addVertex(n1);
		g.addVertex(n2);
		g.addVertex(n3);
		
		DefaultWeightedEdge edge12 = (DefaultWeightedEdge)g.addEdge(n1, n2);
		DefaultWeightedEdge edge23 = (DefaultWeightedEdge)g.addEdge(n2, n3);
		
		g.setEdgeWeight(edge12, 1);
		g.setEdgeWeight(edge23, 1);
		
		
		// ON MET LES ID DES DONNEES CREEES DANS UNE LISTE
		// CELA VA REPRESENTER LES DONNEES QUI INTERESSENT LES UTILISATEURS
		
		ArrayList<Integer> listeDesDonnees = new ArrayList<Integer>();
		listeDesDonnees.add(donnee1.getIDdonnee());
		listeDesDonnees.add(donnee2.getIDdonnee());
		listeDesDonnees.add(donnee3.getIDdonnee());
		
		
		// ON CREE UN UTILISATEUR ET ON L'AJOUTE AU GRAPHE
		
		Utilisateurs utilisateur1 = new Utilisateurs(listeDesDonnees,1,2);
		g.placerDonneeDansGraphe(utilisateur1);
		
		g.affichageNoeudGraphe();
		
		System.out.println(g);
		
		// REINITIALISATION 
		
		donnee3.reinitialise();
		utilisateur1.reinitialise();
		n3.reinitialise();
		
		
		//=============================================================================
		
		// LE GRAPHE g2 CORRESPOND A LA QUESTION 3
		
		//=============================================================================
		
		System.out.println("\n \t Question 3 : ");
		Graphe<NoeudSysteme, Edge> g2 = new Graphe <>(Edge.class);
		
		
		// CREATION DES DONNEES A PLACER
		
		Donnees donnee11 = new Donnees(40);
		ArrayList<Donnees> listeDonnees2 = new ArrayList<Donnees>();
		listeDonnees2.add(donnee11);
		g2.ajouterDonneesAPlacer(listeDonnees2);
		System.out.println("\n On cherche a placer : "+donnee11+"\n \t entre les deux utilisateurs");
		
		// CREATION DES NOEUDS SYSTEME ET ON LES RELIE ENTRE EUX
		
		NoeudSysteme n11 = new NoeudSysteme(50);
		NoeudSysteme n12 = new NoeudSysteme(40);
		NoeudSysteme n13 = new NoeudSysteme(45);
		NoeudSysteme n14 = new NoeudSysteme(40);
		
		g2.ajouterNoeudGraphe(n11);
		g2.ajouterNoeudGraphe(n12);
		g2.ajouterNoeudGraphe(n13);
		g2.ajouterNoeudGraphe(n14);
		
		n11.ajouterNoeudAccessible(n12);	
		n12.ajouterNoeudAccessible(n13);
		n13.ajouterNoeudAccessible(n14);
		
		g2.addVertex(n11);
		g2.addVertex(n12);
		g2.addVertex(n13);
		g2.addVertex(n14);
		
		
		DefaultWeightedEdge edge1112 = (DefaultWeightedEdge)g2.addEdge(n11, n12);
		DefaultWeightedEdge edge1213 = (DefaultWeightedEdge)g2.addEdge(n12, n13);
		DefaultWeightedEdge edge1314 = (DefaultWeightedEdge)g2.addEdge(n13, n14);
		
		
		g2.setEdgeWeight(edge1112, 1);
		g2.setEdgeWeight(edge1213, 1);
		g2.setEdgeWeight(edge1314, 1);
		
		
		// CREATION DES UTILISATEURS
		ArrayList<Integer> listeDesDonnees2 = new ArrayList<Integer>();
		listeDesDonnees2.add(donnee11.getIDdonnee());
		Utilisateurs utilisateur11 = new Utilisateurs(listeDesDonnees2,1,2);
		Utilisateurs utilisateur12= new Utilisateurs(listeDesDonnees2,4,2);

		
		// ON PLACE LES DONNEES DANS LE GRAPHE
		
		System.out.println("\n Voila ou est place la donnee : \n");
		g2.placerDonneeAvec2Utilisateurs(utilisateur11, utilisateur12, donnee11);
		g2.affichageNoeudGraphe();
		
		donnee11.reinitialise();
		utilisateur12.reinitialise();
		n13.reinitialise();
		
		//=============================================================================
		
		// LE GRAPHE g3 CORRESPOND A LA QUESTION 4
		
		//=============================================================================
		
		System.out.println("\n \t Question 4 : ");
		Graphe<NoeudSysteme, Edge> g3 = new Graphe <>(Edge.class);
				
		// CREATION DES DONNEES A PLACER
				
		Donnees donnee21 = new Donnees(40);
		Donnees donnee22 = new Donnees(25);
		Donnees donnee23 = new Donnees(25);
		
		ArrayList<Donnees> listeDonnees3 = new ArrayList<Donnees>();
		listeDonnees3.add(donnee21);
		listeDonnees3.add(donnee22);
		listeDonnees3.add(donnee23);
		g3.ajouterDonneesAPlacer(listeDonnees3);
		System.out.println("\n On cherche a placer : "+listeDonnees3);
				
		// CREATION DES NOEUDS SYSTEME ET ON LES RELIE ENTRE EUX
				
		NoeudSysteme n21 = new NoeudSysteme(50);
		NoeudSysteme n22 = new NoeudSysteme(40);
		NoeudSysteme n23 = new NoeudSysteme(40);
			
		g3.ajouterNoeudGraphe(n21);
		g3.ajouterNoeudGraphe(n22);
		g3.ajouterNoeudGraphe(n23);
			
		n21.ajouterNoeudAccessible(n22);	
		n22.ajouterNoeudAccessible(n23);
			
		g3.addVertex(n21);
		g3.addVertex(n22);
		g3.addVertex(n23);
			
				
		DefaultWeightedEdge edge2122 = (DefaultWeightedEdge)g3.addEdge(n21, n22);
		DefaultWeightedEdge edge2223 = (DefaultWeightedEdge)g3.addEdge(n22, n23);
			
				
		g3.setEdgeWeight(edge2122, 1);
		g3.setEdgeWeight(edge2223, 1);
			
				
		// CREATION DES UTILISATEURS
		ArrayList<Integer> listeDesDonnees3 = new ArrayList<Integer>();
		listeDesDonnees3.add(donnee21.getIDdonnee());
		listeDesDonnees3.add(donnee22.getIDdonnee());
		listeDesDonnees3.add(donnee23.getIDdonnee());
		Utilisateurs utilisateur21 = new Utilisateurs(listeDesDonnees3,1,2);
				
		// ON PLACE LES DONNEES DANS LE GRAPHE
				
		g3.placerDonneeMKP(utilisateur21);
		g3.affichageNoeudGraphe();
	}
}