package com.service;

import java.rmi.RemoteException;
import java.util.List;

public class MasterServiceImpl implements MasterService {

	public MasterServiceImpl() throws RemoteException {
		super();
	}
	
	public void registerServer(String host, String serverName, Integer port) throws RemoteException {
		MessageServiceModel sm = new MessageServiceModel(host, serverName, port);
		// Para registrar o servidor salva ele na lista de servidores
		servers.add(sm);
	}
	
	public List<MessageServiceModel> getServers() throws RemoteException {
		return servers;
	}
}
