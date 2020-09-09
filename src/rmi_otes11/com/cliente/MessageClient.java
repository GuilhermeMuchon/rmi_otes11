package rmi_otes11.com.cliente;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import rmi_otes11.com.servidor.Message;

public class MessageClient {
	public static void main(String[] args) {
		try {
			Message m = (Message) Naming.lookup("//127.0.0.1:1099/MessageService");
			System.out.println("Mensagem : " + m);
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Erro Remoto: " + re.toString(), "Erro Remoto",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro Local: " + e.toString(), "Erro Local",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}