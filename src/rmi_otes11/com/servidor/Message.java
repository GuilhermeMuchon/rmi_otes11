package rmi_otes11.com.servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote {
	
	public String imprimir(String mensagem) throws RemoteException;

	public void mostraListaDeMsg() throws RemoteException;

	public void adicionaMsg(String mensagem) throws RemoteException;

}
