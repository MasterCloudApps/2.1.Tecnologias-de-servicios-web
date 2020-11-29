package es.codeurjc.web;

public class UserService {

	private int numUsers;

	public UserService(int numUsers) {
		this.numUsers = numUsers;
	}

	public int getNumUsers() {
		return numUsers;
	}
}
