package tp2.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tp2.server.bancaire.Banque;
import tp2.server.bancaire.Compte;

public class BanqueExporte extends UnicastRemoteObject implements BanqueDistante {

	//permet de trouvé le compte dans la banque réelle
	protected Banque support;
	static final Logger LOGGER= LogManager.getLogger(BanqueExporte.class);
	
	public BanqueExporte(Banque banque) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		support = banque;
	}
	
	@Override
	public CompteDistant getCompte(Integer numero) throws RemoteException {
		Optional<Compte> opt = support.getCompte(numero);
		if (!opt.isEmpty()) {
			//Le compte existe
			Compte cpt = opt.get();
			LOGGER.info("getCompte("+numero+")"+ cpt.toString());
			CompteDistant cd = new CompteExporte(cpt);
			return cd;
		}else {
			//le compte n'existe pas
			LOGGER.error("Pas de compte associé a ce numero"+numero);
			return new CompteExporte(null);
		}
		
	}



}
