package tp2.server;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tp2.server.bancaire.Banque;
import tp2.server.bancaire.Compte;
import tp2.server.rmi.BanqueExporte;

public class AppMain {

	static final Logger LOGGER = LogManager.getLogger(AppMain.class);
	static Properties props = new Properties();

	public static void main(String[] args) {

		// 0. lire les propriétés de l'application
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");

		try {

			props.load(input);

			// 1. démarrer l'annuaire
			final int PORT = Integer.parseInt(props.getProperty("registry.port"));
			Registry registry = LocateRegistry.createRegistry(PORT);
			// 2. publier le service BanqueExporte dans l'annuaire
			Banque banque = new Banque("BMCI");
			Optional<Compte> compte1 = banque.creerCompte(200.0);
			Optional<Compte> compte2 = banque.creerCompte(400.0);
			compte2.get().transferer(100.0, compte1.get());
			LOGGER.info(compte1.toString());				
			LOGGER.info(compte2.toString());
			
			BanqueExporte be = new BanqueExporte(banque);
			registry.rebind("banque.url", be);
			// 3. Attendre que le client arrive
			LOGGER.info("Attente.... ");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}
}
