package tp2.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tp2.server.bancaire.Compte;

public interface CompteDistant extends Remote {

	public Double getSolde() throws RemoteException;
	public Integer getNumero()throws RemoteException;
	public Boolean transferer (Double montant, BanqueDistante bd, Integer numero ) throws RemoteException;
	//mauvais choix 
//	public Boolean transferer (Double montant, CompteDistant cd ) throws RemoteException;
	
	
}
