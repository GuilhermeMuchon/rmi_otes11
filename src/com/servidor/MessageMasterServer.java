package com.servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.service.MasterService;
import com.service.MasterServiceImpl;

// Servidor mestre
public class MessageMasterServer {
	public static void main(String args[]) {
		try {
			MasterServiceImpl server = new MasterServiceImpl();
			MasterService stub = (MasterService) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(1200);
			registry.rebind("MasterServer", stub);
			
			System.out.println("MasterServer ativo na porta 1200!");
	    } catch(Exception e) {
			e.printStackTrace();
	    }
	}
}
