package rmi_otes11.com.servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessageServer {
	
//	public static Registry registry = LocateRegistry.createRegistry(1020);
//	registry.toString()
	MessageServer() {
		try {
			Message c = new MessageImple();
//			System.setProperty("java.rmi.server.hostname", "127.0.0.1");
			Naming.rebind("rmi://127.0.0.1:1099/MessageService", c);
		} catch (RemoteException re) {
			System.out.println("Erro Remoto: " + re.toString());
		} catch (Exception e) {
			System.out.println("Erro Local: " + e.toString());
		}
	}

	public static void main(String[] args) {
		new MessageServer();
	}

}