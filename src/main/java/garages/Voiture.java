package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Precondition : la voiture ne doit pas etre deja� dans un garage
	 *
	 * @param g le garage ou la voiture va stationner
	 * @throws java.lang.Exception Si deja� dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (this.estDansUnGarage()==true) {
			throw new IllegalArgumentException("La voiture est deja dans un garage.") ;
		}
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (this.estDansUnGarage()==false) {
			throw new IllegalArgumentException("La voiture n'est pas dans un garage.") ;
		}
		if (myStationnements.size()==0) {
			throw new IllegalArgumentException("Aucun stationnement n'a ete enregistre.") ;
		}else {
			Stationnement dernierSta = myStationnements.get(myStationnements.size()-1) ;
			dernierSta.terminer();
		}
		
		// TODO: Implémenter cette méthode
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
		Set<Garage> listeGarages = new HashSet<Garage>() ;
		if (myStationnements.size()!=0) {
			for (int i=0; i<myStationnements.size() ; i++) {
				Garage g = myStationnements.get(i).getGarage() ;
				listeGarages.add(g) ;
			}
		}
		return listeGarages ;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// TODO: Implémenter cette méthode
		boolean r ;
		if (myStationnements.size()==0) {
			r=false ;
		}else {
			Stationnement dernierSta = myStationnements.get(myStationnements.size()-1) ;
			r = dernierSta.estEnCours() ;
		}
		
		return r ;
		// Vrai si le dernier stationnement est en cours
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entree / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit a imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode
		Set<Garage> g = this.garagesVisites() ;
		for (Garage i : g) {
			out.println(i + " : \n");
			for (Stationnement s : myStationnements) {
				if (s.getGarage()==i) {
					out.println(s + "\n") ;
				}
			}
		}
	}

}
