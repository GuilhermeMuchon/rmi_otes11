package com.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

import com.service.MasterService;
import com.service.MessageService;
import com.service.MessageServiceImpl;

public class MessageServer {

	private static int port = 1100;
	
	MessageServer() {
		try {
			
			// Da um nome pro servidor para diferenciá-lo
			String serverName = "Server" + MessageServer.port;
	        
			MessageServiceImpl server = new MessageServiceImpl();
			MessageService stub = (MessageService) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(Integer.valueOf(port));
			registry.rebind(serverName, stub);
			
			System.out.println(serverName + " ativo na porta " + MessageServer.port + "!");
			
			Registry registryMasterServer = LocateRegistry.getRegistry("127.0.0.1", 1200);
			MasterService stubMasterServer = (MasterService) registryMasterServer.lookup("MasterServer");
			stubMasterServer.registerServer("127.0.0.1", serverName, Integer.valueOf(port));

			System.out.println("Servidores ativos: " + stubMasterServer.getServers());
		} catch (ExportException ee) {
			// Se der erro de servidor já existente na porta, acrescenta o valor da porta e tenta novamente
			MessageServer.port++;
			new MessageServer();
		} catch (RemoteException re) {
			System.out.println("MessageServer Erro Remoto: " + re.toString());
		} catch (Exception e) {
			System.out.println("MessageServer Erro Local: " + e.toString());
		}
	}

	public static void main(String[] args) {
		new MessageServer();
	}

}