package com.techelevator.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1974644855556556894L;

	private int id;

	public UserNotFoundException(String username, String message) {
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
