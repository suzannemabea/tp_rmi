package tp2.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tp2.server.bancaire.Compte;

public interface BanqueDistante extends Remote {

	public CompteDistant getCompte(final Integer numero) throws RemoteException;
}
