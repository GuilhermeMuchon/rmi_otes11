package com.cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JOptionPane;

import com.service.MasterService;
import com.service.MessageService;
import com.service.MessageServiceModel;

public class MessageClient {

	private static MessageService stubServer;

	// Responsável por encontrar o servidor ativo
	public static MessageServiceModel getAvailableServer() {
		try {
			Registry registryMasterServer = LocateRegistry.getRegistry("127.0.0.1", 1200);
			MasterService stubMasterServer = (MasterService) registryMasterServer.lookup("MasterServer");
			
			for (MessageServiceModel sm : stubMasterServer.getServers()) {
				System.out.println(sm);
				try {
					Registry registryServer = LocateRegistry.getRegistry(sm.getHost(), sm.getPort());
					stubServer = (MessageService) registryServer.lookup(sm.getServerName());
					return sm;
				} catch (Exception e) {
					System.out.println(sm + " não está disponível!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }
	
	public static void main(String[] args) {
		try {

			boolean end = true;
			while (end) {
				String mensagem = JOptionPane.showInputDialog(null, "Digite a mensagem ('Q' para sair | 'S' para mostrar mensagens)", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
				
				if (mensagem == null || mensagem.equals("Q")) {
					end = false;
					break;
				} else {
					
					getAvailableServer();
					
					switch (mensagem) {
						case "S":
							List<String> retornoList = stubServer.getListOfMsg("cliente1");
							System.out.println(retornoList);
							break;
						default:
							String retornoEcho = stubServer.echo("cliente1", mensagem);
							System.out.println(retornoEcho);
							break;
					}
				}
			}

		} catch (RemoteException re) {
			System.out.println("Erro Remoto: " + re.toString());
			JOptionPane.showMessageDialog(null, "Erro Remoto: " + re.toString(), "Erro Remoto",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			System.out.println("Erro Local: " + e.toString());
			JOptionPane.showMessageDialog(null, "Erro Local: " + e.toString(), "Erro Local",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}