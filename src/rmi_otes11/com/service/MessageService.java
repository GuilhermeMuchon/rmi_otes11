package com.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MessageService extends Remote {

	Map<String, List<String>> messageHashMap = new HashMap<String, List<String>>();

	String echo(String nomeCliente, String mensagem) throws RemoteException;

	List<String> getListOfMsg(String nomeCliente) throws RemoteException;

	void addNewMessage(String nomeCliente, String mensagem) throws RemoteException; 
}