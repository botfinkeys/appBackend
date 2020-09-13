package com.tutorial.crud.dto;

public class Message {
	
	private String mensaje;

	
	
	
	public Message(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
