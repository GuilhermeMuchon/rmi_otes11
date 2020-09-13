package rmi_otes11.com.cliente;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import rmi_otes11.com.servidor.Message;

public class MessageClient {

	private static Message objetoRemoto;
	private static String mensagem;
	private static int port = 1099;

	public static void main(String[] args) {
		try {

			boolean end = true;
			while (end) {
				try {
					Registry reg = LocateRegistry.getRegistry("localhost", MessageClient.port);
					Message objetoRemoto = (Message) reg.lookup("Server" + MessageClient.port);
					
//					objetoRemoto = (Message) Naming.lookup("//localhost:" + port + "/MessageService");
					
					System.out.println("Conectado na porta: " + MessageClient.port);
					
					mensagem = JOptionPane.showInputDialog(null, "Digite a mensagem ('Q' para sair | 'S' para mostrar mensagens)", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
					
					
					if (mensagem == null || mensagem.equals("Q")) {
						end = false;
						break;
					} else {
						switch (mensagem) {
							case "S":
								System.out.println(objetoRemoto.mostraListaDeMsg());
								break;
							default:
								System.out.println(objetoRemoto.imprimir(mensagem));
								System.out.println("Mensagem: " + objetoRemoto);
								objetoRemoto.adicionaMsg(mensagem);
								break;
						}
					}
				} catch (NotBoundException|ConnectException ce) {
					System.out.println("Porta " + MessageClient.port + " não está ativa!");
	                MessageClient.port++;
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