package com.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface MasterService extends Remote {

	// Lista de servidores
	List<MessageServiceModel> servers = new ArrayList<MessageServiceModel>();

	// Registra um servidor
	void registerServer(String host, String serverName, Integer port) throws RemoteException;

	// Retorna a lista de servidores
	List<MessageServiceModel> getServers() throws RemoteException;
}