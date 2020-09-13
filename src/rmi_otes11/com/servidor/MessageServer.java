package rmi_otes11.com.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class MessageServer {

	public static int port = 1100;
	public Message stub;
	public Registry registry;
	
	MessageServer() {
		try {
			
			Message objetoServidor = new MessageImple();
		    registry = LocateRegistry.createRegistry(MessageServer.port);
	        registry.rebind("Server" + MessageServer.port, objetoServidor);
	        
			System.out.println("MessageService " + MessageServer.port + " esta ativo!");
		} catch (ExportException ee) {
			MessageServer.port++;
			new MessageServer();
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