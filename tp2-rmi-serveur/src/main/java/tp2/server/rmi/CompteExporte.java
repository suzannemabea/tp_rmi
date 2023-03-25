package tp2.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

import tp2.server.bancaire.Compte;

public class CompteExporte extends UnicastRemoteObject implements CompteDistant {

	protected Optional<Compte> support;
	
	
	// Class Proxy
	public CompteExporte( Compte cpt) throws RemoteException{
		super();
		if(cpt!=null)
		support = Optional.ofNullable(cpt); //methode qui creer un optional si l'optional est non nulle
		
	}

	@Override
	public Double getSolde() throws RemoteException {
		// TODO Auto-generated method stub
		if(!support.isEmpty()) {
			return support.get().getSolde();
		}else {
			throw new RemoteException("Pas de compte associé");
		}
	}


	@Override
	public Integer getNumero() throws RemoteException {
		if(!support.isEmpty()) {
			return support.get().getNumero();
		}else {
			throw new RemoteException("Pas de compte associé");
		}
					
	}
	
	public Boolean transferer(Double montant, BanqueDistante bd, Integer numero) throws RemoteException {
		if(!support.isEmpty()) {
			CompteExporte ce = (CompteExporte) bd.getCompte(numero);
			if (!ce.support.isEmpty()) {
				support.get().transferer(montant, ce.support.get());
			}else {
				throw new RemoteException("Pas de compte destinataire existant");				
			}
		}else {
			throw new RemoteException("Pas de compte source existant");
		}
		return null;
		
	}
}
