package tp2.server.bancaire;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Banque {
	
	static final Logger LOGGER =LogManager.getLogger(Banque.class);
	protected String nom;
	protected  List<Compte> comptes;

	public Banque (String nom ) {
		this.nom = nom;
		comptes = new LinkedList<>();
	}
	
	public Optional<Compte> creerCompte(Double initSolde) {
		
		final int numero = comptes.size()+1;
		Compte cpt = new Compte(numero, initSolde);
		comptes.add(cpt);
		
		return Optional.of(cpt);
	}
	
	public Optional<Compte> getCompte(final Integer numero) {
		
		LOGGER.info("Appel de getCompte("+numero+ ")");
		for(Compte c : comptes) {
			if(c.getNumero() == numero) {
				return Optional.of(c);
			}
			
		}
		return Optional.empty();
	}
	
}
