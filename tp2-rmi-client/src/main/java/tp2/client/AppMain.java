package tp2.client;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tp2.server.rmi.BanqueDistante;
import tp2.server.rmi.CompteDistant;

public class AppMain {
	static final Logger LOGGER = LogManager.getLogger(AppMain.class);
	static Properties props = new Properties();

	
	public static void main(String[] args) {
		LOGGER.info("tp2 client started");
		// Security manager pour traquer le code frauduleux
	/*	if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}*/
	//	System.setProperty("java.security.policy","tp2-client/src/main/ressources/tp2-client.policy");

		// 0. charger les propriétés de l'application
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		try {
			props.load(input);
			// 1. se connecter au registry
			final int PORT = Integer.parseInt(props.getProperty("registry.port"));
			Registry registry = LocateRegistry.getRegistry(PORT);
			LOGGER.info("1. Se connecter");
			// 2. demander un service a partir de l'url
			BanqueDistante bd = (BanqueDistante) registry.lookup(props.getProperty("banque.url"));
			LOGGER.info("2. demander un service");
			if (bd != null) {
				// 3. invoquer le service BanqueDistante.getCompte
				LOGGER.info("3.invoquer un service");
				CompteDistant cd = bd.getCompte(10);

				// 4. appeler un service secondaire CompteDistant getsolde()
				Double value = cd.getSolde();
				LOGGER.info("value = " + value);
				//5. faire un transfer d'argent avec le compte 2
				
				Boolean result = cd.transferer(100.0, bd, 2);
				LOGGER.info("resultat du transfert ="+ result);
				LOGGER.info("value = "+cd.getSolde());
			}else {
            	LOGGER.info("3.pas de service principal");
			}
		} catch (RemoteException re) {
			// TODO: handle exception
			LOGGER.error("Remote "+ re.getCause().getMessage());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}