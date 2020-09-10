package rmi_otes11.com.cliente;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import rmi_otes11.com.servidor.Message;

public class MessageClient {

	private static Message objetoRemoto;
	private static String mensagem;

	public static void main(String[] args) {
		try {

			boolean end = true;
			while (end) {
				objetoRemoto = (Message) Naming.lookup("//localhost:1100/MessageService");
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
						System.out.println("Mensagem : " + objetoRemoto);
						objetoRemoto.adicionaMsg(mensagem);
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