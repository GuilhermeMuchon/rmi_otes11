package rmi_otes11.com.cliente;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import rmi_otes11.com.servidor.Message;

public class MessageClient {

	private static Message mensagem;
	private static String dados;

	public static void main(String[] args) {
		try {

			boolean end = true;
			while (end) {
				mensagem = (Message) Naming.lookup("//localhost:1100/MessageService");
				dados = JOptionPane.showInputDialog(null, "Digite a mensagem ('Q' para sair)", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
				

				if (dados.equals("Q")) {
					end = false;
					break;
				}
				
				System.out.println(mensagem.imprimir(dados));
				System.out.println("Mensagem : " + mensagem);
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