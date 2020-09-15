package com.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {

	public MessageServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	// Apresenta mensagem e atualiza demais servidores com ela
	public String echo(String nomeCliente, String mensagem) throws RemoteException {
		updateOtherServers(nomeCliente, mensagem);
		return "Mensagem: " + mensagem;
	}
	
	@Override
	// Retorna lista com o hash map das mensagens
	public List<String> getListOfMsg(String nomeCliente) throws RemoteException {
		return messageHashMap.get(nomeCliente);
	}
	
	// Adiciona nova mensagem na lista de mensagens
	public void addNewMessage(String nomeCliente, String mensagem) throws RemoteException {
		List<String> messagesList = messageHashMap.get(nomeCliente);
		if(messagesList == null) {
			messagesList = new ArrayList<String>();
		}
		messagesList.add(mensagem);
		messageHashMap.put(nomeCliente, messagesList);		
	}
	
	// Comunica todos os outros servidores sobre a nova mensagem
	public void updateOtherServers(String nomeCliente, String mensagem) {
		try {
			Registry registryMasterServer = LocateRegistry.getRegistry("127.0.0.1", 1200);
			MasterService stubMasterServer = (MasterService) registryMasterServer.lookup("MasterServer");
			System.out.println("Servidores ativos: " + stubMasterServer.getServers());
			
			for (MessageServiceModel sm : stubMasterServer.getServers()) {
				try {
					Registry registryServer= LocateRegistry.getRegistry(sm.getHost(), sm.getPort());
					MessageService stubServer = (MessageService) registryServer.lookup(sm.getServerName());
					stubServer.addNewMessage(nomeCliente, mensagem);
				} catch (RemoteException re) {
					System.out.println("MessageServiceImpl Erro Remoto: " + re.toString());
				}
			}
		} catch (Exception e) {
			System.out.println("MasterServer não está disponível");
		}		
	}

}
