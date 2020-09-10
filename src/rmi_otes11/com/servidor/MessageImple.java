package rmi_otes11.com.servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MessageImple extends UnicastRemoteObject implements Message {

	private static final long serialVersionUID = 7308250285961892175L;
	private List<String> listaDeMsg = new ArrayList<String>();

	protected MessageImple() throws RemoteException {
		super();
	}

	@Override
	public String imprimir(String mensagem) throws RemoteException {
		return "S1:" + mensagem;
	}

	@Override
	public void mostraListaDeMsg() throws RemoteException {
		listaDeMsg.forEach(mensagem -> {
			System.out.println(mensagem);
		});
	}

	@Override
	public void adicionaMsg(String mensagem) throws RemoteException {
		listaDeMsg.add(mensagem);
	}
}
