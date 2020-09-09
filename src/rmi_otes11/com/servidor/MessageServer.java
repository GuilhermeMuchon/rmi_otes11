package rmi_otes11.com.servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MessageServer {
	
	public Message stub;
	public Registry registry;
//	registry.toString()
	
	MessageServer() {
		try {
			
			Message objetoServidor = new MessageImple();
//			stub = (Message) UnicastRemoteObject.exportObject(objetoServidor, 0);
		    registry = LocateRegistry.createRegistry(1100);
	        registry.rebind("MessageService", objetoServidor);
	        
//			System.setProperty("java.rmi.server.hostname", "127.0.0.1");
//			Naming.rebind("rmi://127.0.0.1:1099/MessageService", c);
			System.out.println("MessageService esta ativo!");
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