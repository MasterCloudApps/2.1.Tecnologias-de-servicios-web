package es.codeurjc.web;

public class UserService {

	private final int numUsers;

	public UserService(int numUsers) {
		this.numUsers = numUsers;
	}

	public int getNumUsers() {
		return numUsers;
	}
}
