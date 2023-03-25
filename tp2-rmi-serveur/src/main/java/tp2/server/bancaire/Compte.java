package tp2.server.bancaire;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Classe métier qui représente le compte bancaire 
 * d'un client de la banque
 */
public class Compte {

	static final Logger LOGGER =LogManager.getLogger(Compte.class);
	protected Integer numero;
	protected Double solde;
	
	public Compte(Integer numero, Double solde) {
		super();
		this.numero = numero;
		this.solde = solde;
	}
	
	public boolean deposer(Double montant) {
		if(montant >=0.0) {
			solde += montant;
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
	
	public boolean retirer (Double montant) {
		if(montant >=0.0) {
			solde -= montant;
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
	public Integer getNumero() {
		
		return this.numero;
	}
	public Double getSolde() {
		LOGGER.info("Appel de getSolde ->"+solde+ ")");
		return solde;
	}
	
	
	
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", solde=" + solde + "]";
	}
	
	
	
	public Boolean transferer(Double montant, Compte destinataire) {
		if(montant >0.0) {
			if(this.retirer(montant)) {
				destinataire.deposer(montant);
				return Boolean.TRUE;
			}else {
				LOGGER.info("Compte Source "+ numero + "insuffisamment crédité");
				return Boolean.FALSE;
			}
		}else {
			LOGGER.info("La valeur du montant a transférer est négative "+ montant);
			return Boolean.FALSE;
		}
	}
	
	
}
