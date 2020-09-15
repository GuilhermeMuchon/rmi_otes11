package com.service;

import java.io.Serializable;

public class MessageServiceModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String host;
	private String serverName;
	private Integer port;
	
	public MessageServiceModel(String host, String serverName, Integer port) {
		this.host = host;
		this.serverName = serverName;
		this.port = port;
	}
	
	public String getHost() {
		return host;
	}
	
	public String getServerName() {
		return serverName;
	}
	
	public Integer getPort() {
		return port;
	}

}
