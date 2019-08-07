package com.techelevator.exception;

public class PostNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6974644855556556894L;

	private int id;

	public PostNotFoundException(int id, String message) {
		super(message);
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
